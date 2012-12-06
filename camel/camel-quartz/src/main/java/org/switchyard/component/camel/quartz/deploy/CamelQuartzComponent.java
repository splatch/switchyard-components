package org.switchyard.component.camel.quartz.deploy;

import org.switchyard.component.camel.common.deploy.BaseBindingComponent;
import org.switchyard.component.camel.quartz.model.v1.V1CamelQuartzBindingModel;

public class CamelQuartzComponent extends BaseBindingComponent {

    public CamelQuartzComponent() {
        super("CamelQuartzComponent", V1CamelQuartzBindingModel.QUARTZ);
    }

}
