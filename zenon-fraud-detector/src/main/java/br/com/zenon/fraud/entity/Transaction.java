package br.com.zenon.fraud.entity;

import java.math.BigDecimal;

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
