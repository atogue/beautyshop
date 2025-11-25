package tech.craftchain.beauty_shop.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.craftchain.beauty_shop.data.Address;
import tech.craftchain.beauty_shop.owner.Owner;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("shops")
public class Shop {
    @Id
    private String id;
    private String name;
    private String phone;
    private List<String> activities;
    private Address address;
    private Owner owner;
}
