package com.mirfit.mirfit.repositories;

import com.mirfit.mirfit.models.BonusesAccount;
import com.mirfit.mirfit.models.GetBonusesResponse;
import com.mirfit.mirfit.rowmappers.BonusesRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Repository
public class BonusesRepositoryImpl implements BonusesRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BonusesRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String updateBonuses(UUID userId, double numberOfBonuses) {
        String error = null;

        try {
            List<BonusesAccount> res = jdbcTemplate.query(
                    "SELECT * FROM bonuses WHERE user_id = ?",
                    new BonusesRowMapper(),
                    userId.toString()
            );

            if (res.size() == 0)
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cannot find user with id = " + userId
                );

            int count = jdbcTemplate.update(
                    "UPDATE bonuses " +
                            "SET number_of_bonuses = CASE" +
                            "  WHEN number_of_bonuses + ? >= 0" +
                            "    THEN number_of_bonuses + ?" +
                            "    ELSE number_of_bonuses " +
                            "END " +
                            "WHERE user_id = ?",
                    numberOfBonuses,
                    numberOfBonuses,
                    userId.toString()
            );

            if (count == 0 || numberOfBonuses + res.get(0).getNumberOfBonuses() < 0) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Not enough bonuses"
                );
            }
        } catch (DataAccessException e) {
            error = e.getMessage();
        }

        return error;
    }

    @Override
    public GetBonusesResponse getBonuses(UUID userId) {
        GetBonusesResponse getBonusesResponse = new GetBonusesResponse();

        try {
            List<BonusesAccount> res = jdbcTemplate.query(
                    "SELECT * FROM bonuses WHERE user_id = ?",
                    new BonusesRowMapper(),
                    userId.toString()
            );

            if (res.size() != 0) {
                getBonusesResponse.setBonuses(res.get(0));
            } else {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cannot find user with id = " + userId
                );
            }
        } catch (DataAccessException e) {
            getBonusesResponse.setError(e.getMessage());
        }

        return getBonusesResponse;
    }

    @Override
    public String add(UUID id) {
        String error = null;

        try {
            int count = jdbcTemplate.update(
                    "INSERT IGNORE INTO bonuses (user_id, number_of_bonuses) VALUES (?, 0)",
                    id.toString()
            );

            if (count == 0) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Failed to create new bonuses account"
                );
            }
        } catch (DataAccessException e) {
            error = e.getMessage();
        }

        return error;
    }

    @Override
    public String delete(UUID userId) {
        String error = null;

        try {
            int count = jdbcTemplate.update(
                    "DELETE IGNORE FROM bonuses WHERE user_id = ?",
                    userId.toString()
            );

            if (count == 0) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cannot find user with id = " + userId
                );
            }
        } catch (DataAccessException e) {
            error = e.getMessage();
        }

        return error;
    }
}
