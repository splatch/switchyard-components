package org.switchyard.component.camel.ftp.deploy;

import org.switchyard.component.camel.common.deploy.BaseBindingComponent;
import org.switchyard.component.camel.ftp.model.v1.V1CamelFtpBindingModel;
import org.switchyard.component.camel.ftps.model.v1.V1CamelFtpsBindingModel;
import org.switchyard.component.camel.sftp.model.v1.V1CamelSftpBindingModel;

public class CamelFtpComponent extends BaseBindingComponent {

    public CamelFtpComponent() {
        super("CamelFtp",
            V1CamelFtpBindingModel.FTP,
            V1CamelFtpsBindingModel.FTPS,
            V1CamelSftpBindingModel.SFTP
        );
    }

}
