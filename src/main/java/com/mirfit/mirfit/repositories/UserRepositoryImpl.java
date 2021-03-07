package com.mirfit.mirfit.repositories;

import com.mirfit.mirfit.models.*;
import com.mirfit.mirfit.rowmappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

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
    public GetUserResponse getById(UUID id) {
        try {
            List<User> users = jdbcTemplate.query(
                    "SELECT * FROM user WHERE id = ?",
                    new UserRowMapper(),
                    id.toString()
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
    public AddUserResponse add(AddUserRequest request) {
        String error = null;
        UUID id = UUID.randomUUID();

        try {
            int count = jdbcTemplate.update(
                    "INSERT IGNORE INTO user (id, first_name, second_name, patronymic, password, login) " +
                            "VALUES (?, ?, ?, ?, ?, ?)",
                    id.toString(),
                    request.getFirstName(),
                    request.getSecondName(),
                    request.getPatronymic(),
                    request.getPassword(),
                    request.getLogin()
            );

            if (count == 0)
                error = "Login is not available";
        } catch (Exception e) {
            error = e.getMessage();
        }

        return new AddUserResponse(error, id);
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
                return new AuthUserResponse(result.get(0), null);
            }
            else {
                return new AuthUserResponse(null, "Wrong login or password");
            }
        }
        catch(Exception e) {
            return new AuthUserResponse(null, e.getMessage());
        }
    }

    @Override
    public String deleteUser(UUID id) {
        try {

            var result = jdbcTemplate.update(
                    "DELETE FROM user WHERE id = ?",
                    id.toString()
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
