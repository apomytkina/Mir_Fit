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
            int count = jdbcTemplate.update(
                    "UPDATE IGNORE bonuses SET number_of_bonuses = number_of_bonuses + ? WHERE id = ?",
                    numberOfBonuses,
                    userId
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
}
