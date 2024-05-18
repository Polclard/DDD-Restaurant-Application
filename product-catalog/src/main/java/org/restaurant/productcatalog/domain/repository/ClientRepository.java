package org.restaurant.productcatalog.domain.repository;

import org.restaurant.productcatalog.domain.models.Client;
import org.restaurant.productcatalog.domain.models.ClientId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, ClientId> {
}
