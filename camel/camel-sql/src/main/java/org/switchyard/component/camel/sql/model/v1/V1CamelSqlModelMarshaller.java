package org.switchyard.component.camel.sql.model.v1;

import org.switchyard.component.camel.common.marshaller.BaseModelMarshaller;
import org.switchyard.component.camel.common.marshaller.ModelCreator;
import org.switchyard.config.Configuration;
import org.switchyard.config.model.Descriptor;

import static org.switchyard.component.camel.sql.model.Constants.SQL_NAMESPACE_V1;

public class V1CamelSqlModelMarshaller extends BaseModelMarshaller {

    protected V1CamelSqlModelMarshaller(Descriptor desc) {
        super(desc, SQL_NAMESPACE_V1);
    
        registerBinding(V1CamelSqlBindingModel.SQL, new ModelCreator<V1CamelSqlBindingModel>() {
            @Override
            public V1CamelSqlBindingModel create(Configuration config, Descriptor descriptor) {
                return new V1CamelSqlBindingModel(config, descriptor);
            }
        });
    }

}
