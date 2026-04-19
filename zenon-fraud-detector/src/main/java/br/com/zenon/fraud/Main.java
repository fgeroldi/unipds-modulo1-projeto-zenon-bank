package br.com.zenon.fraud;

import br.com.zenon.fraud.entity.Transaction;
import br.com.zenon.fraud.entity.TransactionIngestor;

import java.util.List;

public class Main {
    static void main() {
        var ingestor = new TransactionIngestor("data/paysim_with_bad_data.csv");
        List<Transaction> transactions = ingestor.load();

        for (int i = 0; i < 10; i++) {
            System.out.println(transactions.get(i));
        }
    }
}
