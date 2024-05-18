package org.restaurant.ordermanagement.domain.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import org.restaurant.sharedkernel.domain.base.ValueObject;
import org.restaurant.sharedkernel.domain.financial.Currency;
import org.restaurant.sharedkernel.domain.financial.Money;

@Getter
public class Client implements ValueObject {
    private ClientId clientId;
    private String name;
    private String contactInfo;

    public Client()
    {
        this.clientId = ClientId.randomId(ClientId.class);
        this.name = "";
        this.contactInfo = "";
    }

    @JsonCreator
    public Client(@JsonProperty("id") ClientId id,
                      @JsonProperty("clientName") String name,
                  @JsonProperty("contactInfo") String contactInfo) {
        this.clientId = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }
}
