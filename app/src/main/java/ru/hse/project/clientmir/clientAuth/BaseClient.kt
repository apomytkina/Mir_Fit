package ru.hse.project.clientmir.clientAuth

import android.content.Context
import android.net.Uri
import android.util.Log
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException


class BaseClient(private val context: Context) {

    private val AUTH_USER = "authUser"
    private val UPDATE_USER = "updateUser"
    private val PRIVATE_MODE = 0;
    private val PREF_FILE = "json_user";
    private val JSON = "application/json; charset=utf-8".toMediaType()
    private val BASE_URL = "http://192.168.31.121:8080/users/"
    private val ADD_USER_URL = "addUser"
    private val client: OkHttpClient = OkHttpClient()
    private val userSharedPref = context.getSharedPreferences(PREF_FILE, PRIVATE_MODE)
    var currentUser: User? = User.builder(userSharedPref, this);

    fun signInUser(
        updateUI: (Boolean) -> Unit,
        login: String,
        password: String
    ) {

        val json = JSONObject().apply {
            put(User.CODE_LOGIN, login)
            put(User.CODE_PASSWORD, password)
        }.toString()


        call(json, AUTH_USER).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                updateUI.invoke(false)
                Log.d("RESPONSE", e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val obj = JSONObject(response.body?.string()!!)
                    val editor = userSharedPref.edit()
                    editor.putLong(User.CODE_ID, obj.getLong(User.CODE_ID))
                    editor.putString(User.CODE_FIRST_NAME, obj.getString(User.CODE_FIRST_NAME))
                    editor.putString(User.CODE_SECOND_NAME, obj.getString(User.CODE_SECOND_NAME))
                    editor.putString(User.CODE_PATRONYMIC, obj.getString(User.CODE_PATRONYMIC))
                    editor.putString(User.CODE_CARD_NUMBER, obj.getString(User.CODE_CARD_NUMBER))
                    editor.putString(User.CODE_LOGIN, login)
                    editor.putString(User.CODE_PASSWORD, password)
                    editor.apply()
                    updateUI.invoke(true)
                    Log.d("RESPONSE", "Successful")
                } else {
                    updateUI.invoke(false)
                    Log.d(

                        "RESPONSE", "Not Successful" + "\n" + response.code
                    )
                }
            }
        })


    }

    private fun call(json: String, modificationUrl: String): Call {
        val body = json.toRequestBody(JSON)
        val request: Request = Request.Builder()
            .url(BASE_URL + modificationUrl)
            .post(body)
            .build()
        return client.newCall(request)
    }

    fun createUser(
        updateUI: (Boolean) -> Unit,
        firstName: String,
        secondName: String,
        patronymic: String,
        cardNumber: Long,
        login: String,
        password: String
    ) {


        val json = JSONObject().apply {
            put(User.CODE_CARD_NUMBER, cardNumber.toString())
            put(User.CODE_FIRST_NAME, firstName)
            put(User.CODE_LOGIN, login)
            put(User.CODE_PASSWORD, password)
            put(User.CODE_PATRONYMIC, patronymic)
            put(User.CODE_SECOND_NAME, secondName)
        }.toString()


        call(json, ADD_USER_URL).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                updateUI.invoke(false)
                Log.d("RESPONSE", e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {

//                    val obj = JSONObject(response.body?.string()!!)
//                    val editor = userSharedPref.edit()
//                    editor.putLong(User.CODE_ID, obj.getLong(User.CODE_ID))
//                    editor.putString(User.CODE_FIRST_NAME, firstName)
//                    editor.putString(User.CODE_SECOND_NAME, secondName)
//                    editor.putString(User.CODE_PATRONYMIC, patronymic)
//                    editor.putLong(User.CODE_CARD_NUMBER, cardNumber)
//                    editor.putString(User.CODE_LOGIN, login)
//                    editor.putString(User.CODE_PASSWORD, password)
//                    editor.apply()
                    updateUI.invoke(false)
                    Log.d("RESPONSE", "Successful " + response.code)
                } else {
                    updateUI.invoke(false)
                    Log.d("RESPONSE", "Not Successful " + response.code)
                }
            }
        })

    }


    fun updateUser(user: User) {
        val json = JSONObject().apply {
            put(User.CODE_LOGIN, user.id)
            put(User.CODE_PASSWORD, user.cardNumber)
        }.toString()

        call(json, UPDATE_USER).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                TODO("Not yet implemented")
            }
        })
    }


    fun updateImage(url: Uri) {


    }


}


