package core.interfaces;

import core.models.CustomerToSend;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

public interface IHttpCustomer {
    HttpResponse add(CustomerToSend customer) throws IOException, URISyntaxException, InterruptedException;

    HttpResponse find(int id) throws IOException, URISyntaxException, InterruptedException;
}
