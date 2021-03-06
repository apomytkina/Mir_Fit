package com.mirfit.mirfit.rowmappers;

import com.mirfit.mirfit.models.Bonuses;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class BonusesRowMapper implements RowMapper<Bonuses> {
    @Override
    public Bonuses mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Bonuses(
                UUID.fromString(resultSet.getString("id")),
                resultSet.getDouble("number_of_bonuses")
        );
    }
}
