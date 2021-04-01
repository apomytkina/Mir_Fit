package com.mirfit.mirfit.services;

import com.mirfit.mirfit.models.Product;
import com.mirfit.mirfit.models.Receipt;
import com.mirfit.mirfit.models.Transaction;
import com.mirfit.mirfit.repositories.ProductRepository;
import com.mirfit.mirfit.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class BonusesServiceImpl implements BonusesService {
    private final ProductRepository productRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public BonusesServiceImpl(ProductRepository productRepository, TransactionRepository transactionRepository) {
        this.productRepository = productRepository;
        this.transactionRepository = transactionRepository;
    }

    public String saveTransaction(Receipt receipt, String status) {
        return transactionRepository.add(new Transaction(
                0,
                receipt.getTransactionId(),
                Date.from(receipt.getLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Time.valueOf(receipt.getLocalTime()),
                getNumberOfBonuses(receipt),
                status
        ));
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.getAll();
    }

    @Override
    public double getNumberOfBonuses(Receipt receipt) {
        long healthyProductsAmount = getHealthyProductsAmount(receipt.getGoods());
        double numOfBonuses;

        if (healthyProductsAmount < 1000) {
            numOfBonuses = 0.02 * healthyProductsAmount;
        } else if (healthyProductsAmount < 5000) {
            numOfBonuses = 0.05 * healthyProductsAmount;
        } else if (healthyProductsAmount < 10000) {
            numOfBonuses = 0.07 * healthyProductsAmount;
        } else if (healthyProductsAmount < 50000) {
            numOfBonuses = 0.1 * healthyProductsAmount;
        } else if (healthyProductsAmount < 100000) {
            numOfBonuses = 0.13 * healthyProductsAmount;
        } else {
            numOfBonuses = 0.15 * healthyProductsAmount;
        }

        return numOfBonuses;
    }

    private long getHealthyProductsAmount(List<Product> products) {
        long healthyProductsAmount = 0;
        List<Product> healthyProducts = productRepository.getAllHealthyProducts();

        for (Product p : products) {
            for (Product hp : healthyProducts) {
                if (p.getCategory().equals("alcohol")) {
                    return 0;
                }

                if (p.getName().equals(hp.getName())) {
                    healthyProductsAmount += p.getPrice();
                }
            }
        }

        return healthyProductsAmount;
    }
}
