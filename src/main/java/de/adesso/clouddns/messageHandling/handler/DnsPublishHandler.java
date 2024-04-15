package de.adesso.clouddns.messageHandling.handler;

import de.adesso.clouddns.messageHandling.message.DnsPublishMessage;
import de.adesso.clouddns.messageHandling.type.DnsMessageType;
import de.adesso.communication.messageHandling.Message;
import de.adesso.communication.messageHandling.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DnsPublishHandler implements MessageHandler {

    private final Map<String, String> dns;

    @Autowired
    public DnsPublishHandler(Map<String, String> dns) {
        this.dns = dns;
    }

    @Override
    public <T extends Message> void handle(T message) {
        if(supports(message)){
            DnsPublishMessage dnsPublishMessage = (DnsPublishMessage) message;
            dns.put(dnsPublishMessage.service(), dnsPublishMessage.topic());
        }
    }

    @Override
    public <T extends Message> boolean supports(T message) {
        return message.getMessageType().equals(DnsMessageType.DNS_PUBLISH.name());
    }
}
