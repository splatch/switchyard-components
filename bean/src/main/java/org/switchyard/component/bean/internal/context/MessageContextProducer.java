package org.switchyard.component.bean.internal.context;

import javax.enterprise.util.AnnotationLiteral;

import org.switchyard.component.bean.Message;

public class MessageContextProducer extends AbstractProducer {

    @Override
    public AnnotationLiteral<Message> getQualifier() {
        return new AnnotationLiteral<Message>() { };
    }

    @Override
    protected ContextResolver getContextResolver() {
        return new MessageContextResolver();
    }
}
