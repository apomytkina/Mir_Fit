package ru.hse.project.clientmir.clientAuth

import android.content.Context
import android.util.Log
import okhttp3.*
import java.io.IOException

class BaseClientAuth(private val context: Context) {

    private val PRIVATE_MODE = 0;
    private val PREF_FILE = "json_user";
    private val userSharedPref = context.getSharedPreferences(PREF_FILE, PRIVATE_MODE)
    var currentUser: User? = User.builder(userSharedPref);

    fun signInUser(
        updateUI: (Boolean, String) -> Unit,
        login: String,
        password: String
    ) {

        val client = OkHttpClient()
        val formBody = FormBody.Builder()
            .add(User.CODE_LOGIN, login)
            .add(User.CODE_PASSWORD, password)
            .build()


        val request = Request.Builder()
            .url("https://10.0.2.2:8080/users/authUser")
            .post(formBody)
            .build()


        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                updateUI.invoke(false, e.message.toString())
                Log.d("RESPONSE", e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    // val editor = userSharedPref.edit()
                    // editor.putLong(User.CODE_ID, 0)
                    // editor.putString(User.CODE_LOGIN, login)
                    // editor.putString(User.CODE_PASSWORD, password)
                    // editor.apply()
                    // currentUser = User.builder(userSharedPref)
                    updateUI.invoke(true, null.toString())
                    Log.d("RESPONSE", "Successful")
                } else {
                    updateUI.invoke(false, response.message)
                    Log.d("RESPONSE", "Not Successful")
                }
            }
        })

    }


    fun createUser(
        updateUI: (Boolean, String) -> Unit,
        firstName: String,
        secondName: String,
        patronymic: String,
        cardNumber: Long,
        login: String,
        password: String
    ) {


        val client = OkHttpClient()
        val formBody = FormBody.Builder()
            .add(User.CODE_CARD_NUMBER, cardNumber.toString())
            .add(User.CODE_FIRST_NAME, firstName)
            .add(User.CODE_LOGIN, login)
            .add(User.CODE_PASSWORD, password)
            .add(User.CODE_PATRONYMIC, patronymic)
            .add(User.CODE_SECOND_NAME, secondName)
            .build()

        val request = Request.Builder()
            .url("https://localhost:8080/users")
            .post(formBody)
            .build()


        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                updateUI.invoke(false, e.message.toString())
                Log.d("RESPONSE", e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    // val editor = userSharedPref.edit()
                    // editor.putLong(User.CODE_ID, 0)
                    // editor.putString(User.CODE_FIRST_NAME, firstName)
                    // editor.putString(User.CODE_SECOND_NAME, secondName)
                    // editor.putString(User.CODE_PATRONYMIC, patronymic)
                    // editor.putLong(User.CODE_CARD_NUMBER, cardNumber)
                    // editor.putString(User.CODE_LOGIN, login)
                    // editor.putString(User.CODE_PASSWORD, password)
                    // editor.apply()
                    // currentUser = User.builder(userSharedPref)
                    updateUI.invoke(true, null.toString())
                    Log.d("RESPONSE", "Successful")
                } else {
                    updateUI.invoke(false, response.message)
                    Log.d("RESPONSE", "Not Successful")
                }
            }
        })

    }
}


