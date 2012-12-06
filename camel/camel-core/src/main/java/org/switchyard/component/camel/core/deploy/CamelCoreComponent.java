package org.switchyard.component.camel.core.deploy;

import org.switchyard.component.camel.common.deploy.BaseBindingComponent;
import org.switchyard.component.camel.core.model.direct.v1.V1CamelDirectBindingModel;
import org.switchyard.component.camel.core.model.file.v1.V1CamelFileBindingModel;
import org.switchyard.component.camel.core.model.mock.v1.V1CamelMockBindingModel;
import org.switchyard.component.camel.core.model.seda.v1.V1CamelSedaBindingModel;
import org.switchyard.component.camel.core.model.timer.v1.V1CamelTimerBindingModel;
import org.switchyard.component.camel.core.model.v1.V1CamelBindingModel;

public class CamelCoreComponent extends BaseBindingComponent {

    public CamelCoreComponent() {
        super("CamelCore",
            V1CamelBindingModel.URI,
            V1CamelDirectBindingModel.DIRECT,
            V1CamelFileBindingModel.FILE,
            V1CamelSedaBindingModel.SEDA,
            V1CamelTimerBindingModel.TIMER,
            V1CamelMockBindingModel.MOCK
        );
    }

}
