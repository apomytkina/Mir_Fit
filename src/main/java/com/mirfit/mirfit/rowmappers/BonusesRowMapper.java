package com.mirfit.mirfit.rowmappers;

import com.mirfit.mirfit.models.BonusesAccount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class BonusesRowMapper implements RowMapper<BonusesAccount> {
    @Override
    public BonusesAccount mapRow(ResultSet resultSet, int i) throws SQLException {
        return new BonusesAccount(
                resultSet.getLong("id"),
                resultSet.getDouble("number_of_bonuses"),
                UUID.fromString(resultSet.getString("user_id"))
                );
    }
}
