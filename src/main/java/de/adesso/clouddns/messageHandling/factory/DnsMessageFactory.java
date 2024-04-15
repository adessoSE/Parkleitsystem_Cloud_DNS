package de.adesso.clouddns.messageHandling.factory;

import de.adesso.clouddns.messageHandling.message.DnsPublishMessage;
import de.adesso.clouddns.messageHandling.type.DnsMessageType;
import de.adesso.clouddns.messageHandling.message.DnsRequestMessage;
import de.adesso.communication.messageHandling.Message;
import de.adesso.communication.messageHandling.MessageFactory;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DnsMessageFactory implements MessageFactory {
    @Override
    public Message fromJson(JSONObject jsonMessage) {
        DnsMessageType messageType = DnsMessageType.valueOf(jsonMessage.getString("messageType").toUpperCase());
        return switch(messageType){
            case DNS_REQUEST -> {
                String request = jsonMessage.getString("request");
                String source = jsonMessage.getString("source");
                String messageId = jsonMessage.getString("messageId");
                yield new DnsRequestMessage(request, source, messageId);
            }
            case DNS_PUBLISH -> {
                String topic = jsonMessage.getString("topic");
                String service = jsonMessage.getString("service");
                yield new DnsPublishMessage(service, topic);
            }
        };
    }

    @Override
    public boolean supports(JSONObject jsonMessage) {
        try {
            return jsonMessage.has("messageType") &&
                    Arrays.stream(DnsMessageType.values()).toList().contains(
                            DnsMessageType.valueOf(jsonMessage.getString("messageType").toUpperCase()));
        }
        catch (IllegalArgumentException e){
            return false;
        }
    }
}
