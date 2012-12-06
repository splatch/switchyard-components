package org.switchyard.component.camel.netty.deploy;

import org.switchyard.component.camel.common.deploy.BaseBindingComponent;
import org.switchyard.component.camel.netty.model.v1.V1CamelNettyTcpBindingModel;
import org.switchyard.component.camel.netty.model.v1.V1CamelNettyUdpBindingModel;

public class CamelNettyComponent extends BaseBindingComponent {

    public CamelNettyComponent() {
        super("CamelNettyComponent",
            V1CamelNettyTcpBindingModel.TCP,
            V1CamelNettyUdpBindingModel.UDP
        );
    }

}
