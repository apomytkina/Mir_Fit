package ru.hse.project.mirfit.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.hse.project.mirfit.MainActivity
import ru.hse.project.mirfit.R
import ru.hse.project.mirfit.clientAuth.BaseClient

class AuthActivity : AppCompatActivity() {


    private lateinit var client: BaseClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        client = BaseClient(this)
    }

    override fun onStart() {
        super.onStart()
        val currentUser = client.currentUser
        if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finishAfterTransition()
        }
    }
}