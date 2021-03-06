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
package org.switchyard.component.jca.processor;

import java.util.Properties;

import org.switchyard.Exchange;
import org.switchyard.HandlerException;
import org.switchyard.Message;
import org.switchyard.component.common.composer.MessageComposer;
import org.switchyard.component.jca.composer.JCABindingData;
import org.switchyard.component.jca.composer.JCAComposition;
import org.switchyard.component.jca.config.model.JCABindingModel;

/**
 * Abstract class for processing outbound delivery regarding to JCA outbound contract.
 * 
 * @author <a href="mailto:tm.igarashi@gmail.com">Tomohisa Igarashi</a>
 */
public abstract class AbstractOutboundProcessor {
    
    private String _connectionFactoryJNDIName;
    private Properties _mcfProperties;
    private ClassLoader _appClassLoader;
    private JCABindingModel _jcaBindingModel;
    
    /**
     * process outbound delivery.
     * 
     * @param exchange {@link Exchange}
     * @return response message
     * @throws HandlerException failed to deliver
     */
    public abstract Message process(Exchange exchange) throws HandlerException;

    /**
     * initialize the processor.
     */
    public abstract void initialize();
    
    /**
     * uninitialize the processor.
     */
    public abstract void uninitialize();
    
    /**
     * set connection information.
     * 
     * @param name name of the class which represents connection information
     * @param props connection properties
     * @return {@link AbstractOutboundProcessor} to support method chaining
     */
    public abstract AbstractOutboundProcessor setConnectionSpec(String name, Properties props);
    
    /**
     * set interaction information.
     * 
     * @param name name of the class which represents interaction information
     * @param props interaction properties
     * @return {@link AbstractOutboundProcessor} to support method chaining
     */
    public abstract AbstractOutboundProcessor setInteractionSpec(String name, Properties props);
    
    /**
     * set connection factory JNDI name.
     * 
     * @param name connection factory JNDI name
     * @return {@link AbstractOutboundProcessor} to support method chaining
     */
    public AbstractOutboundProcessor setConnectionFactoryJNDIName(String name) {
        _connectionFactoryJNDIName = name;
        return this;
    }
    
    /**
     * get connection factory JNDI name.
     * 
     * @return connection factory JNDI name
     */
    public String getConnectionFactoryJNDIName() {
        return _connectionFactoryJNDIName;
    }
    
    /**
     * set managed connection factory properties.
     * 
     * @param props {@link Properties} for managed connection factory
     * @return {@link AbstractOutboundProcessor} to support method chaining
     */
    public AbstractOutboundProcessor setMCFProperties(Properties props) {
        _mcfProperties = props;
        return this;
    }
    
    /**
     * get managed connection factory properties.
     * 
     * @return {@link Properties} for managed connection factory
     */
    public Properties getMCFProperties() {
        return _mcfProperties;
    }

    /**
     * set application class loader.
     * 
     * @param loader application class loader
     * @return {@link AbstractOutboundProcessor} to support method chaining
     */
    public AbstractOutboundProcessor setApplicationClassLoader(ClassLoader loader) {
        _appClassLoader = loader;
        return this;
    }
    
    /**
     * get application class loader.
     * 
     * @return application class loader
     */
    public ClassLoader getApplicationClassLoader() {
        return _appClassLoader;
    }
    
    /**
     * set JCA binding model.
     * @param model JCA binding model
     * @return {@link AbstractOutboundProcessor} to support method chaining
     */
    public AbstractOutboundProcessor setJCABindingModel(JCABindingModel model) {
        _jcaBindingModel = model;
        return this;
    }
    
    /**
     * get JCA binding model.
     * @return JCA binding model
     */
    public JCABindingModel getJCABindingModel() {
        return _jcaBindingModel;
    }
    
    protected <D extends JCABindingData> MessageComposer<D> getMessageComposer(Class<D> clazz) {
        return JCAComposition.getMessageComposer(_jcaBindingModel, clazz);
    }
}
