package org.switchyard.component.camel.amqp.deploy;

import org.switchyard.component.camel.amqp.model.v1.V1CamelAmqpBindingModel;
import org.switchyard.component.camel.common.deploy.BaseBindingComponent;

public class CamelAmqpComponent extends BaseBindingComponent {

    public CamelAmqpComponent() {
        super("CamelAmqpComponent", V1CamelAmqpBindingModel.AMQP);
    }

}
