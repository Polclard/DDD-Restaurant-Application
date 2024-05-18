package org.restaurant.ordermanagement.services.impl;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.restaurant.ordermanagement.domain.exceptions.OrderIdNotExistException;
import org.restaurant.ordermanagement.domain.exceptions.OrderItemIdNotExistException;
import org.restaurant.ordermanagement.domain.model.Order;
import org.restaurant.ordermanagement.domain.model.OrderId;
import org.restaurant.ordermanagement.domain.model.ReservationId;
import org.restaurant.ordermanagement.domain.repository.OrderRepository;
import org.restaurant.ordermanagement.services.OrderService;
import org.restaurant.ordermanagement.services.forms.OrderForm;
import org.restaurant.ordermanagement.services.forms.ReservationForm;
import org.restaurant.sharedkernel.domain.events.orders.ReservationCreated;
//import org.restaurant.sharedkernel.infra.DomainEventPublisher;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import jakarta.validation.Validator;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
//    private final DomainEventPublisher domainEventPublisher;
    private final Validator validator;


    @Override
    public OrderId makeReservation(OrderForm orderForm) {
        Objects.requireNonNull(orderForm,"order must not be null.");
        var constraintViolations = validator.validate(orderForm);
        if (constraintViolations.size()>0) {
            throw new ConstraintViolationException("The order form is not valid", constraintViolations);
        }
        var newOrder = orderRepository.saveAndFlush(toDomainObject(orderForm));
//        newOrder.getReservationsList().forEach(item->domainEventPublisher.publish(new ReservationCreated(item.getRestaurantId().getId(),item.getQuantity())));
        return newOrder.getId();
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return orderRepository.findById(id);
    }

    @Override
    public void addItem(OrderId orderId, ReservationForm reservationForm) throws OrderIdNotExistException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.addItem(reservationForm.getRestaurant(),
                reservationForm.getQuantity(),
                reservationForm.getNumberOfClients(),
                reservationForm.getReservationDate(),
                reservationForm.getClient()
                );
        orderRepository.saveAndFlush(order);
//        domainEventPublisher.publish(new ReservationCreated(reservationForm.getRestaurant().getRestaurantId().getId(),reservationForm.getQuantity()));
    }

    @Override
    public void deleteItem(OrderId orderId, ReservationId reservationId) throws OrderIdNotExistException, OrderItemIdNotExistException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.removeItem(reservationId);
        orderRepository.saveAndFlush(order);
    }

    private Order toDomainObject(OrderForm orderForm) {
        var order = new Order(Instant.now(),orderForm.getCurrency());
        orderForm.getItems().forEach(item->order.addItem(item.getRestaurant(),
                item.getQuantity(),
                item.getNumberOfClients(),
                item.getReservationDate(),
                item.getClient()));
        return order;
    }

}
