package org.switchyard.component.camel.jpa.model.v1;

import org.switchyard.component.camel.common.marshaller.BaseModelMarshaller;
import org.switchyard.component.camel.common.marshaller.ModelCreator;
import org.switchyard.config.Configuration;
import org.switchyard.config.model.Descriptor;

import static org.switchyard.component.camel.jpa.model.Constants.JPA_NAMESPACE_V1;

public class V1CamelJpaModelMarshaller extends BaseModelMarshaller {

    protected V1CamelJpaModelMarshaller(Descriptor desc) {
        super(desc, JPA_NAMESPACE_V1);

        registerBinding(V1CamelJpaBindingModel.JPA, new ModelCreator<V1CamelJpaBindingModel>() {
            @Override
            public V1CamelJpaBindingModel create(Configuration config, Descriptor descriptor) {
                return new V1CamelJpaBindingModel(config, descriptor);
            }
        });
    }

}
