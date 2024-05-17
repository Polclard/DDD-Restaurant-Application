package org.restaurant.productcatalog.services.form;

import lombok.Data;
import org.restaurant.sharedkernel.domain.financial.Money;

@Data
public class RestaurantForm {
    private String restaurantName;
    private String restaurantLocation;
    private Money price;
    private int sales;

}
