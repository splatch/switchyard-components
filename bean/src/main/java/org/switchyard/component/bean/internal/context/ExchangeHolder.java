/* 
 * JBoss, Home of Professional Open Source 
 * Copyright 2013 Red Hat Inc. and/or its affiliates and other contributors
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
package org.switchyard.component.bean.internal.context;

import org.switchyard.Exchange;

/**
 * Wrapper for thread local which allows to hold exchange.
 */
public class ExchangeHolder {

    /**
     * Exchange holder.
     */
    private static ThreadLocal<Exchange> _exchange = new ThreadLocal<Exchange>();

    /**
     * Set the {@link Exchange} for the current thread.
     * @param exchange The exchange.
     */
    public static void setExchange(Exchange exchange) {
        if (exchange != null) {
            _exchange.set(exchange);
        } else {
            _exchange.remove();
        }
    }

    /**
     * Get the {@link Exchange} for the current thread.
     * @return The exchange.
     */
    public static Exchange getExchange() {
        Exchange context = _exchange.get();

        if (context == null) {
            throw new UnsupportedOperationException("Illegal call to get the SwitchYard Exchange. Must be called within the scope of an Exchange Handler Chain.");
        }
        return context;
    }

}