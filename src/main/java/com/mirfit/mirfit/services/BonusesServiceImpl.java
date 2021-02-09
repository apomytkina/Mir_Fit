package com.mirfit.mirfit.services;

import com.mirfit.mirfit.models.GetBonusesResponse;
import com.mirfit.mirfit.repositories.BonusesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BonusesServiceImpl implements BonusesService {
    private final BonusesRepository bonusesRepository;

    @Autowired
    public BonusesServiceImpl(BonusesRepository bonusesRepository) {
        this.bonusesRepository = bonusesRepository;
    }

    @Override
    public String updateBonuses(long id, double numberOfBonuses) {
        return bonusesRepository.updateBonuses(id, numberOfBonuses);
    }

    @Override
    public GetBonusesResponse getBonuses(long id) {
        return bonusesRepository.getBonuses(id);
    }
}
