package org.restaurant.productcatalog.xport.events;

import lombok.AllArgsConstructor;
import org.restaurant.productcatalog.domain.models.RestaurantId;
import org.restaurant.productcatalog.services.RestaurantService;
import org.restaurant.sharedkernel.domain.config.TopicHolder;
import org.restaurant.sharedkernel.domain.events.DomainEvent;
import org.restaurant.sharedkernel.domain.events.orders.ReservationCreated;
import org.restaurant.sharedkernel.domain.events.orders.ReservationRemoved;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestaurantEventListener {
    private final RestaurantService restaurantService;

    @KafkaListener(topics= TopicHolder.TOPIC_RESERVATION_CREATED, groupId = "restaurantCatalog")
    public void consumeReservationCreatedEvent(String jsonMessage) {
        try {
            ReservationCreated event = DomainEvent.fromJson(jsonMessage,ReservationCreated.class);
            restaurantService.reservationCreated(RestaurantId.of(event.getRestaurantId()), event.getQuantity());
        } catch (Exception e){

        }

    }

    @KafkaListener(topics= TopicHolder.TOPIC_RESERVATION_REMOVED, groupId = "restaurantCatalog")
    public void consumeReservationRemovedEvent(String jsonMessage) {
        try {
            ReservationRemoved event = DomainEvent.fromJson(jsonMessage, ReservationRemoved.class);
            restaurantService.reservationRemoved(RestaurantId.of(event.getRestaurantId()), event.getQuantity());
        } catch (Exception e){

        }

    }

}
