package com.mirfit.mirfit.rowmappers;

import com.mirfit.mirfit.models.Transaction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionRowMapper implements RowMapper<Transaction> {
    @Override
    public Transaction mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Transaction(
                resultSet.getInt("id"),
                resultSet.getString("transaction_number"),
                resultSet.getDate("date"),
                resultSet.getTime("time"),
                resultSet.getDouble("bonuses"),
                resultSet.getString("status")
        );
    }
}

