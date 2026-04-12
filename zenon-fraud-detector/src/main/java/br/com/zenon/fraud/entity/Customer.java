package br.com.zenon.fraud.entity;

import java.math.BigDecimal;

public record Customer(String name, BigDecimal oldBalance, BigDecimal newBalance) {
}
