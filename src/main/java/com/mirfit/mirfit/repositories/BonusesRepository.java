package com.mirfit.mirfit.repositories;

import com.mirfit.mirfit.models.GetBonusesResponse;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BonusesRepository {
    String updateBonuses(UUID userId, double numberOfBonuses);

    GetBonusesResponse getBonuses(UUID userId);

    String add(UUID userId);

    String delete(UUID userId);
}
