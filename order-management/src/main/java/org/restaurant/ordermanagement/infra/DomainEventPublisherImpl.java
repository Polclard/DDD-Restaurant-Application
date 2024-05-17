package org.restaurant.ordermanagement.infra;

import lombok.AllArgsConstructor;
import org.restaurant.sharedkernel.domain.events.DomainEvent;
import org.restaurant.sharedkernel.infra.DomainEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DomainEventPublisherImpl implements DomainEventPublisher {
    private final KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public void publish(DomainEvent event) {
        this.kafkaTemplate.send(event.topic(),event.toJson());
    }
}
