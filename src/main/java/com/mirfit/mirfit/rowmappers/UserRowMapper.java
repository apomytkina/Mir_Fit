package com.mirfit.mirfit.rowmappers;

import com.mirfit.mirfit.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        return new User(
                resultSet.getLong("id"),
                resultSet.getString("card_number"),
                resultSet.getString("first_name"),
                resultSet.getString("second_name"),
                resultSet.getString("patronymic"),
                resultSet.getString("password"),
                resultSet.getString("login"));
    }
}
