package org.switchyard.component.camel.common.deploy;

import java.util.List;

import org.switchyard.ServiceDomain;
import org.switchyard.common.camel.SwitchYardCamelContext;
import org.switchyard.component.camel.common.CamelConstants;
import org.switchyard.deploy.Activator;
import org.switchyard.deploy.BaseComponent;

public class BaseBindingComponent extends BaseComponent {

    /**
     * Default constructor.
     */
    public BaseBindingComponent(String name, String ... types) {
        super(types);
        setName(name);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Activator createActivator(ServiceDomain domain) {
        List<String> activationTypes = getActivationTypes();
        String[] types = activationTypes.toArray(new String[activationTypes.size()]);

        SwitchYardCamelContext camelContext = (SwitchYardCamelContext) domain.getProperties()
            .get(SwitchYardCamelContext.CAMEL_CONTEXT_PROPERTY);
        camelContext.getWritebleRegistry().put(CamelConstants.SERVICE_DOMAIN, domain);
        BaseBindingActivator activator = new BaseBindingActivator(camelContext, types);
        activator.setServiceDomain(domain);
        activator.setEnvironment(getConfig());
        return activator;
    }

}

