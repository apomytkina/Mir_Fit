package com.mirfit.mirfit.controllers;

import com.mirfit.mirfit.models.Bonuses;
import com.mirfit.mirfit.models.GetBonusesResponse;
import com.mirfit.mirfit.services.BonusesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping(value = "/bonuses")
public class BonusesController {
    private final BonusesService bonusesService;

    @Autowired
    public BonusesController(BonusesService bonusesService) {
        this.bonusesService = bonusesService;
    }

    @PutMapping("increase")
    public ResponseEntity<String> increase(@RequestBody Bonuses request) {
        String error = bonusesService.updateBonuses(request.getId(), request.getNumberOfBonuses());

        if (error != null) {
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("decrease")
    public ResponseEntity<String> decrease(@RequestBody Bonuses request) {
        String result = bonusesService.updateBonuses(request.getId(), request.getNumberOfBonuses() * -1);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Bonuses> getBonuses(@PathVariable UUID id) {
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

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String error = bonusesService.delete(id);

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
