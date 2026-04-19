package br.com.zenon.fraud.entity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static br.com.zenon.fraud.entity.TransactionType.*;

public record Transaction(
    Integer step,
    TransactionType type,
    BigDecimal amount,
    Customer origin,
    Customer destination,
    Boolean isFraud,
    Boolean isFlaggedAsFraud
) {
    public static Transaction from(String rawTransaction) {
        List<String> transactionData = Arrays.stream(rawTransaction.split(",")).toList();
        var step = Integer.valueOf(transactionData.getFirst());
        var type = TransactionType.valueOf(transactionData.get(1));
        var amount = new BigDecimal(transactionData.get(2));
        var isFraud = Boolean.valueOf(transactionData.get(9).equals("1"));
        var isFlaggedAsFraud = Boolean.valueOf(transactionData.get(10).equals("1"));

        List<String> originData = transactionData.subList(3,6);
        var origin = Customer.from(originData);

        List<String> destinationData = transactionData.subList(6,9);
        var destination = Customer.from(destinationData);

        return new Transaction(step, type, amount, origin, destination, isFraud, isFlaggedAsFraud);
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
