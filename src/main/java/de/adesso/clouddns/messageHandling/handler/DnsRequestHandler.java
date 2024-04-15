package de.adesso.clouddns.messageHandling.handler;

import de.adesso.clouddns.messageHandling.type.DnsMessageType;
import de.adesso.clouddns.messageHandling.message.DnsRequestMessage;
import de.adesso.communication.cloud.CloudSender;
import de.adesso.communication.messageHandling.Message;
import de.adesso.communication.messageHandling.MessageHandler;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DnsRequestHandler implements MessageHandler {

    private final Map<String, String> dns;
    private final CloudSender cloudSender;

    @Autowired
    public DnsRequestHandler(Map<String, String> dns, CloudSender cloudSender) {
        this.dns = dns;
        this.cloudSender = cloudSender;
    }

    @Override
    public <T extends Message> void handle(T message) {
        if(supports(message)){
            DnsRequestMessage dnsRequestMessage = (DnsRequestMessage) message;
            JSONObject response = new JSONObject()
                    .put("messageType", "dns_response")
                    .put("response", dns.get(dnsRequestMessage.request()) != null ?
                            dns.get(dnsRequestMessage.request()) : dnsRequestMessage.request())
                    .put("request", dnsRequestMessage.request())
                    .put("messageId", dnsRequestMessage.messageId())
                    .put("domain", computeDomain(dnsRequestMessage.request()));
            cloudSender.send(dnsRequestMessage.source(), response);
        }
    }

    @Override
    public <T extends Message> boolean supports(T message) {
        return message.getMessageType().equals(DnsMessageType.DNS_REQUEST.name());
    }

    protected String computeDomain(String request){
        if(request.contains(".")){
            return "ip";
        }
        else return "mqtt";
    }
}
