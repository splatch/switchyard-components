package org.switchyard.component.camel.model.v1;

import org.switchyard.component.camel.common.marshaller.BaseModelMarshaller;
import org.switchyard.component.camel.common.marshaller.ModelCreator;
import org.switchyard.config.Configuration;
import org.switchyard.config.model.Descriptor;

import static org.switchyard.component.camel.model.Constants.COMPONENT_NAMESPACE_V1;

public class V1CamelComponentModelMarshaller extends BaseModelMarshaller {

    protected V1CamelComponentModelMarshaller(Descriptor desc) {
        super(desc, COMPONENT_NAMESPACE_V1);
        register(V1CamelImplementationModel.IMPLEMENTATION + "." + V1CamelImplementationModel.CAMEL,
            new ModelCreator<V1CamelImplementationModel>() {
                @Override
                public V1CamelImplementationModel create(Configuration config, Descriptor descriptor) {
                    return new V1CamelImplementationModel(config, descriptor);
                }
        });
    }

}
