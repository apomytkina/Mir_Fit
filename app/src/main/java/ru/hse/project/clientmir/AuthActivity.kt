package ru.hse.project.clientmir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.hse.project.clientmir.appClient.BaseAuth

class AuthActivity : AppCompatActivity() {


    lateinit var auth: BaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        auth = BaseAuth(this);

    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finishAfterTransition()
        }
    }


}