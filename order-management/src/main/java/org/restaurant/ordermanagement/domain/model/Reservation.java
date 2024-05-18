package org.restaurant.ordermanagement.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.restaurant.ordermanagement.domain.valueObjects.ClientId;
import org.restaurant.ordermanagement.domain.valueObjects.RestaurantId;
import org.restaurant.sharedkernel.domain.base.AbstractEntity;
import org.restaurant.sharedkernel.domain.base.DomainObjectId;
import org.restaurant.sharedkernel.domain.financial.Money;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

@Entity
@Table(name="reservation")
@Getter
public class Reservation extends AbstractEntity<ReservationId> {
    private Money reservationPrice;
    @Column(name = "qty", nullable = false)
    private int quantity;
    private int numberOfClients;
    private LocalDateTime reservationDate;
    @AttributeOverride(name="id", column = @Column(name="restaurant_id", nullable = false))
    private RestaurantId restaurantId;
    @AttributeOverride(name="id", column = @Column(name="client_id", nullable = false))
    private ClientId clientId;

    protected Reservation() {
        super(ReservationId.randomId(ReservationId.class));
    }

    public Reservation(@NonNull RestaurantId restaurantId,
                       @NonNull Money itemPrice,
                       int qty,
                       int numberOfClients,
                       LocalDateTime reservationDate,
                       @NonNull ClientId clientId) {
        super(DomainObjectId.randomId(ReservationId.class));
        this.restaurantId = restaurantId;
        this.reservationPrice = itemPrice;
        this.quantity = qty;
        this.numberOfClients = numberOfClients;
        this.reservationDate = reservationDate;
        this.clientId = clientId;
    }

    public Money subtotal() {
        return reservationPrice.multiply(quantity);
    }

}
