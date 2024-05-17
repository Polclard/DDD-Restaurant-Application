package org.restaurant.productcatalog.services.impl;

import lombok.AllArgsConstructor;
import org.restaurant.productcatalog.domain.exceptions.RestaurantNotFoundException;
import org.restaurant.productcatalog.domain.models.Restaurant;
import org.restaurant.productcatalog.domain.models.RestaurantId;
import org.restaurant.productcatalog.domain.repository.RestaurantRepository;
import org.restaurant.productcatalog.services.RestaurantService;
import org.restaurant.productcatalog.services.form.RestaurantForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public Restaurant findById(RestaurantId id) {
        return restaurantRepository.findById(id).orElseThrow(RestaurantNotFoundException::new);
    }

    @Override
    public Restaurant createRestaurant(RestaurantForm form) {
        Restaurant p = Restaurant.build(form.getRestaurantName(),
                form.getRestaurantLocation(),
                form.getPrice(),
                form.getSales());
        restaurantRepository.save(p);
        return p;

    }

    @Override
    public Restaurant reservationCreated(RestaurantId restaurantId, int quantity) {
        Restaurant p = restaurantRepository.findById(restaurantId).orElseThrow(RestaurantNotFoundException::new);
        p.addSales(quantity);
        restaurantRepository.saveAndFlush(p);
        return p;

    }

    @Override
    public Restaurant reservationRemoved(RestaurantId restaurantId, int quantity) {
        Restaurant p = restaurantRepository.findById(restaurantId).orElseThrow(RestaurantNotFoundException::new);
        p.removeSales(quantity);
        restaurantRepository.saveAndFlush(p);
        return p;

    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }
}
