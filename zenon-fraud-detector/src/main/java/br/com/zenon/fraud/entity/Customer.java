package br.com.zenon.fraud.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public record Customer(String name, BigDecimal oldBalance, BigDecimal newBalance) {
    public Customer {
        Objects.requireNonNull(name);
        Objects.requireNonNull(oldBalance);
        Objects.requireNonNull(newBalance);

        if (oldBalance.signum() < 0) throw new IllegalArgumentException("Customer old balance amount cannot negative");
        if (newBalance.signum() < 0) throw new IllegalArgumentException("Customer old balance amount cannot negative");
    }

    public static Customer from(List<String> rawData) {
        return new Customer(rawData.getFirst().trim(), new BigDecimal(rawData.get(1).trim()), new BigDecimal(rawData.get(2).trim()));
    }
}
