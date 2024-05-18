package org.restaurant.productcatalog.services.form;

import lombok.Data;
import org.restaurant.sharedkernel.domain.financial.Money;

@Data
public class ClientForm {
    private String clientName;
    private String clientContactInfo;
}
