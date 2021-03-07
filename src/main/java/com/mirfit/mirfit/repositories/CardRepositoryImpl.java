package com.mirfit.mirfit.repositories;

import com.mirfit.mirfit.models.Card;
import com.mirfit.mirfit.models.CardDto;
import com.mirfit.mirfit.models.GetCardsResponse;
import com.mirfit.mirfit.rowmappers.CardRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Repository
public class CardRepositoryImpl implements CardRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CardRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String updateBonuses(UUID userId, double numberOfBonuses) {
        String error = null;

        try {
            List<Card> res = jdbcTemplate.query(
                    "SELECT * FROM bonuses WHERE user_id = ?",
                    new CardRowMapper(),
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
    public GetCardsResponse getCardsByUserId(UUID userId) {
        GetCardsResponse getCardsResponse = new GetCardsResponse();

        try {
            List<Card> res = jdbcTemplate.query(
                    "SELECT * FROM card WHERE user_id = ?",
                    new CardRowMapper(),
                    userId.toString()
            );

            getCardsResponse.setCards(res);
        } catch (DataAccessException e) {
            getCardsResponse.setError(e.getMessage());
        }

        return getCardsResponse;
    }

    @Override
    public String addCard(UUID id, CardDto card) {
        String error = null;

        try {
            int count = jdbcTemplate.update(
                    "INSERT IGNORE INTO card (user_id, number_of_bonuses, name, number) VALUES (?, 0, ?, ?)",
                    id.toString(),
                    card.getName(),
                    card.getNumber()
            );

            if (count == 0) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Card with number '" + card.getNumber() + "' is already added."
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
                    "DELETE IGNORE FROM card WHERE user_id = ?",
                    userId.toString()
            );

            if (count == 0) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cannot find cards with user id = " + userId
                );
            }
        } catch (DataAccessException e) {
            error = e.getMessage();
        }

        return error;
    }

    @Override
    public String delete(String cardNumber) {
        String error = null;

        try {
            int count = jdbcTemplate.update(
                    "DELETE IGNORE FROM card WHERE number = ?",
                    cardNumber
            );

            if (count == 0) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cannot find card with number = " + cardNumber
                );
            }
        } catch (DataAccessException e) {
            error = e.getMessage();
        }

        return error;
    }

    @Override
    public String updateName(String cardNumber, String name) {
        String error = null;

        try {
            int count = jdbcTemplate.update(
                    "UPDATE IGNORE card " +
                            "SET name = ? " +
                            "WHERE number = ?",
                    name,
                    cardNumber
            );

            if (count == 0) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cannot find card with number = " + cardNumber
                );
            }
        } catch (DataAccessException e) {
            error = e.getMessage();
        }

        return error;
    }
}
