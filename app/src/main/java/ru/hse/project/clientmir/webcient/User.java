package ru.hse.project.clientmir.webcient;

import android.content.SharedPreferences;

public class User {

    public static final String CODE_ID = "user_id";
    public static final String CODE_FIRST_NAME = "firstName";
    public static final String CODE_SECOND_NAME = "secondName";
    public static final String CODE_PATRONYMIC = "patronymic";

    private final SharedPreferences sharedPref;
    private final long id;
    private String firstName;
    private String secondName;
    private String patronymic;


    User(SharedPreferences sharedPref, long id, String firstName,String secondName,String patronymic) {
        this.sharedPref = sharedPref;
        this.id = id;
        this.firstName = firstName;
        this.secondName=secondName;
        this.patronymic=patronymic;
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

    public void updateName(String newName) {
        firstName = newName;
    }



    public static User builder(SharedPreferences sharedPref) {
        long userId = sharedPref.getLong(CODE_ID, 0);
        if (userId == 0) {
            return null;
        }

        String firstName = sharedPref.getString(CODE_FIRST_NAME,null);
        String secondName= sharedPref.getString(CODE_SECOND_NAME,null);
        String patronymic = sharedPref.getString(CODE_PATRONYMIC,null);
        return new User(sharedPref,userId,firstName,secondName,patronymic);
    }
}
