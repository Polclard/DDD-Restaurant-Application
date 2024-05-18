package org.restaurant.productcatalog.services;

import org.restaurant.productcatalog.domain.models.Client;
import org.restaurant.productcatalog.domain.models.ClientId;
import org.restaurant.productcatalog.services.form.ClientForm;
import org.restaurant.productcatalog.services.form.RestaurantForm;

import java.util.List;

public interface ClientService {
    Client findById(ClientId id);
    Client createClient(ClientForm form);
    Client removeClient(ClientId id);
    List<Client> getAll();
}
