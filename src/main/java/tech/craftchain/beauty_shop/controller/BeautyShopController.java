package tech.craftchain.beauty_shop.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tech.craftchain.beauty_shop.data.ShopInfo;
import tech.craftchain.beauty_shop.data.UserInfo;
import tech.craftchain.beauty_shop.data.UserType;
import tech.craftchain.beauty_shop.service.BeautyShopService;

import java.util.List;

@RestController
@RequestMapping(path = "data")
@AllArgsConstructor
public class BeautyShopController {
    private BeautyShopService beautyShopService;

    @GetMapping("/v1/users/{type}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<UserInfo> getAllUsersByType(@PathVariable String type) {
        return switch (UserType.getType(type.toUpperCase())) {
            case CLIENT -> beautyShopService.findAllClients();
            case EMPLOYEE -> beautyShopService.findAllEmployees();
            case OWNER -> beautyShopService.findAllOwners();
        };
    }

    @PostMapping("/v1/users/{type}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createUserByType(@RequestBody UserInfo info, @PathVariable String type) {
        switch (UserType.getType(type.toUpperCase())) {
            case CLIENT -> {
                info.setUserType(UserType.CLIENT);
                beautyShopService.createClient(info);
            }
            case EMPLOYEE -> {
                info.setUserType(UserType.EMPLOYEE);
                beautyShopService.createEmployee(info);
            }
            case OWNER -> {
                info.setUserType(UserType.OWNER);
                beautyShopService.createOwner(info);
            }
        }
    }

    @GetMapping("/v1/shops")
    @ResponseStatus(code = HttpStatus.OK)
    public List<ShopInfo> getAllShops() {
        return beautyShopService.findAllShopInfos();
    }

    @PostMapping("/v1/shops")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createShop(@RequestBody ShopInfo info) {
        beautyShopService.createShop(info);
    }

}
