package com.mirfit.mirfit.controllers;

import com.mirfit.mirfit.models.Receipt;
import com.mirfit.mirfit.models.Transaction;
import com.mirfit.mirfit.services.BonusesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bonuses")
public class BonusesController {
    private final BonusesService bonusesService;

    @Autowired
    public BonusesController(BonusesService bonusesService) {
        this.bonusesService = bonusesService;
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return new ResponseEntity<>(bonusesService.getAllTransactions(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> start(@RequestBody Receipt receipt) {
        return new ResponseEntity<>(bonusesService.saveTransaction(receipt, "wait"), HttpStatus.OK);
    }

    @PostMapping("bonuses")
    public ResponseEntity<Double> getBonuses(@RequestBody Receipt receipt) {
        return new ResponseEntity<>(bonusesService.getNumberOfBonuses(receipt), HttpStatus.OK);
    }
}
