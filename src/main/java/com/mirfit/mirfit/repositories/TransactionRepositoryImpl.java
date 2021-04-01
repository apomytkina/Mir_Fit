package com.mirfit.mirfit.repositories;

import com.mirfit.mirfit.models.Transaction;
import com.mirfit.mirfit.rowmappers.TransactionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TransactionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String add(Transaction transaction) {
        try {
            int count = jdbcTemplate.update(
                    "INSERT IGNORE INTO transaction (transaction_number, date, time, bonuses, status) VALUES (?, ?, ?, ?, ?)",
                    transaction.getTransactionNumber(),
                    transaction.getDate(),
                    transaction.getTime(),
                    transaction.getBonuses(),
                    transaction.getStatus()
            );

            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String update(Transaction transaction) {
        return null;
    }

    @Override
    public List<Transaction> getAll() {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM transaction",
                    new TransactionRowMapper()
            );
        } catch (Exception e) {
            return null;
        }
    }
}
