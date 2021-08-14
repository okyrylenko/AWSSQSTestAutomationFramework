package core.helpers;

import com.google.inject.Binder;
import com.google.inject.Module;
import core.interfaces.*;
import dataaccess.api.HttpCustomerRepository;
import dataaccess.api.HttpPurchaseRepository;
import dataaccess.sqs.SqsCustomerRepository;
import org.testng.IModuleFactory;
import org.testng.ITestContext;
import testlogic.services.CustomerServiceHttp;
import testlogic.services.CustomerServiceSqs;

public class Ioc implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(IHttpCustomer.class).to(HttpCustomerRepository.class);
        binder.bind(ISqsCustomer.class).to(SqsCustomerRepository.class);
        binder.bind(ICustomerServiceHttp.class).to(CustomerServiceHttp.class);
        binder.bind(ICustomerServiceSqs.class).to(CustomerServiceSqs.class);
        binder.bind(IHttpPurchaseRepository.class).to(HttpPurchaseRepository.class);
        binder.bind(IHttpResponseDecorator.class).to(HttpResponseDecorator.class);
    }
}
