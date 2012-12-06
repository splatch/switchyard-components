package org.switchyard.component.camel.jms.deploy;

import org.switchyard.component.camel.common.deploy.BaseBindingComponent;
import org.switchyard.component.camel.jms.model.v1.V1CamelJmsBindingModel;

public class CamelJmsComponent extends BaseBindingComponent {

    public CamelJmsComponent() {
        super("CamelJmsComponent", V1CamelJmsBindingModel.JMS);
    }

}
