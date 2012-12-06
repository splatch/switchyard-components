package org.switchyard.component.camel.atom.model.v1;

import org.switchyard.component.camel.common.marshaller.BaseModelMarshaller;
import org.switchyard.component.camel.common.marshaller.ModelCreator;
import org.switchyard.config.Configuration;
import org.switchyard.config.model.Descriptor;

import static org.switchyard.component.camel.atom.model.Constants.ATOM_NAMESPACE_V1;

public class V1CamelAtomModelMarshaller extends BaseModelMarshaller {

    protected V1CamelAtomModelMarshaller(Descriptor desc) {
        super(desc, ATOM_NAMESPACE_V1);

        registerBinding(V1CamelAtomBindingModel.ATOM, new ModelCreator<V1CamelAtomBindingModel>() {
            @Override
            public V1CamelAtomBindingModel create(Configuration config, Descriptor descriptor) {
                return new V1CamelAtomBindingModel(config, descriptor);
            }
        });
    }

}
