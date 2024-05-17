package org.restaurant.productcatalog.services;

import org.restaurant.productcatalog.domain.models.Restaurant;
import org.restaurant.productcatalog.domain.models.RestaurantId;
import org.restaurant.productcatalog.services.form.RestaurantForm;

import java.util.List;

public interface RestaurantService {
    Restaurant findById(RestaurantId id);
    Restaurant createRestaurant(RestaurantForm form);
    Restaurant reservationCreated(RestaurantId restaurantId, int quantity);
    Restaurant reservationRemoved(RestaurantId restaurantId, int quantity);
    List<Restaurant> getAll();

}
