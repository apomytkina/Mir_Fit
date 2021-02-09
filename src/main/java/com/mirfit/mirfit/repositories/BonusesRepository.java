package com.mirfit.mirfit.repositories;

import com.mirfit.mirfit.models.GetBonusesResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface BonusesRepository {
    String updateBonuses(long userId, double numberOfBonuses);

    GetBonusesResponse getBonuses(long userId);
}
