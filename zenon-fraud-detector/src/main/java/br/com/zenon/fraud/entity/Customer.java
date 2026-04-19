package br.com.zenon.fraud.entity;

import java.math.BigDecimal;
import java.util.List;

public record Customer(String name, BigDecimal oldBalance, BigDecimal newBalance) {
    public static Customer from(List<String> rawData) {
        return new Customer(rawData.getFirst(), new BigDecimal(rawData.get(1)), new BigDecimal(rawData.get(2)));
    }
}
