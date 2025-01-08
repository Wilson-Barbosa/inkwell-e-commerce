package com.theinkwell.server.domains.user.enums;

public enum RoleEnum {
    ADMIN("ADMIN"),
    CUSTOMER("CUSTOMER");

    private final String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
