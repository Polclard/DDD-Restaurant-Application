package org.restaurant.productcatalog.xport.rest;

import lombok.AllArgsConstructor;
import org.restaurant.productcatalog.domain.models.Client;
import org.restaurant.productcatalog.domain.models.Restaurant;
import org.restaurant.productcatalog.services.ClientService;
import org.restaurant.productcatalog.services.RestaurantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@AllArgsConstructor
public class ClientResource {
    private final ClientService clientService;

    @GetMapping
    public List<Client> getAll() {
        return clientService.getAll();
    }

}