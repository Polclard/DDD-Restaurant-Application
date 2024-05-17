package org.restaurant.ordermanagement.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.restaurant.ordermanagement.domain.exceptions.OrderIdNotExistException;
import org.restaurant.ordermanagement.domain.model.Order;
import org.restaurant.ordermanagement.domain.model.OrderId;
import org.restaurant.ordermanagement.domain.valueObjects.Restaurant;
import org.restaurant.ordermanagement.domain.valueObjects.RestaurantId;
import org.restaurant.ordermanagement.services.OrderService;
import org.restaurant.ordermanagement.services.forms.OrderForm;
import org.restaurant.ordermanagement.services.forms.ReservationForm;
import org.restaurant.ordermanagement.xport.client.RestaurantClient;
import org.restaurant.sharedkernel.domain.financial.Currency;
import org.restaurant.sharedkernel.domain.financial.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class OrderServiceImplTests {
    @Autowired
    private OrderService orderService;

    @Autowired
    private RestaurantClient restaurantClient;



    private static Restaurant newRestaurant(String name, String location, Money price, int sales) {
        Restaurant p = new Restaurant(RestaurantId.randomId(RestaurantId.class), name, location, price, sales);
        return p;
    }

    @Test
    public void testPlaceOrder() {

        ReservationForm oi1 = new ReservationForm();
        oi1.setRestaurant(newRestaurant("Amor",
                "Kocani",
                Money.valueOf(Currency.MKD, 1500),
                4));
        oi1.setQuantity(1);

        ReservationForm oi2 = new ReservationForm();
        oi2.setRestaurant(newRestaurant("Puze",
                "Sveti Nikole",
                Money.valueOf(Currency.MKD, 500),
                3));
        oi2.setQuantity(2);

        OrderForm orderForm = new OrderForm();
        orderForm.setCurrency(Currency.MKD);
        orderForm.setItems(Arrays.asList(oi1,oi2));

        OrderId newOrderId = orderService.makeReservation(orderForm);
        Order newOrder = orderService.findById(newOrderId).orElseThrow(OrderIdNotExistException::new);
        Assertions.assertEquals(newOrder.total(),Money.valueOf(Currency.MKD,2500));

    }

    @Test
    public void testPlaceOrderWithRealData() {
        List<Restaurant> productList = restaurantClient.findAll();
        Restaurant p1 = productList.get(0);
        Restaurant p2 = productList.get(1);

        ReservationForm oi1 = new ReservationForm();
        oi1.setRestaurant(p1);
        oi1.setQuantity(1);

        ReservationForm oi2 = new ReservationForm();
        oi2.setRestaurant(p2);
        oi2.setQuantity(2);

        OrderForm orderForm = new OrderForm();
        orderForm.setCurrency(Currency.MKD);
        orderForm.setItems(Arrays.asList(oi1,oi2));

        OrderId newOrderId = orderService.makeReservation(orderForm);
        Order newOrder = orderService.findById(newOrderId).orElseThrow(OrderIdNotExistException::new);

        Money outMoney = p1.getPrice().multiply(oi1.getQuantity()).add(p2.getPrice().multiply(oi2.getQuantity()));
        Assertions.assertEquals(newOrder.total(),outMoney);
    }

}
