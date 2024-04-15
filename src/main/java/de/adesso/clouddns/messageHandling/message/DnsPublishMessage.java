package de.adesso.clouddns.messageHandling.message;

import de.adesso.clouddns.messageHandling.type.DnsMessageType;
import de.adesso.communication.messageHandling.Message;

public record DnsPublishMessage(String service, String topic) implements Message {
    @Override
    public String getMessageType() {
        return DnsMessageType.DNS_PUBLISH.name();
    }
}
