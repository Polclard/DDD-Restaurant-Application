package org.restaurant.ordermanagement.services.forms;

import lombok.Data;
import org.restaurant.ordermanagement.domain.valueObjects.Client;
import org.restaurant.ordermanagement.domain.valueObjects.Restaurant;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class ReservationForm {
    @NotNull
    private Restaurant restaurant;

    @NotNull
    private Client client;

    @Min(1)
    private int quantity = 1;
    @Min(1)
    private int numberOfClients = 1;
    private LocalDateTime reservationDate = LocalDateTime.now();


}
