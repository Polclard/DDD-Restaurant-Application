package org.restaurant.sharedkernel.infra;

import org.restaurant.sharedkernel.domain.events.DomainEvent;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
