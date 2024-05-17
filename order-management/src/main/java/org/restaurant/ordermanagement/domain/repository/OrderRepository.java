package org.restaurant.ordermanagement.domain.repository;

import org.restaurant.ordermanagement.domain.model.Order;
import org.restaurant.ordermanagement.domain.model.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, OrderId> {
}
