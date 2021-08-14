package testlogic.services;

import core.helpers.Convert;
import core.interfaces.ICustomerServiceHttp;
import core.interfaces.IHttpCustomer;
import core.models.CustomerToReceive;
import core.models.CustomerToSend;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

public class CustomerServiceHttp implements ICustomerServiceHttp {

    @Inject
    IHttpCustomer customer;

    public CustomerServiceHttp(){
    }

    public HttpResponse getCustomer(int id) throws IOException, URISyntaxException, InterruptedException {
        return customer.find(id);
    }

    public HttpResponse add(CustomerToSend customer) throws IOException, URISyntaxException, InterruptedException {
        return this.customer.add(customer);
    }
}
