package org.switchyard.component.bean.internal.context;

import java.lang.annotation.Annotation;

import javax.enterprise.util.AnnotationLiteral;

import org.switchyard.component.bean.Exchange;

public class ExchangeContextProducer extends AbstractProducer {

    @Override
    public Annotation getQualifier() {
        return new AnnotationLiteral<Exchange>() { };
    }

    @Override
    protected ContextResolver getContextResolver() {
        return new ExchangeContextResolver();
    }

}
