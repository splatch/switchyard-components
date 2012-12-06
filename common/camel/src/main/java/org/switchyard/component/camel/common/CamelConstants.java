/* 
 * JBoss, Home of Professional Open Source 
 * Copyright 2012 Red Hat Inc. and/or its affiliates and other contributors
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
package org.switchyard.component.camel.common;

/**
 * Constants used by Camel component.
 *
 * @author Magesh Kumar B <mageshbk@jboss.com> (C) 2012 Red Hat Inc.
 */
public interface CamelConstants {

    String COMMON_NAMESPACE_V1 = "urn:switchyard-component-camel-common:config:1.0";

    /**
     * HTTP scheme.
     */
    String HTTP_SCHEME = "http:";

    /**
     * cxfrs transport scheme.
     */
    String CXFRS_SCHEME = "cxfrs:";

    /**
     * Scheme seperator.
     */
    String SCHEME_SUFFIX = "//";

    /**
     * cxfrs://http://<host>:<port> transport scheme.
     */
    String CXFRS_HTTP_SCHEME = CXFRS_SCHEME + SCHEME_SUFFIX + HTTP_SCHEME + SCHEME_SUFFIX;

    /**
     * cxfrs://http:/// transport scheme.
     */
    String CXFRS_HTTP_NO_HOST_SCHEME = CXFRS_HTTP_SCHEME + "/";

    /**
     * resourceClasses.
     */
    String RESOURCE_CLASSES = "resourceClasses=";

    String CAMEL_CXF_RS_RESPONSE_CLASS = "CamelCxfRsResponseClass";
    String CAMEL_CXF_RS_USING_HTTP_API = "CamelCxfRsUsingHttpAPI";

    /**
     * Property added to each Camel Context so that code initialized inside 
     * Camel can access the SY service domain.
     */
    String SERVICE_DOMAIN = "org.switchyard.camel.serviceDomain";

}
