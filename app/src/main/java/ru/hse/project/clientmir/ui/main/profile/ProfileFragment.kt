package ru.hse.project.clientmir.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.hse.project.clientmir.R
import ru.hse.project.clientmir.clientAuth.BaseClientAuth
import ru.hse.project.clientmir.clientAuth.User

class ProfileFragment : Fragment() {

    private var user: User? = null
    lateinit var recycler: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Юзер приходит null, но в релизе всегда будет
        user = BaseClientAuth(inflater.context).currentUser
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

//        root.findViewById<TextView>(R.id.prof_text_full_name).text =
//            (user.secondName + " " + user.firstName)


        // data of user cards
        val data = arrayListOf(
            CardObject("0", "12031209312", "myCard"),
            CardObject("123", "2333453", "mySecondCard"),
            CardObject("123", "2333453", "mySecondCard"),
            CardObject("123", "2333453", "mySecondCard")
        )

        val postAdapter = CardAdapter(parentFragmentManager,data)

        recycler = root.findViewById(R.id.card_recycler)

        recycler.apply {
            layoutManager = LinearLayoutManager(root.context)
            adapter = postAdapter
        }


        root.findViewById<ConstraintLayout>(R.id.prof_lay_setting).setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_navigation_setting)
        }

        root.findViewById<ConstraintLayout>(R.id.prof_lay_add_card).setOnClickListener {
            DialogAddCardFragment(postAdapter).show(parentFragmentManager, "ADD_CARD")

        }

        return root
    }


    override fun onResume() {
        super.onResume()

    }

}