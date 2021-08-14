package core.helpers;

import core.models.CustomerToSend;
import core.models.PurchaseHistory;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class LocalDataRepository {

    public static CustomerToSend getCustomer() throws IOException, ParseException {
        return Convert.stringToObject(LocalFileReader.getCustomer(), CustomerToSend.class);
    }

    public static PurchaseHistory getPurchase() throws IOException {
        return Convert.stringToObject(LocalFileReader.getPurchase(), PurchaseHistory.class);
    }

}
