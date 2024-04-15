package de.adesso.clouddns.messageHandling;

import de.adesso.communication.messaging.UniversalSubscriber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    private final UniversalSubscriber subscriber;

    @Autowired
    public SubscriptionService(UniversalSubscriber subscriber) {
        this.subscriber = subscriber;
    }

    @PostConstruct
    public void init(){
        subscriber.subscribe("cloud-dns");
    }
}
