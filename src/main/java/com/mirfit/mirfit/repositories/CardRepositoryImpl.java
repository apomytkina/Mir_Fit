package com.mirfit.mirfit.repositories;

import com.mirfit.mirfit.models.Card;
import com.mirfit.mirfit.models.CardDto;
import com.mirfit.mirfit.models.GetBonusesResponse;
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
    public String updateBonuses(String cardNumber, double numberOfBonuses) {
        String error = null;

        try {
            List<Card> res = jdbcTemplate.query(
                    "SELECT * FROM card WHERE number = ?",
                    new CardRowMapper(),
                    cardNumber
            );

            if (res.size() == 0)
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cannot find card with number = " + cardNumber
                );

            jdbcTemplate.update(
                    "UPDATE card SET number_of_bonuses = number_of_bonuses + ? WHERE number = ?",
                    numberOfBonuses,
                    cardNumber
            );

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
    public GetBonusesResponse getBonuses(String cardNumber) {
        GetBonusesResponse getBonusesResponse = new GetBonusesResponse(null, 0);

        try {
            List<Card> res = jdbcTemplate.query(
                    "SELECT * FROM card WHERE number = ?",
                    new CardRowMapper(),
                    cardNumber
            );

            if (res.size() > 0)
                getBonusesResponse.setBonuses(res.get(0).getNumberOfBonuses());
            else
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No such card"
                );
        } catch (DataAccessException e) {
            getBonusesResponse.setError(e.getMessage());
        }

        return getBonusesResponse;
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
            jdbcTemplate.update(
                    "DELETE IGNORE FROM card WHERE user_id = ?",
                    userId.toString()
            );
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
