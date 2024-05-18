package org.restaurant.ordermanagement.domain.valueObjects;

import jakarta.persistence.Embeddable;
import org.restaurant.sharedkernel.domain.base.DomainObjectId;

@Embeddable
public class ClientId extends DomainObjectId {
    public ClientId(String id) {
        super(id);
    }

    public ClientId() {
    }
}
