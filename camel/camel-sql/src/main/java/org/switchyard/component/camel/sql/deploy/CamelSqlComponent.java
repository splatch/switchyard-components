package org.switchyard.component.camel.sql.deploy;

import org.switchyard.component.camel.common.deploy.BaseBindingComponent;
import org.switchyard.component.camel.sql.model.v1.V1CamelSqlBindingModel;

public class CamelSqlComponent extends BaseBindingComponent {

    public CamelSqlComponent() {
        super("CamelSqlComponent", V1CamelSqlBindingModel.SQL);
    }

}
