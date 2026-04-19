package br.com.zenon.fraud.entity;

import java.util.Optional;

public interface TransactionRepository {
    Optional<Transaction> findTransactionByCustomerName(String name);
}
