package org.switchyard.component.bean.internal.context;

import org.switchyard.Context;
import org.switchyard.Exchange;

public class MessageContextResolver extends ThreadLocalResolver {

    @Override
    protected Context getContext(Exchange exchange) {
        return exchange.getMessage().getContext();
    }

}
