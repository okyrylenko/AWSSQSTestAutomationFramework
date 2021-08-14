package core.interfaces;

import software.amazon.awssdk.services.sqs.model.*;

public interface ISqsClient {
    SendMessageResponse sendMessage(SendMessageRequest sendMessageRequest);

    ReceiveMessageResponse getMessages(ReceiveMessageRequest request);

    DeleteMessageResponse deleteMessage(DeleteMessageRequest request);
}
