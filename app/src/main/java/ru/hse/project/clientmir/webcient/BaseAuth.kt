package ru.hse.project.clientmir.webcient

import android.content.Context
import android.util.Log
import android.widget.Toast
import okhttp3.*
import java.io.IOException

class BaseAuth(private val context: Context) {

    private val PRIVATE_MODE = 0;
    private val PREF_FILE = "json_user";
    private val userSharedPref = context.getSharedPreferences(PREF_FILE, PRIVATE_MODE)
    var currentUser: User? = User.builder(userSharedPref);

    fun signInWithEmailAndPassword(email: String, password: String) {


    }


    fun createUserWithEmailAndPassword(email: String, password: String) {
        val client = OkHttpClient()

        val formBody = FormBody.Builder()
            .add("cardNumber", "32423")
            .add("firstName", "alex")
            .add("login", "login")
            .add("password", "123456789")
            .add("patronymic", "Vladimirovich")
            .add("secondName", "volov")
            .build()


        val request = Request.Builder()
            .url("https://localhost:8080/users")
            .post(formBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

                Log.d("RESPONSE", e.message.toString())

            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    Log.d("RESPONSE", "Successful")


                } else {
                    Log.d("RESPONSE_D", "Not Successful")

                }

            }
        })


        // val editor = userSharedPref.edit()
        // editor.putLong(User.CODE_ID,0)
        //  editor.putString(User.CODE_EMAIL, email);
        // editor.putString(User.CODE_PASSWORD, password);
        // editor.apply()
        // currentUser = User.builder(userSharedPref)
    }

}


