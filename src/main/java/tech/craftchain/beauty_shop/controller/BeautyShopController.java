package tech.craftchain.beauty_shop.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
import tech.craftchain.beauty_shop.service.ImcService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "data", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class BeautyShopController {
    private BeautyShopService beautyShopService;
    private ImcService imcService;

    @GetMapping("/v1/users/{type}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<UserInfo> getAllUsersByType(@Valid @Parameter(in = ParameterIn.PATH, schema = @Schema(allowableValues = {"CLIENT", "EMPLOYEE", "OWNER"})) @PathVariable String type) {
        return switch (UserType.getType(type.toUpperCase())) {
            case CLIENT -> beautyShopService.findAllClients();
            case EMPLOYEE -> beautyShopService.findAllEmployees();
            case OWNER -> beautyShopService.findAllOwners();
        };
    }

    @PostMapping("/v1/users/{type}")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Secured("USER")
    public void createUserByType(@RequestBody UserInfo info, @Valid @Parameter(in = ParameterIn.PATH, schema = @Schema(allowableValues = {"CLIENT", "EMPLOYEE", "OWNER"})) @PathVariable String type) {
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
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createShop(@RequestBody ShopInfo info) {
        beautyShopService.createShop(info);
    }

    @GetMapping("/v1/imc/{weight}/{height}")
    @ResponseStatus(code = HttpStatus.OK)
    public Map<Double, String> findImcInfoByWeightAndHeight(@Parameter(description = "User's weight in KG") @PathVariable double weight,
                                                        @Parameter(description = "User's height in X.YZ meters") @PathVariable double height) {
        return imcService.findImcInfo(weight, height);
    }

}
