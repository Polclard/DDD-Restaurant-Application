package org.restaurant.ordermanagement.services.forms;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.restaurant.sharedkernel.domain.financial.Currency;
import javax.validation.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderForm {
    @NotNull
    private Currency currency;

    @Valid
    @NotEmpty
    private List<ReservationForm> items = new ArrayList<>();

}
