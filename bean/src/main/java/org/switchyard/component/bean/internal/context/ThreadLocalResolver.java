package org.switchyard.component.bean.internal.context;

import org.switchyard.Context;
import org.switchyard.Exchange;

public abstract class ThreadLocalResolver implements ContextResolver {

	@Override
	public Context getContext() {
		return getContext(ExchangeHolder.getExchange());
	}

	protected abstract Context getContext(Exchange exchange);

}
