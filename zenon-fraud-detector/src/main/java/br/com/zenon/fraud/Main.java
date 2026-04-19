package br.com.zenon.fraud;

import br.com.zenon.fraud.entity.*;

import java.util.List;
import java.util.Optional;

public class Main {
    static void main() {
        var ingestor = new TransactionIngestor("data/PS_20174392719_1491204439457_log.csv");
        List<Transaction> transactions = ingestor.load();
        var listRepository = new TransactionListRepository(transactions);
        var mapRepository = new TransactionMapRepository(transactions);

        Optional<Transaction> t1 = listRepository.findTransactionByCustomerName("C12345");
        t1.ifPresentOrElse(IO::println, () -> IO.println("Transação não encontrada para o cliente C12345"));

        Optional<Transaction> t2 = listRepository.findTransactionByCustomerName("C1231006815");
        t2.ifPresentOrElse(IO::println, () -> IO.println("Transação não encontrada para o cliente C1231006815"));

        long inicio, fim;

        inicio = System.nanoTime();
        Optional<Transaction> t3 = listRepository.findTransactionByCustomerName("C1868032458");
        fim = System.nanoTime();
        t3.ifPresentOrElse(IO::println, () -> IO.println("Transação não encontrada para o cliente C1868032458"));
        System.out.println("Completado em "+ (fim-inicio) / 1_000_000.0 + "ms");

        inicio = System.nanoTime();
        Optional<Transaction> t4 = mapRepository.findTransactionByCustomerName("C1868032458");
        fim = System.nanoTime();
        t4.ifPresentOrElse(IO::println, () -> IO.println("Transação não encontrada para o cliente C1868032458"));
        System.out.println("Completado em "+ (fim-inicio) / 1_000_000.0 + "ms");
    }
}
