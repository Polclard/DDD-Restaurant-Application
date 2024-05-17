package org.restaurant.productcatalog.domain.models;

import org.restaurant.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class ClientId extends DomainObjectId {
    private ClientId() {
        super(RestaurantId.randomId(RestaurantId.class).getId());
    }

    public ClientId(@NonNull String uuid) {
        super(uuid);
    }

    public static ClientId of(String uuid) {
        ClientId p = new ClientId(uuid);
        return p;
    }
}
