package org.switchyard.component.camel.ftp.model.v1;

import static org.switchyard.component.camel.ftp.Constants.FTP_NAMESPACE_V1;

import org.switchyard.component.camel.common.marshaller.BaseModelMarshaller;
import org.switchyard.component.camel.common.marshaller.ModelCreator;
import org.switchyard.component.camel.ftps.model.v1.V1CamelFtpsBindingModel;
import org.switchyard.component.camel.sftp.model.v1.V1CamelSftpBindingModel;
import org.switchyard.config.Configuration;
import org.switchyard.config.model.Descriptor;

public class V1CamelFtpModelMarshaller extends BaseModelMarshaller {

    protected V1CamelFtpModelMarshaller(Descriptor desc) {
        super(desc, FTP_NAMESPACE_V1);

        registerBinding(V1CamelFtpBindingModel.FTP, new ModelCreator<V1CamelFtpBindingModel>() {
            @Override
            public V1CamelFtpBindingModel create(Configuration config, Descriptor descriptor) {
                return new V1CamelFtpBindingModel(config, descriptor);
            }
        });

        registerBinding(V1CamelFtpsBindingModel.FTPS, new ModelCreator<V1CamelFtpsBindingModel>() {
            @Override
            public V1CamelFtpsBindingModel create(Configuration config, Descriptor descriptor) {
                return new V1CamelFtpsBindingModel(config, descriptor);
            }
        });

        registerBinding(V1CamelSftpBindingModel.SFTP, new ModelCreator<V1CamelSftpBindingModel>() {
            @Override
            public V1CamelSftpBindingModel create(Configuration config, Descriptor descriptor) {
                return new V1CamelSftpBindingModel(config, descriptor);
            }
        });
    }

}
