package br.com.zenon.fraud.entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionIngestor {
    private String filename;
    private int MAX_LINES = 1000;

    public TransactionIngestor(String filename) {
        this.filename = filename;
    }

    public List<Transaction> load() {
        var transactions = new ArrayList<Transaction>();

        try (var inputStream = new FileInputStream(filename); var scanner = new Scanner(inputStream);) {
            int lineCounter = 0;
            while(scanner.hasNextLine() && lineCounter < MAX_LINES+1) {
                String line = scanner.nextLine();
                lineCounter++;

                if(lineCounter == 1)
                    continue;

                var transaction = Transaction.from(line);
                transactions.add(transaction);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Arquivo não encontrado");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return transactions;
    }
}
