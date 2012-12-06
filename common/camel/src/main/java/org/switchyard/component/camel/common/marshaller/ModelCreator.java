package org.switchyard.component.camel.common.marshaller;

import org.switchyard.config.Configuration;
import org.switchyard.config.model.Descriptor;
import org.switchyard.config.model.Model;

public interface ModelCreator<T extends Model> {
    T create(Configuration config, Descriptor descriptor);
}