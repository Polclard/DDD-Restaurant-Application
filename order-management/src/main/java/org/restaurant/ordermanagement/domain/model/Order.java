package org.restaurant.ordermanagement.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.restaurant.ordermanagement.domain.model.enumerated.Status;
import org.restaurant.ordermanagement.domain.valueObjects.Restaurant;
import org.restaurant.sharedkernel.domain.base.AbstractEntity;
import org.restaurant.sharedkernel.domain.financial.Currency;
import org.restaurant.sharedkernel.domain.financial.Money;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
public class Order extends AbstractEntity<OrderId> {

    private Instant orderedOn;

    @Enumerated(value = EnumType.STRING)
    private Status orderState;

    private Money total;

    @Column(name="order_currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Reservation> reservationsList = new HashSet<>();

    protected Order() {
        super(OrderId.randomId(OrderId.class));
    }
    public Order(Instant now, Currency currency) {
        super(OrderId.randomId(OrderId.class));
        this.orderedOn = now;
        this.currency = currency;
    }

    public Money total() {
        return reservationsList.stream().map(Reservation::subtotal).reduce(new Money(currency, 0), Money::add);
    }

    public Reservation addItem(@NonNull Restaurant restaurant,
                               int qty,
                               int numberOfClients,
                               LocalDateTime reservationDate) {
        Objects.requireNonNull(restaurant,"Reservation must not be null");
        var item = new Reservation(restaurant.getRestaurantId(),
                restaurant.getPrice(),
                qty,
                numberOfClients,
                reservationDate);
        reservationsList.add(item);
        return item;
    }

    public void removeItem(@NonNull ReservationId reservationId) {
        Objects.requireNonNull(reservationId,"Reservation Item Id must not be null");
        reservationsList.removeIf(v->v.getRestaurantId().equals(reservationId));
    }
}
