package ru.hse.project.clientmir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.hse.project.clientmir.clientAuth.BaseClient

class AuthActivity : AppCompatActivity() {


    lateinit var client: BaseClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        client = BaseClient(this);

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