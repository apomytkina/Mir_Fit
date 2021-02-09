package com.mirfit.mirfit.repositories;

import com.mirfit.mirfit.models.*;
import com.mirfit.mirfit.rowmappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    // For testing
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public GetUserResponse getById(long id) {
        try {
            List<User> users = jdbcTemplate.query(
                    "SELECT * FROM user WHERE id = ?",
                    new UserRowMapper(),
                    id
            );

            if (users.size() != 0)
                return new GetUserResponse(users.get(0), null);
            else
                return new GetUserResponse(null, "User not found");
        } catch (Exception e) {
            return new GetUserResponse(null, e.getMessage());
        }
    }

    @Override
    public String add(AddUserRequest request) {
        String error = null;

        try {
            int count = jdbcTemplate.update(
                    "INSERT IGNORE INTO user (card_number, first_name, second_name, patronymic, password, login) " +
                            "VALUES (?, ?, ?, ?, ?, ?)",
                    request.getCardNumber(),
                    request.getFirstName(),
                    request.getSecondName(),
                    request.getPatronymic(),
                    request.getPassword(),
                    request.getLogin()
            );

            if (count == 0)
                error = "User with login '" + request.getLogin() + "' is already added.";
        } catch (Exception e) {
            error = e.getMessage();
        }

        return error;
    }

    @Override
    public AuthUserResponse authUser(AuthUserRequest request) {

        try {
            var result = jdbcTemplate.query(
                    "SELECT * FROM user WHERE login = ? AND password = ?",
                        new UserRowMapper(),
                        request.getLogin(),
                        request.getPassword()
            );

            if (result.size() != 0) {
                return new AuthUserResponse(result.get(0).getId(),null);
            }
            else {
                return new AuthUserResponse(-1, "Wrong login or password");
            }
        }
        catch(Exception e) {
            return new AuthUserResponse(-1, e.getMessage());
        }
    }

    @Override
    public String deleteUser(long id) {
        try {

            var result = jdbcTemplate.update(
                    "DELETE FROM user WHERE id = ?",
                    id
            );

            if (result != 0) {
                return null;
            }

            else {
                return "User not found";
            }
        }
        catch (Exception ex) {
            return ex.getMessage();
        }
    }
}
