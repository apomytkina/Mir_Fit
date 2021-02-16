package com.mirfit.mirfit.repositories;

import com.mirfit.mirfit.models.Bonuses;
import com.mirfit.mirfit.models.GetBonusesResponse;
import com.mirfit.mirfit.rowmappers.BonusesRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Repository
public class BonusesRepositoryImpl implements BonusesRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BonusesRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String updateBonuses(long userId, double numberOfBonuses) {
        String error = null;

        try {
            List<Bonuses> res = jdbcTemplate.query(
                    "SELECT * FROM bonuses WHERE id = ?",
                    new BonusesRowMapper(),
                    userId
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
                            "WHERE id = ?",
                    numberOfBonuses,
                    numberOfBonuses,
                    userId
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
    public GetBonusesResponse getBonuses(long userId) {
        GetBonusesResponse getBonusesResponse = new GetBonusesResponse();

        try {
            List<Bonuses> res = jdbcTemplate.query(
                    "SELECT * FROM bonuses WHERE ID = ?",
                    new BonusesRowMapper(),
                    userId
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

    public long getLastInsertId() {
        long id = 0;

        try {
            List<Long> res = jdbcTemplate.query(
                    "SELECT LAST_INSERT_ID();",
                    (rs, i) -> rs.getLong("LAST_INSERT_ID()")
            );

            if (res.size() > 0)
                id = res.get(0);
        } catch (DataAccessException e) {
            //nothing here
        }

        return id;
    }

    @Override
    public String add() {
        String error = null;

        try {
            int count = jdbcTemplate.update(
                    "INSERT INTO bonuses (number_of_bonuses) VALUES (0)"
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
    public String delete(long userId) {
        String error = null;

        try {
            int count = jdbcTemplate.update(
                    "DELETE IGNORE FROM bonuses WHERE id = ?",
                    userId
            );

            if (count == 0) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cannot find account with id = " + userId
                );
            }
        } catch (DataAccessException e) {
            error = e.getMessage();
        }

        return error;
    }
}
