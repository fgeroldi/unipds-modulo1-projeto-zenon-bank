package br.com.zenon.fraud.entity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public record Transaction(
    Integer step,
    TransactionType type,
    BigDecimal amount,
    Customer origin,
    Customer destination,
    Boolean isFraud,
    Boolean isFlaggedAsFraud
) {
    public Transaction {
        Objects.requireNonNull(step);
        Objects.requireNonNull(type);
        Objects.requireNonNull(amount);
        Objects.requireNonNull(origin);
        Objects.requireNonNull(destination);
        Objects.requireNonNull(isFraud);
        Objects.requireNonNull(isFlaggedAsFraud);

        if (step < 0) throw new IllegalArgumentException("Step must be positive: " + step);
        if (amount.signum() < 0) throw new IllegalArgumentException("Customer old balance amount cannot negative");
    }

    public static Optional<Transaction> from(String rawTransaction) {
        Optional<Transaction> transaction = Optional.empty();

        try {
            List<String> transactionData = Arrays.stream(rawTransaction.split(",")).toList();
            var step = Integer.parseInt(transactionData.getFirst().trim());
            var type = TransactionType.valueOf(transactionData.get(1).trim());
            var amount = new BigDecimal(transactionData.get(2).trim());
            var isFraud = Boolean.valueOf(transactionData.get(9).trim().equals("1"));
            var isFlaggedAsFraud = Boolean.valueOf(transactionData.get(10).trim().equals("1"));

            List<String> originData = transactionData.subList(3,6);
            var origin = Customer.from(originData);

            List<String> destinationData = transactionData.subList(6,9);
            var destination = Customer.from(destinationData);

            transaction = Optional.of(new Transaction(step, type, amount, origin, destination, isFraud, isFlaggedAsFraud));
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + rawTransaction + " | " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + rawTransaction + " | " + e.getMessage());
        }
        return transaction;
    }

    public void printTransaction() {
        System.out.println("step:" + this.step());
        System.out.println("type:" + this.type());
        System.out.println("amount:" + this.amount());
        System.out.println("nameOrig:"+ this.origin().name());
        System.out.println("oldBalanceOrg:"+ this.origin().oldBalance());
        System.out.println("newBalanceOrig:"+ this.origin().newBalance());
        System.out.println("nameDest:"+ this.destination().name());
        System.out.println("oldBalanceDest:"+ this.destination().oldBalance());
        System.out.println("newBalanceDest:"+ this.destination().newBalance());
        System.out.println("isFraud:"+ (this.isFraud() ? 1 : 0));
        System.out.println("isFlaggedFraud:"+ (this.isFlaggedAsFraud() ? 1 : 0));
    }
}
