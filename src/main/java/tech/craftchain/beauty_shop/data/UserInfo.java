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
public class UserInfo {
    private String name;
    private String email;
    private String phone;
    private Address address;
    private UserType userType;
    private List<ShopInfo> shops;
}
