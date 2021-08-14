package core.interfaces;

import core.models.PurchaseHistory;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

public interface IHttpPurchaseRepository {
    HttpResponse createNewPurchase(PurchaseHistory purchase) throws IOException, URISyntaxException, InterruptedException;
}
