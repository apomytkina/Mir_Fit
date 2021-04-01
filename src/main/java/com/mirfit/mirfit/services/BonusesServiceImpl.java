package com.mirfit.mirfit.services;

import com.isomessage.ObjectHexTranslator;
import com.mirfit.mirfit.models.Product;
import com.mirfit.mirfit.models.Receipt;
import com.mirfit.mirfit.models.Transaction;
import com.mirfit.mirfit.repositories.CardRepository;
import com.mirfit.mirfit.repositories.ProductRepository;
import com.mirfit.mirfit.repositories.TransactionRepository;
import com.models.ISOMessage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Time;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

@Service
public class BonusesServiceImpl implements BonusesService {
    private final ProductRepository productRepository;
    private final TransactionRepository transactionRepository;
    private final String BASE_URL = "https://mir-acquirer.herokuapp.com/main/fit-api";
    private static final OkHttpClient client = new OkHttpClient();
    private final CardService cardService;

    @Autowired
    public BonusesServiceImpl(ProductRepository productRepository,
                              TransactionRepository transactionRepository,
                              CardService cardService) {
        this.productRepository = productRepository;
        this.transactionRepository = transactionRepository;
        this.cardService = cardService;
    }

    public boolean start(Receipt receipt) {
        try {
            if (receipt.getAccrual()) {
                // если начисление бонусов
                double currentBonuses = getNumberOfBonuses(receipt);
                saveTransaction(receipt, "wait", currentBonuses);
                boolean success = makeTransaction(receipt, receipt.getAmount(), "00");
                if (success) {
                    cardService.updateBonuses(receipt.getCardSequence(), currentBonuses);
                    transactionRepository.update("success",receipt.getTransactionNumber());
                } else {
                    transactionRepository.update("failure",receipt.getTransactionNumber());
                    return false;
                }
            } else {
                // списание бонусов
                double sumBonuses = cardService.getBonuses(receipt.getCardSequence()).getBonuses();
                double withdrawnBonuses;
                if (sumBonuses >= receipt.getAmount()) {
                    withdrawnBonuses = receipt.getAmount();
                    receipt.setAmount(0);
                } else {
                    withdrawnBonuses = sumBonuses;
                    long newAmount = receipt.getAmount() - Math.round(sumBonuses);
                    receipt.setAmount(newAmount);
                }
                saveTransaction(receipt, "wait", withdrawnBonuses);

                boolean success = makeTransaction(receipt, receipt.getAmount(), "00");
                if (success) {
                    cardService.updateBonuses(receipt.getCardSequence(), (-1) * withdrawnBonuses);
                    transactionRepository.update("success",receipt.getTransactionNumber());
                } else {
                    transactionRepository.update("failure",receipt.getTransactionNumber());
                    return false;
                }
            }
            return true;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error with request");
        }
    }

    private String saveTransaction(Receipt receipt, String status, double bonuses) {

        return transactionRepository.add(new Transaction(
                0,
                receipt.getTransactionNumber(),
                Date.valueOf(receipt.getLocalDate()),
                Time.valueOf(receipt.getLocalTime().plusHours(3)),
                bonuses,
                receipt.getAccrual(),
                receipt.getCardSequence(),
                receipt.getAmount(),
                status,
                receipt.getCardAcceptorIdentificationCode()
        ));
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.getAll();
    }

    @Override
    public String cancelTransaction(String transactionNumber) {
        Transaction transaction = transactionRepository.getByNumber(transactionNumber);

        if (transaction.getStatus().equals("success")) {

                boolean success = makeTransaction(new Receipt(
                        transactionNumber,
                        transaction.getAmount(),
                        null,
                        transaction.getDate().toLocalDate(),
                        transaction.getTime().toLocalTime(),
                        transaction.isAccrual(),
                        transaction.getCardNumber(),
                        transaction.getCardAcceptorIdentificationCode()
                ), transaction.getAmount(), "28");

                if (success) {
                    if (transaction.isAccrual()) {
                        cardService.updateBonuses(transaction.getCardNumber(), (-1) * transaction.getBonuses());
                    } else {
                        cardService.updateBonuses(transaction.getCardNumber(), transaction.getBonuses());
                    }

                    transactionRepository.update("cancelled", transactionNumber);
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Failed to cancel");
                }
                return null;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to cancel");
        }
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

    private boolean makeTransaction(Receipt receipt, long amount, String processingCode) {

        ISOMessage isoMessage = new ISOMessage(
                receipt.getCardSequence(),
                processingCode,
                amount,
                receipt.getLocalTime(),
                receipt.getLocalDate(),
                receipt.getCardAcceptorIdentificationCode(),
                receipt.getTransactionNumber());

        try {
            String encodedMessage = ObjectHexTranslator.getEncodedMessage(isoMessage);

            Request request = new Request.Builder()
                    .url(BASE_URL + "?Payload=" + encodedMessage)
                    .build();

            Response response = client.newCall(request).execute();
            ISOMessage resultMessage;
            if (response.isSuccessful()) {
                String result = response.body().string();
                try {
                    resultMessage = ObjectHexTranslator.getISOMessage(result);
                    return true;
                } catch (Exception  e) {
                    return false;
                }
            }
            return false;
        } catch (Exception e) {

            return false;
        }

    }
}
