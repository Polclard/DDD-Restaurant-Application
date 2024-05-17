package org.restaurant.ordermanagement.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.restaurant.ordermanagement.domain.model.enumerated.Status;
import org.restaurant.ordermanagement.domain.valueObjects.RestaurantId;
import org.restaurant.sharedkernel.domain.base.AbstractEntity;
import org.restaurant.sharedkernel.domain.base.DomainObjectId;
import org.restaurant.sharedkernel.domain.financial.Money;
import org.springframework.cglib.core.Local;
import org.springframework.lang.NonNull;

import javax.annotation.processing.Generated;
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
    @AttributeOverride(name="id", column = @Column(name="product_id", nullable = false))
    private RestaurantId restaurantId;

    protected Reservation() {
        super(ReservationId.randomId(ReservationId.class));
    }

    public Reservation(@NonNull RestaurantId restaurantId,
                       @NonNull Money itemPrice,
                       int qty,
                       int numberOfClients,
                       LocalDateTime reservationDate) {
        super(DomainObjectId.randomId(ReservationId.class));
        this.restaurantId = restaurantId;
        this.reservationPrice = itemPrice;
        this.quantity = qty;
        this.numberOfClients = numberOfClients;
        this.reservationDate = reservationDate;
    }

    public Money subtotal() {
        return reservationPrice.multiply(quantity);
    }

}
