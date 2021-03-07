package com.mirfit.mirfit.controllers;

import com.mirfit.mirfit.models.*;
import com.mirfit.mirfit.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/cards")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PatchMapping
    public ResponseEntity<String> updateName(@RequestBody UpdateNameRequest request) {
        String error = cardService.updateName(request.getCardNumber(), request.getName());

        if (error == null)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCard(@RequestBody AddCardRequest request) {
        String error = cardService.addCard(request.getUserId(), request.getCard());

        if (error == null)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Card>> getCardsByUserId(@PathVariable UUID id) {
        GetCardsResponse result = cardService.getBonuses(id);

        if (result.getError() == null) {
            return new ResponseEntity<>(result.getCards(), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    result.getError()
            );
        }
    }

    @DeleteMapping("/{cardNumber}")
    public ResponseEntity<String> delete(@PathVariable String cardNumber) {
        String error = cardService.delete(cardNumber);

        if (error != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    error
            );
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/all/{userId}")
    public ResponseEntity<String> delete(@PathVariable UUID userId) {
        String error = cardService.delete(userId);

        if (error != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    error
            );
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
