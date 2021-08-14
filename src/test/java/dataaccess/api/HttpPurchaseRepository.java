package dataaccess.api;

import core.interfaces.IHttpPurchaseRepository;
import core.models.CustomerToReceive;
import dataaccess.clients.HTTPClient;
import core.helpers.Convert;
import core.models.PurchaseHistory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpPurchaseRepository extends HTTPClient implements IHttpPurchaseRepository {
    private final String BASEURL = "http://www.localhost:8080/purchase";
    public HttpResponse createNewPurchase(PurchaseHistory purchase) throws IOException, URISyntaxException, InterruptedException {
        String data = Convert.objectToString(purchase);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASEURL))
                .header("Content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();
        return super.post(request);
    }
}
