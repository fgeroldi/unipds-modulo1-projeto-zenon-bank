package br.com.zenon.fraud.entity;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FraudAnalyzer {
    private final List<Transaction> transactions;

    public FraudAnalyzer(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void analyze() {
        System.out.println("1. Total de Fraudes:");
        outputAllFrauds();

        List<BigDecimal> topThreeFrauds = findTopFrauds(3);
        System.out.println("2. Top 3 fraudes de maior valor:");
        topThreeFrauds.forEach(System.out::println);

        List<String> topFiveSuspiciousClients = findTopSuspiciousClients(5);
        System.out.println("3. Top 5 clientes suspeitos:");
        topFiveSuspiciousClients.forEach(System.out::println);

        BigDecimal totalFraudLoss = calculateTotalFraudLoss();
        System.out.println("4. Prejuizo total: " + totalFraudLoss);

        Map<TransactionType, Long> fraudsByType = countFraundsByType();
        System.out.println("5. Total de fraudes por tipo de transação: ");
        fraudsByType.forEach((k, v) -> System.out.printf("%s: %d\n", k, v));
    }

    private void outputAllFrauds() {
        transactions.stream()
                .filter(Transaction::isFraud)
                .forEach(System.out::println);
    }

    private List<BigDecimal> findTopFrauds(int limit) {
        return transactions.stream()
                .filter(Transaction::isFraud)
                .sorted(Comparator.comparing(Transaction::amount).reversed())
                .limit(limit)
                .map(Transaction::amount)
                .toList();
    }

    private List<String> findTopSuspiciousClients(int limit) {
        return transactions.stream()
                .filter(Transaction::isFraud)
                .sorted(Comparator.comparing(Transaction::amount).reversed())
                .map(t -> t.origin().name())
                .distinct()
                .limit(limit)
                .toList();
    }

    private BigDecimal calculateTotalFraudLoss() {
        return transactions.stream()
                .filter(Transaction::isFraud)
                .map(Transaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Map<TransactionType, Long> countFraundsByType() {
        return transactions.stream()
                .filter(Transaction::isFraud)
                .collect(Collectors.groupingBy(Transaction::type, Collectors.counting()));
    }
}
