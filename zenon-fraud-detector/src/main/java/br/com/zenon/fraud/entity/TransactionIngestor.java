package br.com.zenon.fraud.entity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionIngestor {
    private final String filename;

    public TransactionIngestor(String filename) {
        this.filename = filename;
    }

    public List<Transaction> load() {
        List<Transaction> transactions = new ArrayList<>();
        var path = Path.of(filename);
        try {
            int MAX_LINES = 50000;
            transactions = Files.readAllLines(path)
                    .stream()
                    .skip(1)
                    .limit(MAX_LINES)
                    .map(Transaction::from)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Arquivo não encontrado");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return transactions;
    }
}
