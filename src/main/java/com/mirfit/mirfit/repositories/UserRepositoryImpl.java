package com.mirfit.mirfit.repositories;

import com.mirfit.mirfit.models.AddUserRequest;
import com.mirfit.mirfit.models.User;
import com.mirfit.mirfit.rowmappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getById(long id) {
        try {
            List<User> users = jdbcTemplate.query(
                    "SELECT * FROM user WHERE id = ?",
                    new UserRowMapper(),
                    id
            );

            if (users.size() != 0)
                return users.get(0);
            else
                return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String add(AddUserRequest request) {
        String error = null;

        try {
            int count = jdbcTemplate.update(
                    "INSERT IGNORE INTO user (card_number, first_name, second_name, patronymic) VALUES (?, ?, ?, ?)",
                    request.getCardNumber(),
                    request.getFirstName(),
                    request.getSecondName(),
                    request.getPatronymic()
            );

            if (count == 0)
                error = "User with card number '" + request.getCardNumber() + "' is already added.";
        } catch (Exception e) {
            error = e.getMessage();
        }

        return error;
    }
}
