package com.mirfit.mirfit.controllers;

import com.mirfit.mirfit.models.Bonuses;
import com.mirfit.mirfit.models.GetBonusesResponse;
import com.mirfit.mirfit.services.BonusesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/bonuses")
public class BonusesController {
    private final BonusesService bonusesService;

    @Autowired
    public BonusesController(BonusesService bonusesService) {
        this.bonusesService = bonusesService;
    }

    @PutMapping("{id}/increase/{bonuses}")
    public ResponseEntity<String> increase(@PathVariable long id, @PathVariable double bonuses) {
        String error = bonusesService.updateBonuses(id, bonuses);

        if (error != null) {
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("{id}/decrease/{bonuses}")
    public ResponseEntity<String> decrease(@PathVariable long id, @PathVariable double bonuses) {
        String result = bonusesService.updateBonuses(id, bonuses * -1);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Bonuses> getBonuses(@PathVariable long id) {
        GetBonusesResponse result = bonusesService.getBonuses(id);

        if (result.getError() == null) {
            return new ResponseEntity<>(result.getBonuses(), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    result.getError()
            );
        }
    }
}
