package org.restaurant.ordermanagement.domain.valueObjects;

import jakarta.persistence.Embeddable;
import org.restaurant.sharedkernel.domain.base.DomainObjectId;

@Embeddable
public class  RestaurantId extends DomainObjectId {
    public RestaurantId(String id) {
        super(id);
    }

    public RestaurantId() {
    }
}
