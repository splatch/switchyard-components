package org.switchyard.component.camel.mail.model.v1;

import static org.switchyard.component.camel.mail.model.Constants.MAIL_NAMESPACE_V1;

import org.switchyard.component.camel.common.marshaller.BaseModelMarshaller;
import org.switchyard.component.camel.common.marshaller.ModelCreator;
import org.switchyard.config.Configuration;
import org.switchyard.config.model.Descriptor;

public class V1CamelMailModelMarshaller extends BaseModelMarshaller {

    protected V1CamelMailModelMarshaller(Descriptor desc) {
        super(desc, MAIL_NAMESPACE_V1);

        registerBinding(V1CamelMailBindingModel.MAIL, new ModelCreator<V1CamelMailBindingModel>() {
            @Override
            public V1CamelMailBindingModel create(Configuration config, Descriptor descriptor) {
                return new V1CamelMailBindingModel(config, descriptor);
            }
        });
    }

}
