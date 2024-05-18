package org.restaurant.productcatalog.services.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.restaurant.productcatalog.domain.exceptions.ClientNotFoundException;
import org.restaurant.productcatalog.domain.exceptions.RestaurantNotFoundException;
import org.restaurant.productcatalog.domain.models.Client;
import org.restaurant.productcatalog.domain.models.ClientId;
import org.restaurant.productcatalog.domain.repository.ClientRepository;
import org.restaurant.productcatalog.services.ClientService;
import org.restaurant.productcatalog.services.form.ClientForm;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client findById(ClientId id) {
        return clientRepository.findById(id).orElseThrow(RestaurantNotFoundException::new);
    }

    @Override
    public Client createClient(@NonNull ClientForm form) {
        Client client = Client.build(form.getClientName(),form.getClientContactInfo());
        clientRepository.save(client);
        return client;
    }

    @Override
    public Client removeClient(ClientId id) {
        Client client = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        clientRepository.delete(client);
        return client;
    }

    @Override
    public List<Client> getAll() {
        return this.clientRepository.findAll();
    }
}
