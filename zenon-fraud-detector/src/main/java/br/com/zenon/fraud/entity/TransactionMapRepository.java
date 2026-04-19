package br.com.zenon.fraud.entity;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransactionMapRepository implements TransactionRepository {
    private Map<String, Transaction> transactionMap;

    public TransactionMapRepository(List<Transaction> transactionList) {
        Objects.requireNonNull(transactionList);

        transactionMap = transactionList.stream()
                .collect(Collectors.toMap(t -> t.origin().name(), Function.identity()));
    }

    @Override
    public Optional<Transaction> findTransactionByCustomerName(String name) {
        return Optional.ofNullable(transactionMap.get(name));
    }
}
