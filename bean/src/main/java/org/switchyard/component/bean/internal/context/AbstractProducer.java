package org.switchyard.component.bean.internal.context;

import java.lang.annotation.Annotation;

import javax.enterprise.inject.spi.Bean;

public abstract class AbstractProducer {

    public final Bean<org.switchyard.Context> getContextBean() {
        return new ContextBean(getQualifier(), getContextResolver());
    }

    protected abstract Annotation getQualifier();

    protected abstract ContextResolver getContextResolver();

}
