package dataaccess.api;

import core.interfaces.IHttpCustomer;
import dataaccess.clients.HTTPClient;
import core.helpers.Convert;
import core.models.CustomerToSend;

import java.net.URI;
import java.net.http.HttpRequest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

public class HttpCustomerRepository extends HTTPClient implements IHttpCustomer {
    private final String BASEURL = "http://www.localhost:8080/customer/";

    @Override
    public HttpResponse add(CustomerToSend customer) throws IOException, URISyntaxException, InterruptedException {
        String cust = Convert.objectToString(customer);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASEURL))
                .header("Content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(cust))
                .build();
        return super.post(request);
    }

    @Override
    public HttpResponse find(int id) throws IOException, URISyntaxException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASEURL +id))
                .GET()
                .build();
        return super.get(request);
    }
}
