package br.com.zenon.fraud.entity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TransactionListRepository implements TransactionRepository {
    private final List<Transaction> transactionList;

    public TransactionListRepository(List<Transaction> transactionList) {
        Objects.requireNonNull(transactionList);

        this.transactionList = transactionList;
    }

    @Override
    public Optional<Transaction> findTransactionByCustomerName(String name) {
        return transactionList.stream()
                .filter(t -> name.equals(t.origin().name()))
                .findFirst();
    }
}
