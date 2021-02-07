package ru.hse.project.clientmir.appClient;

import android.content.SharedPreferences;

public final class User {

    public static final String CODE_ID = "userId";
    public static final String CODE_FIRST_NAME = "firstName";
    public static final String CODE_SECOND_NAME = "secondName";
    public static final String CODE_PATRONYMIC = "patronymic";
    public static final String CODE_CARD_NUMBER = "cardNumber";
    public static final String CODE_LOGIN = "login";
    public static final String CODE_PASSWORD = "password";


    private final SharedPreferences sharedPref;
    private final long id;
    private String firstName;
    private String secondName;
    private String patronymic;
    private String login;
    private String password;
    private long cardNumber;


    User(SharedPreferences sharedPref,
         long id,
         String firstName,
         String secondName,
         String patronymic,
         String login,
         String password,
         long cardNumber) {
        this.sharedPref = sharedPref;
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.login = login;
        this.password = password;
        this.cardNumber = cardNumber;
    }

    public SharedPreferences getSharedPref() {
        return sharedPref;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void updateFirstName(String newFirstName) {
        firstName = newFirstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void updateSecondName(String newSecondName) {
        secondName = newSecondName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void updatePatronymic(String newPatronymic) {
        patronymic = newPatronymic;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void updateCardNumber(long newCardNumber) {
        cardNumber = newCardNumber;
    }


    public static User builder(SharedPreferences sharedPref) {
        long userId = sharedPref.getLong(CODE_ID, 0);
        if (userId == 0) {
            return null;
        }
        String firstName = sharedPref.getString(CODE_FIRST_NAME, null);
        String secondName = sharedPref.getString(CODE_SECOND_NAME, null);
        String patronymic = sharedPref.getString(CODE_PATRONYMIC, null);
        String login = sharedPref.getString(CODE_LOGIN, null);
        String password = sharedPref.getString(CODE_PASSWORD, null);
        long cardNumber = sharedPref.getLong(CODE_CARD_NUMBER, 0);

        return new User(sharedPref, userId, firstName, secondName, patronymic, login, password, cardNumber);
    }
}
