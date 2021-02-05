package ru.hse.project.clientmir

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.hse.project.clientmir.webcient.BaseAuth
import ru.hse.project.clientmir.webcient.User

class MainActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var auth: BaseAuth;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = BaseAuth(this);
        auth.createUserWithEmailAndPassword("alex", "123")
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    override fun onClick(it: View) {

    }


    private fun registration() {

    }

    private fun sign() {

    }

    private fun updateUI(currentUser: User?) {

        //Пользователь был ранее авторизован
        if (currentUser != null) {
            setContentView(R.layout.activity_main)
            val navView: BottomNavigationView = findViewById(R.id.nav_view)
            val navController = findNavController(R.id.nav_host_fragment)
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            val appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_profile
                )
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
        }
        // Пользоавтель еще не авторизовался
        else {
            setContentView(R.layout.activity_auth)
        }
    }


}

