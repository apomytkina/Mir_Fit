package com.mirfit.mirfit.repositories;

import com.mirfit.mirfit.models.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository {
    String add(Transaction transaction);

    String update(String status, String transactionNumber);

    List<Transaction> getAll();

    Transaction getByNumber(String transactionNumber);
}
