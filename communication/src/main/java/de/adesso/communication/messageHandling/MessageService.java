package de.adesso.communication.messageHandling;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final List<MessageHandler> messageHandlers;
    private final List<MessageFactory> messageFactories;

    @Autowired
    public MessageService(List<MessageHandler> messageHandlers, List<MessageFactory> messageFactories) {
        this.messageHandlers = messageHandlers;
        this.messageFactories = messageFactories;
    }

    public void handle(JSONObject jsonMessage){
        MessageFactory messageFactory = findSupportingMessageFactory(jsonMessage);
        Message message = messageFactory.fromJson(jsonMessage);
        MessageHandler messageHandler = findSupportingMessageHandler(message);
        messageHandler.handle(message);
    }

    protected MessageFactory findSupportingMessageFactory(JSONObject jsonMessage){
        return messageFactories.stream().filter(messageFactory -> messageFactory.supports(jsonMessage) && !messageFactory.isDefault()).findFirst()
                .orElse(messageFactories.stream().filter(messageFactory -> messageFactory.supports(jsonMessage)).findFirst().orElseThrow());
    }

    protected MessageHandler findSupportingMessageHandler(Message message){
        return messageHandlers.stream().filter(messageHandler -> messageHandler.supports(message) && !messageHandler.isDefault()).findFirst()
                .orElse(messageHandlers.stream().filter(messageHandler -> messageHandler.supports(message)).findFirst().orElseThrow());
    }
}
