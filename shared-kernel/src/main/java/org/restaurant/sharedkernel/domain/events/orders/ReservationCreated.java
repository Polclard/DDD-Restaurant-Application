package org.restaurant.sharedkernel.domain.events.orders;
import org.restaurant.sharedkernel.domain.config.TopicHolder;
import lombok.Getter;
import org.restaurant.sharedkernel.domain.events.DomainEvent;

@Getter
public class ReservationCreated extends DomainEvent {
    private String restaurantId;
    private int quantity;

    public ReservationCreated(String topic) {
        super(TopicHolder.TOPIC_RESERVATION_CREATED);
    }

    public ReservationCreated(String restaurantId, int quantity) {
        super(TopicHolder.TOPIC_RESERVATION_CREATED);
        this.restaurantId = restaurantId;
        this.quantity = quantity;
    }
}
