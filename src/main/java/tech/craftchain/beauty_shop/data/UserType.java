package tech.craftchain.beauty_shop.data;

public enum UserType {
    CLIENT("client"), EMPLOYEE("employee"), OWNER("owner");

    private final String type;

    UserType(String type) {
        this.type = type;
    }

    public static UserType getType(String type) {
        return UserType.valueOf(type);
    }
}
