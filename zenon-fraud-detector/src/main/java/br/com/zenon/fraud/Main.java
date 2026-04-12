package br.com.zenon.fraud;

import br.com.zenon.fraud.entity.Customer;
import br.com.zenon.fraud.entity.Transaction;
import br.com.zenon.fraud.entity.TransactionType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static br.com.zenon.fraud.entity.TransactionType.CASH_OUT;

public class Main {
    static void main() {
        List<Transaction> transactions = createDummyData();

        var transactionCounter = new AtomicInteger(0);
        transactions.forEach(t -> {
            System.out.println("Transação " + transactionCounter.incrementAndGet());
            t.printTransaction();
            System.out.println();
        });
    }

    static List<Transaction> createDummyData() {
        var customerOrigin1 = new Customer(
                "C1231006815",
                BigDecimal.valueOf(170136.0),
                BigDecimal.valueOf(160296.36)
        );

        var customerOrigin2 = new Customer(
                "C1280323807",
                BigDecimal.valueOf(850002.52),
                BigDecimal.valueOf(0.0)
        );

        var customerDest1 = new Customer(
                "M1979787155",
                BigDecimal.valueOf(0.0),
                BigDecimal.valueOf(0.0)
        );

        var customerDest2 = new Customer(
                "C873221189",
                BigDecimal.valueOf(6510099.11),
                BigDecimal.valueOf(7360101.63)
        );

        var transactions = new ArrayList<Transaction>() {{
            add(new Transaction(1, TransactionType.PAYMENT, BigDecimal.valueOf(9839.64), customerOrigin1,
                    customerDest1, false, false));
            add(new Transaction(743, CASH_OUT, BigDecimal.valueOf(850002.52), customerOrigin2,
                    customerDest2, true, false));
        }};

        return transactions;
    }
}
