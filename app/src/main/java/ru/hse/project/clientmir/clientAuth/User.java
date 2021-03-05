package ru.hse.project.clientmir.clientAuth;

import android.content.SharedPreferences;
import android.net.Uri;

public final class User {

    public static final String CODE_ID = "id";
    public static final String CODE_FIRST_NAME = "firstName";
    public static final String CODE_SECOND_NAME = "secondName";
    public static final String CODE_PATRONYMIC = "patronymic";
    public static final String CODE_CARD_NUMBER = "cardNumber";
    public static final String CODE_LOGIN = "login";
    public static final String CODE_PASSWORD = "password";


    private final SharedPreferences sharedPref;
    private final BaseClient baseClient;
    private final long id;
    private String firstName;
    private String secondName;
    private String patronymic;
    private String login;
    private String password;
    private String cardNumber;
    private Uri imageUrl;

    User(SharedPreferences sharedPref, BaseClient baseClient,
         long id,
         String firstName,
         String secondName,
         String patronymic,
         String login,
         String password,
         String cardNumber) {
        this.sharedPref = sharedPref;
        this.baseClient = baseClient;
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
        baseClient.updateUser(this);
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void updateCardNumber(String newCardNumber) {
        cardNumber = newCardNumber;
    }

    public void updateImageUrl(Uri newUrl) {
        imageUrl = newUrl;
    }


    public static User builder(SharedPreferences sharedPref, BaseClient baseClient) {
        long userId = sharedPref.getLong(CODE_ID, 0);
        if (userId == 0) {
            return null;
        }

        String firstName = sharedPref.getString(CODE_FIRST_NAME, null);
        String secondName = sharedPref.getString(CODE_SECOND_NAME, null);
        String patronymic = sharedPref.getString(CODE_PATRONYMIC, null);
        String login = sharedPref.getString(CODE_LOGIN, null);
        String password = sharedPref.getString(CODE_PASSWORD, null);
        String cardNumber = sharedPref.getString(CODE_CARD_NUMBER, null);

        return new User(sharedPref, baseClient, userId, firstName, secondName, patronymic, login, password, cardNumber);
    }
}
