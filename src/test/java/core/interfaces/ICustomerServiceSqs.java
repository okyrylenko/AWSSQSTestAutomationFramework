package core.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import core.models.CustomerToReceive;
import core.models.CustomerToSend;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import java.io.IOException;

public interface ICustomerServiceSqs {
    SendMessageResponse sendCustomer(CustomerToSend customer) throws JsonProcessingException;

    CustomerToReceive find(int id) throws IOException;
}
