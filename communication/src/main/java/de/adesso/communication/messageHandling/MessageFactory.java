package de.adesso.communication.messageHandling;

import org.json.JSONObject;


public interface MessageFactory {

    Message fromJson(JSONObject jsonMessage);
    boolean supports(JSONObject jsonMessage);

    default boolean isDefault(){
        return false;
    }
}
