package org.switchyard.component.sca;

import javax.xml.namespace.QName;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.switchyard.Message;
import org.switchyard.MockDomain;
import org.switchyard.MockExchange;
import org.switchyard.Service;
import org.switchyard.ServiceReference;
import org.switchyard.config.model.composite.v1.V1SCABindingModel;
import org.switchyard.exception.SwitchYardException;
import org.switchyard.metadata.InOnlyOperation;
import org.switchyard.metadata.InOnlyService;
import org.switchyard.remote.RemoteEndpoint;
import org.switchyard.remote.RemoteRegistry;
import org.switchyard.remote.cluster.LoadBalanceStrategy;
import org.switchyard.remote.cluster.RandomStrategy;
import org.switchyard.remote.cluster.RoundRobinStrategy;
import org.switchyard.test.MockHandler;

public class SCAInvokerTest {
    
    private MockDomain _domain;
    private MockHandler _provider;
    
    @Before
    public void setUp() throws Exception {
        _domain = new MockDomain();
        _provider = new MockHandler();
        
    }

    
    @Test
    public void localInvocationSameName() throws Exception {
        V1SCABindingModel config = new V1SCABindingModel();
        SCAInvoker invoker = new SCAInvoker(config);
        
        final QName TEST_SERVICE = new QName("urn:test", "SCAInvokerTest");
        ServiceReference reference = _domain.createInOnlyService(TEST_SERVICE, _provider);
        MockExchange ex = new MockExchange();
        Message msg = ex.createMessage().setContent("TEST");
        ex.setMessage(msg);
        ex.consumer(reference, new InOnlyOperation("Test"));
        ex.provider(_domain.getServices().get(0), new InOnlyOperation("Test"));
        invoker.handleMessage(ex);
        
        Assert.assertTrue(_provider.getMessages().size() == 1);
        Assert.assertEquals(msg, _provider.getMessages().poll().getMessage());
    }
    
    @Test
    public void localInvocationTarget() throws Exception {
        V1SCABindingModel config = new V1SCABindingModel();
        config.setTarget("ServiceB");
        SCAInvoker invoker = new SCAInvoker(config);

        // Serivce A is for app 1, Service B is for app 2
        final QName SERVICE_A = new QName("urn:test", "ServiceA");
        final QName SERVICE_B = new QName("urn:test", "ServiceB");
        ServiceReference referenceA = _domain.registerServiceReference(SERVICE_A, new InOnlyService());
        _domain.registerServiceReference(SERVICE_B, new InOnlyService());
        Service serviceA = _domain.registerService(SERVICE_A, new InOnlyService(), new MockHandler());
        _domain.registerService(SERVICE_B, new InOnlyService(), _provider);
        
        MockExchange ex = new MockExchange();
        Message msg = ex.createMessage().setContent("TEST");
        ex.setMessage(msg);
        ex.consumer(referenceA, new InOnlyOperation(null));
        ex.provider(serviceA, new InOnlyOperation(null));
        invoker.handleMessage(ex);
        
        Assert.assertTrue(_provider.getMessages().size() == 1);
        Assert.assertEquals(msg, _provider.getMessages().poll().getMessage());
    }
    
    @Test
    public void localInvocationTargetNamespace() throws Exception {
        V1SCABindingModel config = new V1SCABindingModel();
        config.setTargetNamespace("urn:app2");
        SCAInvoker invoker = new SCAInvoker(config);

        // urn:app1 is for app 1, urn:app2 is for app 2
        final QName SERVICE_A = new QName("urn:app1", "ServiceA");
        final QName SERVICE_B = new QName("urn:app2", "ServiceA");
        ServiceReference referenceA = _domain.registerServiceReference(SERVICE_A, new InOnlyService());
        _domain.registerServiceReference(SERVICE_B, new InOnlyService());
        Service serviceA = _domain.registerService(SERVICE_A, new InOnlyService(), new MockHandler());
        _domain.registerService(SERVICE_B, new InOnlyService(), _provider);
        
        MockExchange ex = new MockExchange();
        Message msg = ex.createMessage().setContent("TEST");
        ex.setMessage(msg);
        ex.consumer(referenceA, new InOnlyOperation(null));
        ex.provider(serviceA, new InOnlyOperation(null));
        invoker.handleMessage(ex);
        
        Assert.assertTrue(_provider.getMessages().size() == 1);
        Assert.assertEquals(msg, _provider.getMessages().poll().getMessage());
    }
    
    @Test
    public void createOOTBLoadBalancers() throws Exception {
        V1SCABindingModel config = new V1SCABindingModel();
        SCAInvoker invoker = new SCAInvoker(config);
        
        LoadBalanceStrategy roundRobin = invoker.createLoadBalancer("RoundRobinStrategy");
        Assert.assertTrue(roundRobin instanceof RoundRobinStrategy);
        LoadBalanceStrategy random = invoker.createLoadBalancer("RandomStrategy");
        Assert.assertTrue(random instanceof RandomStrategy);
    }
    
    @Test
    public void createCustomLoadBalancers() throws Exception {
        V1SCABindingModel config = new V1SCABindingModel();
        SCAInvoker invoker = new SCAInvoker(config);
        
        LoadBalanceStrategy lb = invoker.createLoadBalancer("org.switchyard.component.sca.MyLoadBalancer");
        Assert.assertTrue(lb instanceof MyLoadBalancer);
        
        // try to create a bogus load balancer
        try {
            invoker.createLoadBalancer("org.foo.Bar");
            Assert.fail("Should not be able to create a bogus load balance strategy");
        } catch (SwitchYardException ex) {
            // expected
        }
    }
}

class MyLoadBalancer implements LoadBalanceStrategy {

    @Override
    public RemoteEndpoint selectEndpoint(QName serviceName) {
        return null;
    }

    @Override
    public RemoteRegistry getRegistry() {
        return null;
    }

    @Override
    public void setRegistry(RemoteRegistry registry) {
    }
}
