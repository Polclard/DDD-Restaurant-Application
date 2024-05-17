package org.restaurant.ordermanagement.domain.model;

import lombok.Getter;
import org.restaurant.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class OrderId extends DomainObjectId {

    private OrderId() {
        super(OrderId.randomId(OrderId.class).getId());
    }

    public OrderId(@NonNull String uuid) {
        super(uuid);
    }
}
