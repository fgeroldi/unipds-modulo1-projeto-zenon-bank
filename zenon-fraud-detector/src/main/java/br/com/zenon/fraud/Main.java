package br.com.zenon.fraud;

import br.com.zenon.fraud.entity.FraudAnalyzer;
import br.com.zenon.fraud.entity.Transaction;
import br.com.zenon.fraud.entity.TransactionIngestor;

import java.util.List;

public class Main {
    static void main() {
        var ingestor = new TransactionIngestor("data/PS_20174392719_1491204439457_log.csv");
        List<Transaction> transactions = ingestor.load();

        var analyzer = new FraudAnalyzer(transactions);
        analyzer.analyze();
    }
}
