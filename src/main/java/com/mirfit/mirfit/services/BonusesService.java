package com.mirfit.mirfit.services;

import com.mirfit.mirfit.models.GetBonusesResponse;
import org.springframework.stereotype.Service;

@Service
public interface BonusesService {
    String updateBonuses(long id, double numberOfBonuses);

    GetBonusesResponse getBonuses(long id);
}
