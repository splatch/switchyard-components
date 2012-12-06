/*
 * JBoss, Home of Professional Open Source Copyright 2009, Red Hat Middleware
 * LLC, and individual contributors by the @authors tag. See the copyright.txt
 * in the distribution for a full listing of individual contributors.
 * 
 * This is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This software is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this software; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA, or see the FSF
 * site: http://www.fsf.org.
 */
package org.switchyard.component.camel.common.model.remote.v1;

import java.net.URI;
import java.util.List;

import org.switchyard.component.camel.common.QueryString;
import org.switchyard.component.camel.common.model.file.v1.V1GenericFileBindingModel;
import org.switchyard.component.camel.common.model.remote.CamelRemoteFileBindingModel;
import org.switchyard.component.camel.common.model.remote.CamelRemoteFileConsumerBindingModel;
import org.switchyard.component.camel.common.model.remote.CamelRemoteFileProducerBindingModel;
import org.switchyard.config.Configuration;
import org.switchyard.config.model.Descriptor;

/**
 * Base class for remote file endpoint configurations.
 * 
 * @author Lukasz Dywicki
 */
public abstract class V1CamelRemoteFileBindingModel extends V1GenericFileBindingModel
    implements CamelRemoteFileBindingModel {

    /**
     * Name of 'directory' element.
     */
    private static final String DIRECTORY = "directory";

    /**
     * Name of 'host' element.
     */
    private static final String HOST = "host";

    /**
     * Name of 'port' element.
     */
    private static final String PORT = "port";

    /**
     * Name of 'username' element.
     */
    private static final String USERNAME = "username";

    /**
     * Name of 'password' element.
     */
    private static final String PASSWORD = "password";

    /**
     * Name of 'binary' element.
     */
    private static final String BINARY = "binary";

    /**
     * Name of 'connectTimeout' element.
     */
    private static final String CONNECTION_TIMEOUT = "connectTimeout";

    /**
     * Name of 'throwExceptionOnConnectFailed' element.
     */
    private static final String THROW_EXCEPTION_ON_CONNECT_FAILED = "throwExceptionOnConnectFailed";

    /**
     * Name of 'stepwise' element.
     */
    private static final String STEPWISE = "stepwise";

    /**
     * Name of 'separator' element.
     */
    private static final String SEPARATOR = "separator";

    /**
     * Name of 'separator' element.
     */
    private static final String RECONNECT_DELAY = "reconnectDelay";

    /**
     * Name of 'separator' element.
     */
    private static final String MAXIMUM_RECONNECT_ATTEMPTS = "maximumReconnectAttempts";

    /**
     * Name of 'separator' element.
     */
    private static final String DISCONNECT = "disconnect";

    /**
     * Remote file consumer.
     */
    private CamelRemoteFileConsumerBindingModel _consume;

    /**
     * Remote file producer.
     */
    private CamelRemoteFileProducerBindingModel _produce;

    /**
     * Create a CamelRemoteBindingModel from the specified configuration and descriptor.
     * 
     * @param config The switchyard configuration instance.
     * @param descriptor The switchyard descriptor instance.
     */
    public V1CamelRemoteFileBindingModel(Configuration config, Descriptor descriptor) {
        super(config, descriptor);
    }

    /**
     * Creates CamelRemoteBindingModel with given protocol name.
     * 
     * @param protocol Protocol prefix in uri.
     * @param namespace Namespace prefix.
     */
    protected V1CamelRemoteFileBindingModel(String protocol, String namespace) {
        super(protocol, namespace);

        setModelChildrenOrder(
            HOST, PORT, USERNAME, PASSWORD, BINARY, CONNECTION_TIMEOUT,
            THROW_EXCEPTION_ON_CONNECT_FAILED, STEPWISE, SEPARATOR, RECONNECT_DELAY,
            MAXIMUM_RECONNECT_ATTEMPTS, DISCONNECT
        );
    }

    @Override
    public String getUsername() {
        return getConfig(USERNAME);
    }

    @Override
    public V1CamelRemoteFileBindingModel setUsername(String username) {
        return setConfig(USERNAME, username);
    }

    @Override
    public String getHost() {
        return getConfig(HOST);
    }

    @Override
    public V1CamelRemoteFileBindingModel setHost(String host) {
        return setConfig(HOST, host);
    }

    @Override
    public Integer getPort() {
        return getIntegerConfig(PORT);
    }

    @Override
    public V1CamelRemoteFileBindingModel setPort(int port) {
        return setConfig(PORT, (port));
    }

    @Override
    public String getDirectory() {
        return getConfig(DIRECTORY);
    }

    @Override
    public V1CamelRemoteFileBindingModel setDirectory(String directory) {
        return setConfig(DIRECTORY, directory);
    }

    @Override
    public String getPassword() {
        return getConfig(PASSWORD);
    }

    @Override
    public V1CamelRemoteFileBindingModel setPassword(String password) {
        return setConfig(PASSWORD, password);
    }

    @Override
    public Boolean isBinary() {
        return getBooleanConfig(BINARY);
    }

    @Override
    public V1CamelRemoteFileBindingModel setBinary(boolean binary) {
        return setConfig(BINARY, binary);
    }

    @Override
    public Integer getConnectTimeout() {
        return getIntegerConfig(CONNECTION_TIMEOUT);
    }

    @Override
    public V1CamelRemoteFileBindingModel setConnectionTimeout(int timeout) {
        return setConfig(CONNECTION_TIMEOUT, timeout);
    }

    @Override
    public Boolean isThrowExceptionOnConnectFailed() {
        return getBooleanConfig(THROW_EXCEPTION_ON_CONNECT_FAILED);
    }

    @Override
    public V1CamelRemoteFileBindingModel setThrowExceptionOnConnectFailed(boolean throwException) {
        return setConfig(THROW_EXCEPTION_ON_CONNECT_FAILED, throwException);
    }

    @Override
    public Boolean isStepwise() {
        return getBooleanConfig(STEPWISE);
    }

    @Override
    public V1CamelRemoteFileBindingModel setStepwise(boolean stepwise) {
        return setConfig(STEPWISE, stepwise);
    }

    @Override
    public PathSeparator getSeparator() {
        return getEnumerationConfig(SEPARATOR, PathSeparator.class);
    }

    @Override
    public V1CamelRemoteFileBindingModel setSeparator(String separator) {
        return setConfig(SEPARATOR, separator);
    }

    @Override
    public Integer getMaximumReconnectAttempts() {
        return getIntegerConfig(MAXIMUM_RECONNECT_ATTEMPTS);
    }

    @Override
    public V1CamelRemoteFileBindingModel setMaximumReconnectAttempts(Integer maximumReconnectAttempts) {
        return setConfig(MAXIMUM_RECONNECT_ATTEMPTS, maximumReconnectAttempts);
    }

    @Override
    public Integer getReconnectDelay() {
        return getIntegerConfig(RECONNECT_DELAY);
    }

    @Override
    public V1CamelRemoteFileBindingModel setReconnectDelay(Integer reconnectDelay) {
        return setConfig(RECONNECT_DELAY, reconnectDelay);
    }

    @Override
    public Boolean getDisconnect() {
        return getBooleanConfig(DISCONNECT);
    }

    @Override
    public V1CamelRemoteFileBindingModel setDisconnect(Boolean disconnect) {
        return setConfig(DISCONNECT, disconnect);
    }

    @Override
    public CamelRemoteFileConsumerBindingModel getConsumer() {
        if (_consume == null) {
            Configuration config = getModelConfiguration().getFirstChild(CONSUME);
            _consume = new V1CamelRemoteFileConsumerBindingModel(config,
                getModelDescriptor());
        }
        return _consume;
    }

    @Override
    public V1CamelRemoteFileBindingModel setConsumer(CamelRemoteFileConsumerBindingModel consumer) {
        Configuration config = getModelConfiguration().getFirstChild(CONSUME);
        if (config != null) {
            // set an existing config value
            getModelConfiguration().removeChildren(CONSUME);
            getModelConfiguration().addChild(((V1CamelRemoteFileConsumerBindingModel) consumer)
                .getModelConfiguration());
        } else {
            setChildModel((V1CamelRemoteFileConsumerBindingModel) consumer);
        }
        _consume = consumer;
        return this;
    }

    @Override
    public CamelRemoteFileProducerBindingModel getProducer() {
        if (_produce == null) {
            Configuration config = getModelConfiguration().getFirstChild(PRODUCE);
            _produce = new V1CamelRemoteFileProducerBindingModel(config,
                getModelDescriptor());
        }
        return _produce;
    }

    @Override
    public V1CamelRemoteFileBindingModel setProducer(CamelRemoteFileProducerBindingModel producer) {
        Configuration config = getModelConfiguration().getFirstChild(PRODUCE);
        if (config != null) {
            // set an existing config value
            getModelConfiguration().removeChildren(PRODUCE);
            getModelConfiguration().addChild(((V1CamelRemoteFileProducerBindingModel) producer)
                .getModelConfiguration());
        } else {
            setChildModel((V1CamelRemoteFileProducerBindingModel) producer);
        }
        _produce = producer;
        return this;
    }

    @Override
    public URI getComponentURI() {
        Configuration modelConfiguration = getModelConfiguration();
        List<Configuration> children = modelConfiguration.getChildren();

        String username = "";
        if (getUsername() != null && getPassword() != null) {
            username = getUsername() + ":" + getPassword() + "@";
        } else if (getUsername() != null && getPassword() == null) {
            username = getUsername() + "@";
        } else if (getUsername() == null && getPassword() != null) {
            throw new IllegalArgumentException("Configuration provides password but do not specify user");
        }

        String baseUri = getEndpointProtocol() + "://" + username + getHost();
        baseUri += getPort() != null ? ":" + getPort() : "";
        baseUri += getDirectory() != null ? "/" + getDirectory() : "";

        QueryString queryStr = new QueryString();
        traverseConfiguration(children, queryStr, HOST, PORT, USERNAME, PASSWORD, DIRECTORY);

        return URI.create(baseUri + queryStr.toString());
    }

    protected abstract String getEndpointProtocol();
    protected abstract void enrichQueryString(QueryString queryString);
}
