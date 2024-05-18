package org.restaurant.productcatalog.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import org.restaurant.sharedkernel.domain.base.AbstractEntity;

@Entity
@Table(name = "client")
@Getter
public class Client extends AbstractEntity<ClientId> {
    private String name;
    private String contactInfo;

    public Client()
    {
        super(ClientId.randomId(ClientId.class));
    }

    public Client(String name, String contactInfo)
    {
        super(ClientId.randomId(ClientId.class));
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public static Client build(String name, String contactInfo)
    {
        Client newClient = new Client();
        newClient.name = name;
        newClient.contactInfo = contactInfo;
        return newClient;
    }
}
