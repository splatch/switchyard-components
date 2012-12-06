package org.switchyard.component.camel.mail.deploy;

import org.switchyard.component.camel.common.deploy.BaseBindingComponent;
import org.switchyard.component.camel.mail.model.v1.V1CamelMailBindingModel;

public class CamelMailComponent extends BaseBindingComponent {

    public CamelMailComponent() {
        super("CamelMailComponent", V1CamelMailBindingModel.MAIL);
    }

}
