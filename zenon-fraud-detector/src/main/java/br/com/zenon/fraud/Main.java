package br.com.zenon.fraud;

import br.com.zenon.fraud.entity.Customer;
import br.com.zenon.fraud.entity.Transaction;
import br.com.zenon.fraud.entity.TransactionIngestor;
import br.com.zenon.fraud.entity.TransactionType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static br.com.zenon.fraud.entity.TransactionType.CASH_OUT;

public class Main {
    static void main() {
        var ingestor = new TransactionIngestor("data/PS_20174392719_1491204439457_log.csv");
        List<Transaction> transactions = ingestor.load();

        for (int i = 0; i < 10; i++) {
            System.out.println(transactions.get(i));
        }
    }
}
