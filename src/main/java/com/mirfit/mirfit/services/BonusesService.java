package com.mirfit.mirfit.services;

import com.mirfit.mirfit.models.GetBonusesResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface BonusesService {
    String updateBonuses(UUID id, double numberOfBonuses);

    GetBonusesResponse getBonuses(UUID id);

    String delete(UUID id);

    String add(UUID id);
}
