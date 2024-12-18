package com.picpayVersaoIncial.picpayVersaoIncial.domain.user;

public enum UserType {
    COMMON("Usuário Comum"),
    MERCHANT("Comerciante");

    private final String description;

    UserType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
