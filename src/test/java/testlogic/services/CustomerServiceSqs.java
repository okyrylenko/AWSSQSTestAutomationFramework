package testlogic.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import core.interfaces.ICustomerServiceSqs;
import core.interfaces.ISqsCustomer;
import core.models.CustomerToReceive;
import core.models.CustomerToSend;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import javax.inject.Inject;
import java.io.IOException;

public class CustomerServiceSqs implements ICustomerServiceSqs {

    @Inject
    ISqsCustomer customer;

    public SendMessageResponse sendCustomer(CustomerToSend customer) throws JsonProcessingException {
        return this.customer.add(customer);
    }

    public CustomerToReceive find(int id) throws IOException {
        return this.customer.find(id);
    }
}
