package org.restaurant.productcatalog.domain.models;

import org.restaurant.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class RestaurantId extends DomainObjectId {
    private RestaurantId() {
        super(RestaurantId.randomId(RestaurantId.class).getId());
    }

    public RestaurantId(@NonNull String uuid) {
        super(uuid);
    }

    public static RestaurantId of(String uuid) {
        RestaurantId p = new RestaurantId(uuid);
        return p;
    }

}
