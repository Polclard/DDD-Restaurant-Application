package org.restaurant.productcatalog.domain.repository;

import org.restaurant.productcatalog.domain.models.Restaurant;
import org.restaurant.productcatalog.domain.models.RestaurantId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, RestaurantId> {
}
