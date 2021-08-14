package dataaccess.clients;
import core.interfaces.ISqsClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

public class SQSClient implements ISqsClient {

    private SqsClient sqsClient;
    public SQSClient(Region region){
        sqsClient = SqsClient.builder()
                .region(region).build();
    }

    public SendMessageResponse sendMessage(SendMessageRequest sendMessageRequest){
        return sqsClient.sendMessage(sendMessageRequest);
    }

    public ReceiveMessageResponse getMessages(ReceiveMessageRequest request){
        return sqsClient.receiveMessage(request);
    }

    public DeleteMessageResponse deleteMessage(DeleteMessageRequest request){
        return sqsClient.deleteMessage(request);
    }
}
