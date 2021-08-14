package core.interfaces;

import core.models.CustomerToSend;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

public interface ICustomerServiceHttp {
    HttpResponse getCustomer(int id) throws IOException, URISyntaxException, InterruptedException;

    HttpResponse add(CustomerToSend customer) throws IOException, URISyntaxException, InterruptedException;
}
