package com.mirfit.mirfit.controllers;

import com.mirfit.mirfit.models.Receipt;
import com.mirfit.mirfit.services.BonusesService;
import com.mirfit.mirfit.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/bonuses")
public class BonusesController {
    private final CardService cardService;
    private final BonusesService bonusesService;

    @Autowired
    public BonusesController(CardService cardService, BonusesService bonusesService) {
        this.cardService = cardService;
        this.bonusesService = bonusesService;
    }

    @PostMapping
    public ResponseEntity<Double> getBonuses(@RequestBody Receipt receipt) {
        return new ResponseEntity<>(bonusesService.getNumberOfBonuses(receipt), HttpStatus.OK);
    }
}
