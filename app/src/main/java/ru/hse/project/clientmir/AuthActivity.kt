package ru.hse.project.clientmir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.hse.project.clientmir.clientAuth.BaseClientAuth

class AuthActivity : AppCompatActivity() {


    lateinit var clientAuth: BaseClientAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        clientAuth = BaseClientAuth(this);

    }

    override fun onStart() {
        super.onStart()

//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
//        finishAfterTransition()

        val currentUser = clientAuth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finishAfterTransition()
        }
    }


}