package core.helpers;

import software.amazon.awssdk.services.sqs.model.MessageAttributeValue;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class SQSMessageAttributeBuilder {

    private Map<String, MessageAttributeValue> messageAttributes;

    public SQSMessageAttributeBuilder(){
        messageAttributes = new HashMap<>();
    }
    public SQSMessageAttributeBuilder add(String name, String value, String dataType){
        messageAttributes.put(name, MessageAttributeValue.builder().dataType(dataType).stringValue(value).build());
        return this;
    }

    public Map<String, MessageAttributeValue> getAttributes(){
        return this.messageAttributes;
    }
}
