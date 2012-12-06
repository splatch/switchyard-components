/*
 * JBoss, Home of Professional Open Source Copyright 2009, Red Hat Middleware
 * LLC, and individual contributors by the @authors tag. See the copyright.txt
 * in the distribution for a full listing of individual contributors.
 * 
 * This is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This software is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this software; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA, or see the FSF
 * site: http://www.fsf.org.
 */
package org.switchyard.component.camel.quartz.model.v1;

import static org.switchyard.component.camel.quartz.model.CamelQuartzBindingModel.QUARTZ;
import static org.switchyard.component.camel.quartz.Constants.QUARTZ_NAMESPACE_V1;

import org.switchyard.component.camel.common.marshaller.BaseModelMarshaller;
import org.switchyard.component.camel.common.marshaller.ModelCreator;
import org.switchyard.config.Configuration;
import org.switchyard.config.model.Descriptor;

/**
 * Camel quartz v1 model marshaler.
 */
public class V1CamelQuartzModelMarshaller extends BaseModelMarshaller {

    public V1CamelQuartzModelMarshaller(Descriptor desc) {
        super(desc, QUARTZ_NAMESPACE_V1);

        registerBinding(QUARTZ, new ModelCreator<V1CamelQuartzBindingModel>() {
            @Override
            public V1CamelQuartzBindingModel create(Configuration config, Descriptor descriptor) {
                return new V1CamelQuartzBindingModel(config, descriptor);
            }
        });
    }

}
