/* 
 * JBoss, Home of Professional Open Source 
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved. 
 * See the copyright.txt in the distribution for a 
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use, 
 * modify, copy, or redistribute it subject to the terms and conditions 
 * of the GNU Lesser General Public License, v. 2.1. 
 * This program is distributed in the hope that it will be useful, but WITHOUT A 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details. 
 * You should have received a copy of the GNU Lesser General Public License, 
 * v.2.1 along with this distribution; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, 
 * MA  02110-1301, USA.
 */

package org.switchyard.component.soap.composer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;
import javax.wsdl.Operation;
import javax.wsdl.Part;
import javax.wsdl.Port;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.Detail;
import javax.xml.soap.DetailEntry;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.dom.DOMSource;

import org.apache.log4j.Logger;
import org.switchyard.Exchange;
import org.switchyard.ExchangeState;
import org.switchyard.Message;
import org.switchyard.component.common.composer.BaseMessageComposer;
import org.switchyard.component.soap.config.model.SOAPMessageComposerModel;
import org.switchyard.component.soap.util.SOAPUtil;
import org.switchyard.component.soap.util.WSDLUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The SOAP implementation of MessageComposer simply copies the SOAP body into
 * the Message and SOAP headers into the Message's context, and vice-versa.
 *
 * @author Magesh Kumar B <mageshbk@jboss.com> (C) 2011 Red Hat Inc.
 */
public class SOAPMessageComposer extends BaseMessageComposer<SOAPBindingData> {

    // Constant suffix used for the reply wrapper when the composer is configured to 
    // wrap response messages with operation name.
    private static final String DOC_LIT_WRAPPED_REPLY_SUFFIX = "Response";

    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String CONTENT_ID = "Content-ID";
    private static final String CONTENT_ID_START = "<";
    private static final String CONTENT_DISPOSITION_NAME = "name=";
    private static final String CONTENT_DISPOSITION_QUOTE = "\"";
    private static final String TEMP_FILE_EXTENSION = ".tmp";

    private static Logger _log = Logger.getLogger(SOAPMessageComposer.class);
    private SOAPMessageComposerModel _config;
    private Port _wsdlPort;
    private Boolean _documentStyle;

    /**
     * {@inheritDoc}
     */
    @Override
    public Message compose(SOAPBindingData source, Exchange exchange) throws Exception {
        final SOAPMessage soapMessage = source.getSOAPMessage();
        final Message message = exchange.createMessage();
        final Boolean input = exchange.getPhase() == null;

        getContextMapper().mapFrom(source, exchange.getContext(message));

        final SOAPBody soapBody = soapMessage.getSOAPBody();
        if (soapBody == null) {
            throw new SOAPException("Missing SOAP body from request");
        }

        try {
            if (soapBody.hasFault()) {
                // peel off the Fault element
                SOAPFault fault = soapBody.getFault();
                if (fault.hasDetail()) {
                    Detail detail = fault.getDetail();
                    // We only support one entry at this moment
                    DetailEntry entry = null;
                    Iterator<DetailEntry> entries = detail.getDetailEntries();
                    if (entries.hasNext()) {
                        entry = entries.next();
                    }
                    if (entry != null) {
                        Node detailNode = entry.getParentNode().removeChild(entry);
                        message.setContent(new DOMSource(detailNode));
                        return message;
                    }
                }
            }

            List<Element> bodyChildren = getChildElements(soapBody);
            if (bodyChildren.size() > 1) {
                throw new SOAPException("Found multiple SOAPElements in SOAPBody");
            } else if (bodyChildren.size() == 0 || bodyChildren.get(0) == null) {
                throw new SOAPException("Could not find SOAPElement in SOAPBody");
            }

            Node bodyNode = bodyChildren.get(0);
            if (_documentStyle) {
                if (_config != null && _config.isUnwrapped()) {
                    String opName = exchange.getContract().getConsumerOperation().getName();
                    // peel off the operation wrapper, if present
                    if (opName != null && opName.equals(bodyNode.getLocalName())) {
                        List<Element> subChildren = getChildElements(bodyNode);
                        if (subChildren.size() == 0 || subChildren.size() > 1) {
                            _log.debug("Unable to unwrap element: " + bodyNode.getLocalName()
                                   + ". A single child element is required.");
                        } else {
                            bodyNode = subChildren.get(0);
                        }
                    }
                }
            }
            bodyNode = bodyNode.getParentNode().removeChild(bodyNode);
            message.setContent(new DOMSource(bodyNode));

            // SOAP Attachments
            Iterator<AttachmentPart> aparts = (Iterator<AttachmentPart>) soapMessage.getAttachments();
            while (aparts.hasNext()) {
                AttachmentPart apRequest = aparts.next();
                String name = apRequest.getDataHandler().getDataSource().getName();
                if ((name == null) || (name.length() == 0)) {
                    String[] disposition = apRequest.getMimeHeader(CONTENT_DISPOSITION);
                    String[] contentId = apRequest.getMimeHeader(CONTENT_ID);
                    name = (contentId != null) ? contentId[0] : null;
                    if ((name == null) && (disposition != null)) {
                        int start = disposition[0].indexOf(CONTENT_DISPOSITION_NAME);
                        String namePart = disposition[0].substring(start + CONTENT_DISPOSITION_NAME.length() + 1);
                        int end = namePart.indexOf(CONTENT_DISPOSITION_QUOTE);
                        name = namePart.substring(0, end);
                    } else if (name == null) {
                        // TODO: Identify the extension using content-type
                        name = UUID.randomUUID() + TEMP_FILE_EXTENSION;
                    } else if (name.startsWith(CONTENT_ID_START)) {
                        name = name.substring(1, name.length() - 1);
                    }
                }
                message.addAttachment(name, new ByteArrayDataSource(apRequest.getDataHandler().getInputStream(), apRequest.getDataHandler().getContentType()));
            }
        } catch (Exception ex) {
            if (ex instanceof SOAPException) {
                throw (SOAPException) ex;
            }
            throw new SOAPException(ex);
        }

        return message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SOAPBindingData decompose(Exchange exchange, SOAPBindingData target) throws Exception {
        final SOAPMessage soapMessage = target.getSOAPMessage();
        final Message message = exchange.getMessage();
        final Boolean input = exchange.getPhase() == null;

        if (message != null) {
            // check to see if the payload is null or it's a full SOAP Message
            if (message.getContent() == null) {
                throw new SOAPException("Unable to create SOAP Body due to null message content");
            }
            if (message.getContent() instanceof SOAPMessage) {
                return new SOAPBindingData((SOAPMessage)message.getContent());
            }
            
            try {
                // convert the message content to a form we can work with
                Node messageNode = message.getContent(Node.class);
                Node messageNodeImport = soapMessage.getSOAPBody().getOwnerDocument().importNode(messageNode, true);
                if (exchange.getState() != ExchangeState.FAULT || isSOAPFaultPayload(messageNode)) {
                    if (_documentStyle) {
                        String opName = exchange.getContract().getProviderOperation().getName();
                        if (_config != null && _config.isUnwrapped()) {
                            String ns = getWrapperNamespace(opName, input);
                            // Don't wrap if it's already wrapped
                            if (!messageNodeImport.getLocalName().equals(opName + DOC_LIT_WRAPPED_REPLY_SUFFIX)) {
                                Element wrapper = messageNodeImport.getOwnerDocument().createElementNS(
                                        ns, opName + DOC_LIT_WRAPPED_REPLY_SUFFIX);
                                wrapper.appendChild(messageNodeImport);
                                messageNodeImport = wrapper;
                            }
                        }
                    }
                    soapMessage.getSOAPBody().appendChild(messageNodeImport);
                    // SOAP Attachments
                    for (String name : message.getAttachmentMap().keySet()) {
                        AttachmentPart apResponse = soapMessage.createAttachmentPart();
                        apResponse.setDataHandler(new DataHandler(message.getAttachment(name)));
                        apResponse.setContentId("<" + name + ">");
                        soapMessage.addAttachmentPart(apResponse);
                    }
                } else {
                    // convert to SOAP Fault since ExchangeState is FAULT but the message is not SOAP Fault
                    SOAPUtil.addFault(soapMessage).addDetail().appendChild(messageNodeImport);
                }
            } catch (Exception e) {
                // Account for exception as payload in case of fault
                if (exchange.getState().equals(ExchangeState.FAULT)
                        && exchange.getMessage().getContent() instanceof Exception) {
                    // Throw the Exception and let JAX-WS format the fault.
                    throw exchange.getMessage().getContent(Exception.class);
                }
                throw new SOAPException("Unable to parse SOAP Message", e);
            }
        }
        
        try {
            getContextMapper().mapTo(exchange.getContext(), target);
        } catch (Exception ex) {
            throw new SOAPException("Failed to map context properties to SOAP message", ex);
        }

        return target;
    }
    
    /**
     * Gets the SOAPMessageComposerModel config.
     * @return the SOAPMessageComposerModel
     */
    public SOAPMessageComposerModel getComposerConfig() {
        return _config;
    }

    /**
     * Sets the SOAPMessageComposerModel config.
     * @param composerConfig configuration
     */
    public void setComposerConfig(SOAPMessageComposerModel composerConfig) {
        _config = composerConfig;
    }

    private boolean isSOAPFaultPayload(org.w3c.dom.Node messageNode) {
        String rootName = messageNode.getLocalName().toLowerCase();

        if (rootName.equals("fault")) {
            String nsURI = messageNode.getNamespaceURI();

            if (nsURI.equals(SOAPUtil.SOAP12_URI) || nsURI.equals(SOAPUtil.SOAP11_URI)) {
                return true;
            }
        }

        return false;
    }

    // Retrieves the immediate child of the specified parent element
    private List<Element> getChildElements(Node parent) {
        List<Element> children = new ArrayList<Element>();
        NodeList nodes = parent.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                children.add((Element)nodes.item(i));
            }
        }
        
        return children;
    }

    private String getWrapperNamespace(String operationName, boolean input) {
        String ns = null;

        if (_wsdlPort != null) {
            Operation operation = WSDLUtil.getOperationByName(_wsdlPort, operationName);
            if (!_documentStyle) {
                ns = input ? operation.getInput().getMessage().getQName().getNamespaceURI()
                    : operation.getOutput().getMessage().getQName().getNamespaceURI();
            } else {
                // Note: WS-I Profile allows only one child under SOAPBody.
                Part part = input ? (Part)operation.getInput().getMessage().getParts().values().iterator().next()
                    : (Part)operation.getOutput().getMessage().getParts().values().iterator().next();
                if (part.getElementName() != null) {
                    ns = part.getElementName().getNamespaceURI();
                } else if (part.getTypeName() != null) {
                    ns = part.getTypeName().getNamespaceURI();
                }
            }
        }
        
        return ns;
    }

    /**
     * Get the WSDL Port used by this message composer.
     * @return the wsdlPort
     */
    public Port getWsdlPort() {
        return _wsdlPort;
    }

    /**
     * Set the WSDL Port used by this message composer.
     * @param wsdlPort WSDL port
     */
    public void setWsdlPort(Port wsdlPort) {
        _wsdlPort = wsdlPort;
    }

    /**
     * Check if the WSDL used is of 'document' style.
     * @return true if 'document' style, false otherwise
     */
    public Boolean isDocumentStyle() {
        return _documentStyle;
    }

    /**
     * Set that the WSDL used is of 'document' style.
     * @param style true or false
     */
    public void setDocumentStyle(Boolean style) {
        _documentStyle = style;
    }

}
