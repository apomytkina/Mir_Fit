package com.mirfit.mirfit.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Time;
import java.sql.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Transaction {

    @Id
    @GeneratedValue
    private int id;
    private String transactionNumber;
    private Date date;
    private Time time;
    private double bonuses;
    private boolean accrual;
    private String cardNumber;
    private Long amount;
    private String cardAcceptorIdentificationCode;

    public Transaction(int id, String transactionNumber,
                       Date date, Time time, double bonuses,
                       boolean accrual, String cardNumber,
                       Long amount, String status, String cardAcceptorIdentificationCode) {
        this.id = id;
        this.transactionNumber = transactionNumber;
        this.date = date;
        this.time = time;
        this.bonuses = bonuses;
        this.accrual = accrual;
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.status = status;
        this.cardAcceptorIdentificationCode = cardAcceptorIdentificationCode;
    }

    public Transaction() {
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardAcceptorIdentificationCode() {
        return cardAcceptorIdentificationCode;
    }

    public void setCardAcceptorIdentificationCode(String cardAcceptorIdentificationCode) {
        this.cardAcceptorIdentificationCode = cardAcceptorIdentificationCode;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public boolean isAccrual() {
        return accrual;
    }

    public void setAccrual(boolean accrual) {
        this.accrual = accrual;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public double getBonuses() {
        return bonuses;
    }

    public void setBonuses(double bonuses) {
        this.bonuses = bonuses;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String status;
}
