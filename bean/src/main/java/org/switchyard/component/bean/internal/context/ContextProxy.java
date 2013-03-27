/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
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

package org.switchyard.component.bean.internal.context;

import java.util.Set;

import org.switchyard.Context;
import org.switchyard.Property;

/**
 * SwitchYard Context proxy.
 *
 * @author <a href="mailto:tom.fennelly@gmail.com">tom.fennelly@gmail.com</a>
 */
public class ContextProxy implements Context {

    private ContextResolver _resolver;

    public ContextProxy(ContextResolver resolver) {
        _resolver = resolver;
    }

    @Override
    public Property getProperty(String name) {
        return getContext().getProperty(name);
    }

    @Override
    public <T> T getPropertyValue(String name) {
        return getContext().getPropertyValue(name);
    }

    @Override
    public Set<Property> getProperties() {
        return getContext().getProperties();
    }

    @Override
    public void removeProperty(Property property) {
        getContext().removeProperty(property);
    }

    @Override
    public void removeProperties() {
        getContext().removeProperties();
    }

    @Override
    public Property setProperty(String name, Object val) {
        return getContext().setProperty(name, val);
    }

    @Override
    public Context setProperties(Set<Property> properties) {
        return getContext().setProperties(properties);
    }

    @Override
    public Context copy() {
        return getContext().copy();
    }

    @Override
    public Set<Property> getProperties(String label) {
        return getContext().getProperties(label);
    }

    @Override
    public void removeProperties(String label) {
        getContext().removeProperties(label);
    }

    private Context getContext() {
        return _resolver.getContext();
    }

}
