package ru.hse.project.mirfit.ui.auth

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import ru.hse.project.mirfit.MainActivity
import ru.hse.project.mirfit.R
import ru.hse.project.mirfit.clientAuth.BaseClient


class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val policy: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        client = BaseClient(this)

        val dd = 13

    }

    override fun onStart() {
        super.onStart()
        val currentUser = client.currentUser
        if (currentUser != null) {
            client.authUserFromStorage().addOnSuccessListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finishAfterTransition()
            }.addOnFailureListener {
                //тост
            }
        }
    }

    companion object {
        lateinit var client: BaseClient
    }
}