package org.switchyard.component.camel.deploy;

import org.switchyard.component.camel.atom.model.v1.V1CamelAtomBindingModel;
import org.switchyard.component.camel.common.deploy.BaseBindingComponent;

public class CamelAtomComponent extends BaseBindingComponent {

	public CamelAtomComponent() {
		super("CamelAtomComponent", V1CamelAtomBindingModel.ATOM);
	}

}
