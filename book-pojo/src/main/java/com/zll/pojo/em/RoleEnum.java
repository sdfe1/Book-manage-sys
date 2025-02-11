package com.zll.pojo.em;

public enum RoleEnum {
    USER("USER"),
    ADMIN("ADMIN");

    private final String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RoleEnum fromString(String value) {
        for (RoleEnum role : RoleEnum.values()) {
            if (role.value.equals(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + value);
    }
}
