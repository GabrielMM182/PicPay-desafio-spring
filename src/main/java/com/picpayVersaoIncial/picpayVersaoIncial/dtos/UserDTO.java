package com.picpayVersaoIncial.picpayVersaoIncial.dtos;

import com.picpayVersaoIncial.picpayVersaoIncial.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstname, String lastname, String document, String email, String password, BigDecimal balance, UserType userType) {
}
