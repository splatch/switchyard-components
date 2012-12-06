package org.switchyard.component.camel.config.test.v1;

import org.apache.camel.Endpoint;
import org.switchyard.component.camel.common.model.v1.V1BaseCamelBindingModel;

public abstract class V1BaseCamelReferenceBindingModelTest<T extends V1BaseCamelBindingModel, E extends Endpoint> extends
        V1BaseCamelServiceBindingModelTest<T, E> {

    public V1BaseCamelReferenceBindingModelTest(Class<E> endpointType, String fileName) {
        this(endpointType, fileName, true);
    }

    public V1BaseCamelReferenceBindingModelTest(Class<E> endpointType, String fileName, boolean valid) {
        super(endpointType, fileName, valid);
    }

    @Override
    protected T getFirstBinding() throws Exception {
        return getFirstCamelReferenceBinding(_fileName);
    }

}
