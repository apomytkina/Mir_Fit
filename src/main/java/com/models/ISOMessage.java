package com.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

// For FIT.
public class ISOMessage {

    // The type of the message.
    // (n4)
    private String mti = "0100";

    // The number of a car.
    // F2 (n13...19)
    private String primaryAccountNumber;

    // The type of the operation.
    // F3 SubF 1. (n2)
    private String processingCode;

    // F4 (n12)
    private long amountTransaction;

    // Time on the side of FIT.
    // F12 ("hhmmss")
    private LocalTime localTime;

    // Date on the side of FIT.
    // F13 ("MMDD")
    private LocalDate localDate;

    // The number of a terminal.
    // F42 (ans15)
    private String cardAcceptorIdentificationCode;

    // The number is set by FIT.
    // F63 SubF 2 (n16)
    private String localTransactionNumber;

    // Date and Time on the side of the core.
    // F7 ("MMDDhhmmss")
    private LocalDateTime transmissionDateAndTime;

    // The number auxiliary for the globalTransactionNumber.
    // F11 (n6)
    private String systemTraceAuditNumber;

    // The number is set by the core.
    // F37 ("YJJJHHNNNNNN")
    private String globalTransactionNumber;


    public ISOMessage() {};

    // For formation of the message on the side of FIT.
    public ISOMessage(String primaryAccountNumber, String processingCode, long amountTransaction,
                      LocalTime localTime, LocalDate localDate,
                      String cardAcceptorIdentificationCode, String localTransactionNumber) {
        setPrimaryAccountNumber(primaryAccountNumber);
        setProcessingCode(processingCode);
        setAmountTransaction(amountTransaction);
        this.localTime = localTime;
        this.localDate = localDate;
        setCardAcceptorIdentificationCode(cardAcceptorIdentificationCode);
        setLocalTransactionNumber(localTransactionNumber);
    }

    public String getMti() {
        return mti;
    }

    public void setMti(String mti) {
        if (mti.compareTo("0100") != 0 && mti.compareTo("0110") != 0)
            throw new IllegalArgumentException("The mti must be equal to only 0100 or 0110!");
        this.mti = mti;
    }

    public String getPrimaryAccountNumber() {
        return primaryAccountNumber;
    }

    public void setPrimaryAccountNumber(String primaryAccountNumber) {
        if (primaryAccountNumber.length() < 13 || 19 < primaryAccountNumber.length())
            throw new IllegalArgumentException("The primaryAccountNumber must have the length from 13 to 19!");
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(primaryAccountNumber);
        if (!matcher.matches())
            throw new IllegalArgumentException("The primaryAccountNumber must include only numeral symbols!");
        this.primaryAccountNumber = primaryAccountNumber;
    }

    public String getProcessingCode() {
        return processingCode;
    }

    public void setProcessingCode(String processingCode) {
        if (processingCode.compareTo("00") != 0 && processingCode.compareTo("28") != 0)
            throw new IllegalArgumentException("The processingCode must be equal to only 00 or 28!");
        this.processingCode = processingCode;
    }

    public long getAmountTransaction() {
        return amountTransaction;
    }

    public void setAmountTransaction(long amountTransaction) {
        String amountTransactionStr = String.valueOf(amountTransaction);
        if (amountTransactionStr.length() > 12)
            throw new IllegalArgumentException("The amountTransaction must have the length no more than 12!");
        this.amountTransaction = amountTransaction;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getCardAcceptorIdentificationCode() {
        return cardAcceptorIdentificationCode;
    }

    public void setCardAcceptorIdentificationCode(String cardAcceptorIdentificationCode) {
        if (cardAcceptorIdentificationCode.length() < 1 || cardAcceptorIdentificationCode.length() > 15)
            throw new IllegalArgumentException
                    ("The cardAcceptorIdentificationCode must have the length from 1 to 15!");
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(cardAcceptorIdentificationCode);
        if (!matcher.matches())
            // todo: can be add to the documentation.
            // According the the FIT's requests.
            throw new IllegalArgumentException("The cardAcceptorIdentificationCode must include only numeral symbols!");
        this.cardAcceptorIdentificationCode = cardAcceptorIdentificationCode;
    }

    public String getLocalTransactionNumber() {
        return localTransactionNumber;
    }

    public void setLocalTransactionNumber(String localTransactionNumber) {
        if (localTransactionNumber.length() < 1 || localTransactionNumber.length() > 16)
            throw new IllegalArgumentException("The localTransactionNumber must have the length from 1 to 16!");
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(localTransactionNumber);
        if (!matcher.matches())
            throw new IllegalArgumentException("The localTransactionNumber must include only numeral symbols!");
        this.localTransactionNumber = localTransactionNumber;
    }

    public LocalDateTime getTransmissionDateAndTime() {
        return transmissionDateAndTime;
    }

    public void setTransmissionDateAndTime(LocalDateTime transmissionDateAndTime) {
        this.transmissionDateAndTime = transmissionDateAndTime;
    }

    public String getSystemTraceAuditNumber() {
        return systemTraceAuditNumber;
    }

    public void setSystemTraceAuditNumber(String systemTraceAuditNumber) {
        if (systemTraceAuditNumber.length() < 1 || systemTraceAuditNumber.length() > 6)
            throw new IllegalArgumentException("The systemTraceAuditNumber must have the length from 1 to 6!");
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(systemTraceAuditNumber);
        if (!matcher.matches())
            throw new IllegalArgumentException("The systemTraceAuditNumber must include only numeral symbols!");
        pattern = Pattern.compile("[0]+");
        if (!matcher.matches())
            throw new IllegalArgumentException("The systemTraceAuditNumber must not be equal to 0!");
        this.systemTraceAuditNumber = systemTraceAuditNumber;
    }

    public String getGlobalTransactionNumber() {
        return globalTransactionNumber;
    }

    public void setGlobalTransactionNumber(String globalTransactionNumber) {
        if (globalTransactionNumber.length() != 12)
            throw new IllegalArgumentException("The globalTransactionNumber must have the length = 12!");
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(globalTransactionNumber);
        if (!matcher.matches())
            throw new IllegalArgumentException("The globalTransactionNumber must include only numeral symbols!");
        this.globalTransactionNumber = globalTransactionNumber;
    }
}
