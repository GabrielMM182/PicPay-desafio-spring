package com.picpayVersaoIncial.picpayVersaoIncial.dtos;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long senderId, Long receiverId) {
}
