package org.switchyard.component.camel.jms.model.v1;

import org.switchyard.component.camel.common.marshaller.BaseModelMarshaller;
import org.switchyard.component.camel.common.marshaller.ModelCreator;
import org.switchyard.config.Configuration;
import org.switchyard.config.model.Descriptor;

import static org.switchyard.component.camel.jms.model.Constants.JMS_NAMESPACE_V1;

public class V1CamelJmsModelMarshaller extends BaseModelMarshaller {

    protected V1CamelJmsModelMarshaller(Descriptor desc) {
        super(desc, JMS_NAMESPACE_V1);

        registerBinding(V1CamelJmsBindingModel.JMS, new ModelCreator<V1CamelJmsBindingModel>() {
            @Override
            public V1CamelJmsBindingModel create(Configuration config, Descriptor descriptor) {
                return new V1CamelJmsBindingModel(config, descriptor);
            }
        });
    }

}
