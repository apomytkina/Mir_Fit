package com.mirfit.mirfit.services;

import com.mirfit.mirfit.models.Receipt;
import com.mirfit.mirfit.models.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BonusesService {
    double getNumberOfBonuses(Receipt receipt);

    String saveTransaction(Receipt receipt, String status);

    List<Transaction> getAllTransactions();
}
