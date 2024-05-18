package org.restaurant.productcatalog.config;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.restaurant.productcatalog.domain.models.Client;
import org.restaurant.productcatalog.domain.models.Restaurant;
import org.restaurant.productcatalog.domain.repository.ClientRepository;
import org.restaurant.productcatalog.domain.repository.RestaurantRepository;
import org.restaurant.sharedkernel.domain.financial.Currency;
import org.restaurant.sharedkernel.domain.financial.Money;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataIntializer {
    private final RestaurantRepository restaurantRepository;
    private final ClientRepository clientRepository;

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

        Client client1 = Client.build("John Doe", "Skopje");
        Client client2 = Client.build("Jane Moe", "Stutgard");

        if(clientRepository.findAll().isEmpty())
        {
            clientRepository.saveAll(Arrays.asList(client1, client2));
        }
    }

}
