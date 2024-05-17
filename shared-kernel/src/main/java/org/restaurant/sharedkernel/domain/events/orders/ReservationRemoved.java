package org.restaurant.sharedkernel.domain.events.orders;

import lombok.Getter;
import org.restaurant.sharedkernel.domain.events.DomainEvent;
import org.restaurant.sharedkernel.domain.config.TopicHolder;

@Getter
public class ReservationRemoved extends DomainEvent {
    private String restaurantId;
    private int quantity;

    public ReservationRemoved(String topic) {
        super(TopicHolder.TOPIC_RESERVATION_REMOVED);
    }

    public ReservationRemoved(String topic, String restaurantId, int quantity) {
        super(TopicHolder.TOPIC_RESERVATION_REMOVED);
        this.restaurantId = restaurantId;
        this.quantity = quantity;
    }

}
