package org.restaurant.ordermanagement.domain.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import lombok.Getter;
import org.restaurant.sharedkernel.domain.base.ValueObject;
import org.restaurant.sharedkernel.domain.financial.Currency;
import org.restaurant.sharedkernel.domain.financial.Money;

@Getter
public class Restaurant implements ValueObject {

    private RestaurantId restaurantId;
    private String name;
    private String location;
    private int sales;
    private Money price;

    public Restaurant()
    {
        this.restaurantId = RestaurantId.randomId(RestaurantId.class);
        this.name = "";
        this.location = "";
        this.sales = 0;
        this.price = Money.valueOf(Currency.MKD, 0);
    }

    @JsonCreator
    public Restaurant(@JsonProperty("id") RestaurantId id,
                      @JsonProperty("restaurantName") String name,
                      @JsonProperty("restaurantlocation") String location,
                      @JsonProperty("price") Money price,
                      @JsonProperty("sales") int sales) {
        this.restaurantId = id;
        this.name = name;
        this.location = location;
        this.price = price;
        this.sales = sales;
    }
}
