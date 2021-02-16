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
        if (id > 0)
            return bonusesRepository.updateBonuses(id, numberOfBonuses);
        else
            return "Id must be positive.";
    }

    @Override
    public GetBonusesResponse getBonuses(long id) {
        if (id > 0)
            return bonusesRepository.getBonuses(id);
        else
            return new GetBonusesResponse("Id must be positive.", null);
    }

    @Override
    public String delete(long id) {
        if (id > 0)
            return bonusesRepository.delete(id);
        else
            return "Id must be positive.";
    }

    @Override
    public String add() {
        return bonusesRepository.add();
    }
}
