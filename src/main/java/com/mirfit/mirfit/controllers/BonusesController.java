package com.mirfit.mirfit.controllers;

import com.mirfit.mirfit.models.Receipt;
import com.mirfit.mirfit.models.Transaction;
import com.mirfit.mirfit.services.BonusesService;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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

    @GetMapping(value = "cancel/{transactionNumber}")
    public ResponseEntity<String> cancelTransaction(@PathVariable String transactionNumber) {
        return new ResponseEntity<>(bonusesService.cancelTransaction(transactionNumber), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boolean> start(@RequestBody Receipt receipt) {
       // return new ResponseEntity<>(bonusesService.saveTransaction(receipt, "wait"), HttpStatus.OK);
        return new ResponseEntity<>(bonusesService.start(receipt), HttpStatus.OK);
    }

    @PostMapping("bonuses")
    public ResponseEntity<Double> getBonuses(@RequestBody Receipt receipt) {
        return new ResponseEntity<>(bonusesService.getNumberOfBonuses(receipt), HttpStatus.OK);
    }
}
