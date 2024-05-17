package org.restaurant.productcatalog.config;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.restaurant.productcatalog.domain.models.Restaurant;
import org.restaurant.productcatalog.domain.repository.RestaurantRepository;
import org.restaurant.sharedkernel.domain.financial.Currency;
import org.restaurant.sharedkernel.domain.financial.Money;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataIntializer {
    private final RestaurantRepository restaurantRepository;

    @PostConstruct
    @Transactional
    public void initData() {
        Restaurant p1 = Restaurant.build("Aledar",
                "Kocani",
                Money.valueOf(Currency.MKD, 500),
                10);
        Restaurant p2 = Restaurant.build("Equilibrium",
                "Skopje",
                Money.valueOf(Currency.MKD, 1200),
                5);
        if (restaurantRepository.findAll().isEmpty()) {
            restaurantRepository.saveAll(Arrays.asList(p1,p2));
        }
    }

}
