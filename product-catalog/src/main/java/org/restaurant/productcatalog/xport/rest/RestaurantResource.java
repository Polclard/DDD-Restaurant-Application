package org.restaurant.productcatalog.xport.rest;

import lombok.AllArgsConstructor;
import org.restaurant.productcatalog.domain.models.Restaurant;
import org.restaurant.productcatalog.services.RestaurantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@AllArgsConstructor
public class RestaurantResource {
    private final RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getAll() {
        return restaurantService.getAll();
    }

}
