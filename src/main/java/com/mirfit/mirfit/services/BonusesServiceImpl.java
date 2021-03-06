package com.mirfit.mirfit.services;

import com.mirfit.mirfit.models.GetBonusesResponse;
import com.mirfit.mirfit.repositories.BonusesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BonusesServiceImpl implements BonusesService {
    private final BonusesRepository bonusesRepository;

    @Autowired
    public BonusesServiceImpl(BonusesRepository bonusesRepository) {
        this.bonusesRepository = bonusesRepository;
    }

    @Override
    public String updateBonuses(UUID id, double numberOfBonuses) {
        if (id != null)
            return bonusesRepository.updateBonuses(id, numberOfBonuses);
        else
            return "Id cannot be null.";
    }

    @Override
    public GetBonusesResponse getBonuses(UUID id) {
        if (id != null)
            return bonusesRepository.getBonuses(id);
        else
            return new GetBonusesResponse("Id cannot be null.", null);
    }

    @Override
    public String delete(UUID id) {
        if (id != null)
            return bonusesRepository.delete(id);
        else
            return "Id cannot be null.";
    }

    @Override
    public String add(UUID id) {
        if (id != null)
            return bonusesRepository.add(id);
        else
            return "Id cannot be null.";
    }
}
