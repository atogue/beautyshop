package tech.craftchain.beauty_shop.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.craftchain.beauty_shop.data.Address;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("clients")
public class Client {
    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private Address address;
    private List<String> shopNames;
}
