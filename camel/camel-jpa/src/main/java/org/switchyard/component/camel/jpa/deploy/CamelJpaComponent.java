package org.switchyard.component.camel.jpa.deploy;

import org.switchyard.component.camel.common.deploy.BaseBindingComponent;
import org.switchyard.component.camel.jpa.model.v1.V1CamelJpaBindingModel;

public class CamelJpaComponent extends BaseBindingComponent {

    public CamelJpaComponent() {
        super("CamelJpaComponent", V1CamelJpaBindingModel.JPA);
    }

}
