package org.restaurant.ordermanagement.services;

import org.restaurant.ordermanagement.domain.exceptions.OrderIdNotExistException;
import org.restaurant.ordermanagement.domain.exceptions.OrderItemIdNotExistException;
import org.restaurant.ordermanagement.domain.model.Order;
import org.restaurant.ordermanagement.domain.model.OrderId;
import org.restaurant.ordermanagement.domain.model.ReservationId;
import org.restaurant.ordermanagement.services.forms.OrderForm;
import org.restaurant.ordermanagement.services.forms.ReservationForm;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderId makeReservation(OrderForm orderForm);
    List<Order> findAll();
    Optional<Order> findById(OrderId id) throws OrderIdNotExistException;
    void addItem(OrderId orderId, ReservationForm reservationForm) throws OrderIdNotExistException;
    void deleteItem(OrderId orderId, ReservationId reservationId) throws OrderIdNotExistException, OrderItemIdNotExistException;

}
