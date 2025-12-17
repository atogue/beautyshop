package tech.craftchain.beauty_shop.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.craftchain.beauty_shop.client.Client;
import tech.craftchain.beauty_shop.client.ClientRepository;
import tech.craftchain.beauty_shop.data.ShopInfo;
import tech.craftchain.beauty_shop.data.UserInfo;
import tech.craftchain.beauty_shop.data.UserType;
import tech.craftchain.beauty_shop.employee.Employee;
import tech.craftchain.beauty_shop.employee.EmployeeRepository;
import tech.craftchain.beauty_shop.owner.Owner;
import tech.craftchain.beauty_shop.owner.OwnerRepository;
import tech.craftchain.beauty_shop.store.Shop;
import tech.craftchain.beauty_shop.store.ShopRepository;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class BeautyShopService {
    private ClientRepository clientRepository;
    private EmployeeRepository employeeRepository;
    private OwnerRepository ownerRepository;
    private ShopRepository shopRepository;


    public void createClient(UserInfo info) {
        var client = Client.builder()
                .name(info.getName()).email(info.getEmail()).phone(info.getPhone())
                .address(info.getAddress())
                .shopNames(info.getShops().stream().map(ShopInfo::getName).toList())
                .build();
        clientRepository.save(client);
        log.info("Client created : {}", client.getName());
    }

    public void createEmployee(UserInfo info) {
        var employee = Employee.builder()
                .name(info.getName()).email(info.getEmail()).phone(info.getPhone())
                .address(info.getAddress())
                .shopName(info.getShops().get(0).getName())//pick 1 item only
                .build();
        employeeRepository.save(employee);
        log.info("Employee created : {}", employee.getName());
    }

    public void createOwner(UserInfo info) {
        var owner = Owner.builder()
                .name(info.getName()).email(info.getEmail()).phone(info.getPhone())
                .address(info.getAddress())
                .shopNames(info.getShops().stream().map(ShopInfo::getName).toList())
                .build();
        ownerRepository.save(owner);
        log.info("Owner created : {}", owner.getName());
    }

    public void createShop(ShopInfo info) {
        var shop = Shop.builder()
                .name(info.getName())
                .phone(info.getPhone())
                .activities(info.getActivities()).address(info.getAddress())
                .owner(findOwnerByName(info.getOwnerName()))
                .build();
        shopRepository.save(shop);
        log.info("Shop created : {}", shop.getName());
    }

    public List<UserInfo> findAllClients() {
        log.info("Find all clients");
        return clientRepository.findAll().stream().map(this::mappingClient).toList();
    }

    public List<UserInfo> findAllEmployees() {
        log.info("Find all employees");
        return employeeRepository.findAll().stream().map(this::mappingEmployee).toList();
    }

    public List<UserInfo> findAllOwners() {
        log.info("Find all owners");
        return ownerRepository.findAll().stream().map(this::mappingOwner).toList();
    }

    public List<ShopInfo> findAllShopInfos() {
        log.info("Find all shop infos");
        return shopRepository.findAll().stream().map(this::mappingShop).toList();
    }

    private UserInfo mappingOwner(Owner owner) {
        return UserInfo.builder()
                .userType(UserType.OWNER)
                .name(owner.getName())
                .address(owner.getAddress())
                .shops(getShopInfosByNames(owner.getShopNames()))
                .build();
    }

    private UserInfo mappingClient(Client client) {
        return UserInfo.builder()
                .userType(UserType.CLIENT)
                .name(client.getName()).email(client.getEmail()).phone(client.getPhone())
                .address(client.getAddress())
                .shops(getShopInfosByNames(client.getShopNames()))
                .build();
    }

    private UserInfo mappingEmployee(Employee employee) {
        return UserInfo.builder()
                .userType(UserType.EMPLOYEE).name(employee.getName()).email(employee.getEmail()).phone(employee.getPhone())
                .address(employee.getAddress())
                .shops(List.of(mappingShop(findShopByName(employee.getShopName()))))
                .build();
    }

    private ShopInfo mappingShop(Shop shop) {
        return ShopInfo.builder()
                .name(shop.getName())
                .activities(shop.getActivities())
                .phone(shop.getPhone())
                .address(shop.getAddress())
                .ownerName(shop.getOwner() != null ? shop.getOwner().getName(): null)
                .build();
    }

    private List<ShopInfo> getShopInfosByNames(List<String> shopNames) {
        return shopNames.stream().filter(Objects::nonNull).map(shopName -> mappingShop(findShopByName(shopName))).toList();
    }

    private Shop findShopByName(String name) {
        return shopRepository.findByName(name).orElseThrow();
    }

    private Owner findOwnerByName(String name) {
        return ownerRepository.findByName(name).orElseThrow();
    }
}
