package org.restaurant.productcatalog.domain.valueObjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Capacity {
    private final int capacity;

    public Capacity(int capacity) {
        this.capacity = capacity;
    }

    protected Capacity() {
        this.capacity = 0;
    }
}
