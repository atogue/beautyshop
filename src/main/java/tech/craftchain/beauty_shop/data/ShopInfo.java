package tech.craftchain.beauty_shop.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopInfo {
    private String name;
    private List<String> activities;
    private String phone;
    private Address address;
    private String ownerName;
}
