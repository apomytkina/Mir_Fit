package ru.hse.project.mirfit.clientAuth

import android.content.Context
import android.content.SharedPreferences
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.TaskCompletionSource
import com.google.android.gms.tasks.Tasks
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import ru.hse.project.mirfit.ui.profile.card.CardObject
import ru.hse.project.mirfit.util.JsonParser
import java.io.IOException
import kotlin.coroutines.coroutineContext


class BaseClient(context: Context) {

    companion object {
        private const val PRIVATE_MODE = 0
        private const val PREF_FILE = "json_user"
        private val JSON = "application/json; charset=utf-8".toMediaType()
        private const val HOST_IP = "192.168.31.121" // 192.168.31.121
        private const val BASE_URL = "http://$HOST_IP:8080"
        private const val USER_CONTROLLER = "/users"
        private const val CARD_CONTROLLER = "/cards"
        private const val CARD_ADD = "$CARD_CONTROLLER/add"
        private const val CREATE_USER = "$USER_CONTROLLER/addUser"
        private const val AUTH_USER = "$USER_CONTROLLER/authUser"
        private const val UPDATE_LOGIN = "$USER_CONTROLLER/updateLogin"
        private const val UPDATE_PASSWORD = "$USER_CONTROLLER/updatePassword"
        private const val DELETE_USER = "$USER_CONTROLLER/delete"

    }

    private val client: OkHttpClient = OkHttpClient()
    private val userSharedPref = context.getSharedPreferences(PREF_FILE, PRIVATE_MODE)
    var currentUser: User? = User.Builder(userSharedPref, this).build()


    fun addCard(card: CardObject): Task<Void> {
        val task = TaskCompletionSource<Void>()

        val json = JSONObject().apply {
            put("card",
                JSONObject().apply {
                    put(CardObject.CODE_NAME, card.cardName)
                    put(CardObject.CODE_NUMBER, card.cardNumber)
                })
            put(CardObject.CODE_USER_ID, card.userId)
        }.toString()


        val body = json.toRequestBody(JSON)
        val request: Request = Request.Builder()
            .url(BASE_URL + CARD_ADD)
            .post(body)
            .build()


        call(request).addOnSuccessListener {
            task.setResult(null)
        }.addOnFailureListener {
            task.setException(it)
        }
        return task.task
    }

    fun editCard(card: CardObject, newName: String): Task<Void> {
        val task = TaskCompletionSource<Void>()

        val json = JSONObject().apply {
            put(CardObject.CODE_NAME, newName)
            put(User.CODE_CARD_NUMBER, card.cardNumber)
        }.toString()


        val body = json.toRequestBody(JSON)
        val request: Request = Request.Builder()
            .url(BASE_URL + CARD_CONTROLLER)
            .patch(body)
            .build()

        call(request).addOnSuccessListener {
            task.setResult(null)
        }.addOnFailureListener {
            task.setException(it)
        }

        return task.task
    }

    fun deleteCard(card: CardObject): Task<Void> {
        val task = TaskCompletionSource<Void>()
        val cardNumber = card.cardNumber

        val request = Request.Builder()
            .url("$BASE_URL$CARD_CONTROLLER/$cardNumber")
            .delete()
            .build()



        call(request).addOnSuccessListener {
            task.setResult(null)
        }.addOnFailureListener {
            task.setException(it)
        }

        return task.task
    }


    fun authUserFromStorage(): Task<Void> {
        val id = currentUser!!.id
        val requestUser: Request = Request.Builder()
            .url("$BASE_URL$USER_CONTROLLER/$id")
            .get()
            .build()
        val requestCard: Request = Request.Builder()
            .url("$BASE_URL$CARD_CONTROLLER/$id")
            .get()
            .build()

        val taskGetInfoUser = call(requestUser)
        val taskGetCardsUser = call(requestCard)
        val tasks = Tasks.whenAll(taskGetCardsUser, taskGetInfoUser)

        tasks.addOnSuccessListener {
            val infoUser = taskGetInfoUser.result!!
            val cardsUser = taskGetCardsUser.result!!
            val obj = JSONObject(infoUser.body?.string()!!)
            val fistName = obj.getString(User.CODE_FIRST_NAME)
            val secondName = obj.getString(User.CODE_SECOND_NAME)
            val patronymic = obj.getString(User.CODE_PATRONYMIC)
            val cards = JsonParser.parseCards(JSONArray(cardsUser.body?.string()))
            val login = obj.getString(User.CODE_LOGIN)
            val password = obj.getString(User.CODE_PASSWORD)

            currentUser = User.Builder(userSharedPref, this)
                .firstName(fistName)
                .secondName(secondName)
                .patronymic(patronymic)
                .cards(cards)
                .login(login)
                .password(password)
                .build()

        }

        return tasks
    }


    fun signInUser(
        login: String,
        password: String
    ): Task<Void> {
        val task = TaskCompletionSource<Void>()

        val json = JSONObject().apply {
            put(User.CODE_LOGIN, login)
            put(User.CODE_PASSWORD, password)
        }.toString()


        val body = json.toRequestBody(JSON)
        val requestUser: Request = Request.Builder()
            .url(BASE_URL + AUTH_USER)
            .post(body)
            .build()

        call(requestUser).addOnSuccessListener { response ->
            val obj = JSONObject(response.body?.string()!!)
            val id = obj.getString(User.CODE_ID)

            val requestCard: Request = Request.Builder()
                .url("$BASE_URL$CARD_CONTROLLER/$id")
                .get()
                .build()

            call(requestCard).addOnSuccessListener {

                val editor = userSharedPref.edit()
                editor.putString(User.CODE_ID, id)
                editor.apply()

                val fistName = obj.getString(User.CODE_FIRST_NAME)
                val secondName = obj.getString(User.CODE_SECOND_NAME)
                val patronymic = obj.getString(User.CODE_PATRONYMIC)
                val cards = JsonParser.parseCards(JSONArray(it.body?.string()))

                currentUser = User.Builder(userSharedPref, this)
                    .firstName(fistName)
                    .secondName(secondName)
                    .patronymic(patronymic)
                    .cards(cards)
                    .login(login)
                    .password(password)
                    .build()

                task.setResult(null)
            }.addOnFailureListener {
                task.setException(it)
            }
        }.addOnFailureListener {
            task.setException(it)
        }


        return task.task
    }


    fun createUser(
        firstName: String,
        secondName: String,
        patronymic: String,
        login: String,
        password: String
    ): Task<Void> {
        val task = TaskCompletionSource<Void>()

        val json = JSONObject().apply {
            put(User.CODE_FIRST_NAME, firstName)
            put(User.CODE_LOGIN, login)
            put(User.CODE_PASSWORD, password)
            put(User.CODE_PATRONYMIC, patronymic)
            put(User.CODE_SECOND_NAME, secondName)
        }.toString()


        val body = json.toRequestBody(JSON)
        val request: Request = Request.Builder()
            .url(BASE_URL + CREATE_USER)
            .post(body)
            .build()

        call(request).addOnSuccessListener {

            val id = it.body?.string()!!.trim('\"')
            val editor = userSharedPref.edit()
            editor.putString(User.CODE_ID, id)
            editor.apply()
            currentUser = User.Builder(userSharedPref, this)
                .firstName(firstName)
                .secondName(secondName)
                .patronymic(patronymic)
                .cards(arrayListOf())
                .login(login)
                .password(password)
                .build()

            task.setResult(null)
        }.addOnFailureListener {
            task.setException(it)
        }

        return task.task
    }


    private fun getCardsFromService(): Task<ArrayList<CardObject>> {
        val task = TaskCompletionSource<ArrayList<CardObject>>()
        val id = currentUser!!.id
        val requestCard: Request = Request.Builder()
            .url("$BASE_URL$CARD_CONTROLLER/$id")
            .get()
            .build()
        call(requestCard).addOnSuccessListener {
            task.setResult(JsonParser.parseCards(JSONArray(it.body?.string())))
        }.addOnFailureListener {
            task.setException(it);
        }
        return task.task;
    }

    private fun call(request: Request): Task<Response> {
        val task = TaskCompletionSource<Response>()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                task.setException(e)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    task.setResult(response)
                } else {
                    val obj = JSONObject(response.body?.string()!!)
                    val message = obj.getString("message")
                    task.setException(Exception(message))
                }
            }
        })
        return task.task
    }

    private fun updateLogin(newLogin: String, id: String): Task<Void> {
        val task = TaskCompletionSource<Void>()

        val json = JSONObject().apply {
            put(User.CODE_ID, id)
            put(User.CODE_LOGIN, newLogin)
        }.toString()

        val body = json.toRequestBody(JSON)
        val request: Request = Request.Builder()
            .url(BASE_URL + UPDATE_LOGIN)
            .patch(body)
            .build()
        call(request).addOnSuccessListener {
            task.setResult(null)
        }.addOnFailureListener {
            task.setException(it)
        }

        return task.task
    }

    private fun updatePassword(newPassword: String, id: String): Task<Void> {
        val task = TaskCompletionSource<Void>()

        val json = JSONObject().apply {
            put(User.CODE_ID, id)
            put(User.CODE_PASSWORD, newPassword)
        }.toString()

        val body = json.toRequestBody(JSON)
        val request: Request = Request.Builder()
            .url(BASE_URL + UPDATE_PASSWORD)
            .patch(body)
            .build()

        call(request).addOnSuccessListener {
            task.setResult(null)
        }.addOnFailureListener {
            task.setException(it)
        }

        return task.task
    }

    fun signOut() {
        val editor = userSharedPref.edit()
        editor.remove(User.CODE_ID)
        editor.apply()
        currentUser = null
    }

    fun deleteUser(): Task<Void> {
        val task = TaskCompletionSource<Void>()
        val id = currentUser!!.id
        val request = Request.Builder()
            .url("$BASE_URL$DELETE_USER/$id")
            .get()
            .build()
        call(request).addOnSuccessListener {
            signOut()
            task.setResult(null)
        }.addOnFailureListener {
            task.setException(it)
        }
        return task.task
    }


    class User(builder: Builder) {
        fun addCard(card: CardObject): Task<Void> {
            val task = baseClient.addCard(card)
            task.addOnSuccessListener {
                cards!!.add(card)
            }
            return task
        }

        fun editCard(newName: String, position: Int): Task<Void> {
            val card = cards!![position]
            val task = baseClient.editCard(card, newName)
            task.addOnSuccessListener {
                card.cardName = newName
            }
            return task
        }


        fun deleteCard(card: CardObject): Task<Void> {
            val task = baseClient.deleteCard(card)
            task.addOnSuccessListener {
                cards!!.remove(card)
            }
            return task
        }

        fun updateLogin(newLogin: String): Task<Void> {
            val task = baseClient.updateLogin(newLogin, id)

            task.addOnSuccessListener {
                baseClient.currentUser = User.Builder(baseClient.userSharedPref, baseClient)
                    .firstName(firstName!!)
                    .secondName(secondName!!)
                    .patronymic(patronymic!!)
                    .cards(cards!!)
                    .login(newLogin)
                    .password(password!!)
                    .build()
            }

            return task
        }

        fun updatePassword(newPassword: String): Task<Void> {
            val task = baseClient.updatePassword(newPassword, id)

            task.addOnSuccessListener {
                baseClient.currentUser = User.Builder(baseClient.userSharedPref, baseClient)
                    .firstName(firstName!!)
                    .secondName(secondName!!)
                    .patronymic(patronymic!!)
                    .cards(cards!!)
                    .login(login!!)
                    .password(newPassword)
                    .build()
            }

            return task
        }

        fun refreshCards(): Task<Void> {
            val task = TaskCompletionSource<Void>()

            baseClient.getCardsFromService().addOnSuccessListener {
                cards!!.clear()
                cards.addAll(it)
                task.setResult(null)
            }.addOnFailureListener {
                task.setException(it)
            }

            return task.task
        }

        private val baseClient: BaseClient = builder.baseClient
        val id: String = builder.id!!
        val firstName: String? = builder.firstName
        val secondName: String? = builder.secondName
        val patronymic: String? = builder.patronymic
        val cards: ArrayList<CardObject>? = builder.cards
        val login: String? = builder.login
        val password: String? = builder.password


        class Builder(sharedPreferences: SharedPreferences, val baseClient: BaseClient) {
            val id: String? = sharedPreferences.getString(CODE_ID, null)
            var firstName: String? = null
            var secondName: String? = null
            var patronymic: String? = null
            var cards: ArrayList<CardObject>? = null
            var login: String? = null
            var password: String? = null


            fun firstName(firstName: String) = apply {
                this.firstName = firstName
            }

            fun secondName(secondName: String) = apply {
                this.secondName = secondName
            }

            fun patronymic(patronymic: String) = apply {
                this.patronymic = patronymic
            }

            fun cards(cards: ArrayList<CardObject>) = apply {
                this.cards = cards
            }

            fun login(login: String) = apply {
                this.login = login
            }

            fun password(password: String) = apply {
                this.password = password
            }


            fun build() = if (id == null) null else User(this)
        }

        companion object {
            const val CODE_ID = "id"
            const val CODE_FIRST_NAME = "firstName"
            const val CODE_SECOND_NAME = "secondName"
            const val CODE_PATRONYMIC = "patronymic"
            const val CODE_CARD_NUMBER = "cardNumber"
            const val CODE_LOGIN = "login"
            const val CODE_PASSWORD = "password"
        }
    }

}




