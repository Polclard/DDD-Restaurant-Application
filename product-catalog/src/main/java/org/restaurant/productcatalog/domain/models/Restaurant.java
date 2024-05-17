package org.restaurant.productcatalog.domain.models;

import jakarta.persistence.*;
import org.restaurant.productcatalog.domain.valueObjects.Capacity;
import org.restaurant.sharedkernel.domain.base.AbstractEntity;
import org.restaurant.sharedkernel.domain.financial.Money;

@Entity
@Table(name = "restaurant")
public class Restaurant extends AbstractEntity<RestaurantId> {

    private String name;
    private String location;
    private int sales;
    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="price_amount")),
            @AttributeOverride(name="currency", column = @Column(name="price_currency"))
    })
    private Money price;

    public Restaurant()
    {
        super(RestaurantId.randomId(RestaurantId.class));
    }

    public static Restaurant build(String restaurantName,
                                   String restaurantLocation,
                                   Money price,
                                   int sales) {
        Restaurant p = new Restaurant();
        p.price = price;
        p.name = restaurantName;
        p.location = restaurantLocation;
//        p.capacity = capacity;
        p.sales = sales;
        return p;
    }

    public void addSales(int qty) {
        this.sales = this.sales - qty;
    }

    public void removeSales(int qty) {
        this.sales -= qty;
    }
}
