package dataaccess.sqs;

import core.helpers.DateTimeHelper;
import core.helpers.SQSMessageAttributeBuilder;
import core.interfaces.ISqsCustomer;
import core.models.CustomerToSend;
import dataaccess.clients.SQSClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.helpers.Convert;
import core.models.CustomerToReceive;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.model.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqsCustomerRepository extends SQSClient implements ISqsCustomer {
    private final String QUEUEURL = "https://sqs.us-east-2.amazonaws.com/962312104812/Customers";
    private final String ATTRIBUTE = "Customer";

    public SqsCustomerRepository() {
        super(Region.US_EAST_2);
    }

    public SendMessageResponse add(CustomerToSend customer, String attribute) throws JsonProcessingException {
        String message = DateTimeHelper.convertToString(customer, new SimpleDateFormat("dd/M/yyyy"));

        Map<String, MessageAttributeValue> messageAttributes = new SQSMessageAttributeBuilder().add("Name", ATTRIBUTE, String.class.getTypeName()).getAttributes();

        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl(this.QUEUEURL)
                .messageBody(message)
                .messageAttributes(messageAttributes)
                .build();

        return super.sendMessage(sendMessageRequest);
    }

    public SendMessageResponse add(CustomerToSend customer) throws JsonProcessingException {
        return this.add(customer, ATTRIBUTE);
    }

    public CustomerToReceive find(int id) throws IOException {
        ReceiveMessageRequest request = ReceiveMessageRequest.builder()
                .queueUrl(this.QUEUEURL)
                .waitTimeSeconds(20)
                .build();

        List<Message> m = super.getMessages(request).messages();
        Message message = this.find(m, id);
        if(message==null){
            return null;
        }

        CustomerToReceive customer = Convert.stringToObject(message.body(), CustomerToReceive.class);
        this.deleteMessage(message);
        return customer;
    }

    private Message find(List<Message> messages, int id) throws JsonProcessingException {
        for (Message message : messages) {
            CustomerToReceive cus = Convert.stringToObject(message.body(), CustomerToReceive.class);
            if (cus.getId() == id) {
                return message;
            }
        }
        return null;
    }


    public DeleteMessageResponse deleteMessage(Message message) {
        DeleteMessageRequest request = DeleteMessageRequest.builder()
                .queueUrl(this.QUEUEURL)
                .receiptHandle(message.receiptHandle())
                .build();

        return super.deleteMessage(request);

    }
}
