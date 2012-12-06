package org.switchyard.component.camel.netty.model.v1;

import static org.switchyard.component.camel.netty.model.Constants.NETTY_NAMESPACE_V1;

import org.switchyard.component.camel.common.marshaller.BaseModelMarshaller;
import org.switchyard.component.camel.common.marshaller.ModelCreator;
import org.switchyard.config.Configuration;
import org.switchyard.config.model.Descriptor;

public class V1CamelNettyModelMarshaller extends BaseModelMarshaller {

    protected V1CamelNettyModelMarshaller(Descriptor desc) {
        super(desc, NETTY_NAMESPACE_V1);

        registerBinding(V1CamelNettyTcpBindingModel.TCP, new ModelCreator<V1CamelNettyTcpBindingModel>() {
            @Override
            public V1CamelNettyTcpBindingModel create(Configuration config, Descriptor descriptor) {
                return new V1CamelNettyTcpBindingModel(config, descriptor);
            }
        });
        registerBinding(V1CamelNettyUdpBindingModel.UDP, new ModelCreator<V1CamelNettyUdpBindingModel>() {
            @Override
            public V1CamelNettyUdpBindingModel create(Configuration config, Descriptor descriptor) {
                return new V1CamelNettyUdpBindingModel(config, descriptor);
            }
        });
    }

}
