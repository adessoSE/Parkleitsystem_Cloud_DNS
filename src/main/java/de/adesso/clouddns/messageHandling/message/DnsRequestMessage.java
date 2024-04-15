package de.adesso.clouddns.messageHandling.message;

import de.adesso.clouddns.messageHandling.type.DnsMessageType;
import de.adesso.communication.messageHandling.Message;

public record DnsRequestMessage(String request, String source, String messageId) implements Message {
    @Override
    public String getMessageType() {
        return DnsMessageType.DNS_REQUEST.name();
    }
}
