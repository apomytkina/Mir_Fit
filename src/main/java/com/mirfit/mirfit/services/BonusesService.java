package com.mirfit.mirfit.services;

import com.mirfit.mirfit.models.Receipt;
import org.springframework.stereotype.Service;

@Service
public interface BonusesService {
    double getNumberOfBonuses(Receipt receipt);
}
