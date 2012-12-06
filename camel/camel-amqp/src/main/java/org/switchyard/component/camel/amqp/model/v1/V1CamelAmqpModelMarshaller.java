package org.switchyard.component.camel.amqp.model.v1;

import org.switchyard.component.camel.common.marshaller.BaseModelMarshaller;
import org.switchyard.component.camel.common.marshaller.ModelCreator;
import org.switchyard.config.Configuration;
import org.switchyard.config.model.Descriptor;

import static org.switchyard.component.camel.amqp.model.Constants.AMQP_NAMESPACE_V1;

public class V1CamelAmqpModelMarshaller extends BaseModelMarshaller {

    protected V1CamelAmqpModelMarshaller(Descriptor desc) {
        super(desc, AMQP_NAMESPACE_V1);

        registerBinding(V1CamelAmqpBindingModel.AMQP, new ModelCreator<V1CamelAmqpBindingModel>() {
            @Override
            public V1CamelAmqpBindingModel create(Configuration config, Descriptor descriptor) {
                return new V1CamelAmqpBindingModel(config, descriptor);
            }
        });
    }

}
