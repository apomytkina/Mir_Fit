package ru.hse.project.mirfit.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.hse.project.mirfit.R
import ru.hse.project.mirfit.clientAuth.BaseClient
import ru.hse.project.mirfit.ui.auth.AuthActivity
import ru.hse.project.mirfit.ui.profile.card.CardAdapter
import ru.hse.project.mirfit.ui.profile.card.CardObject
import ru.hse.project.mirfit.ui.profile.card.DialogAddCardFragment

class ProfileFragment : Fragment() {

    private lateinit var user: BaseClient.User
    lateinit var recycler: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Юзер приходит null, но в релизе всегда будет
        user = AuthActivity.client.currentUser!!
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        root.findViewById<TextView>(R.id.prof_text_full_name).text =
            (user.firstName + " " + user.secondName)



        root.findViewById<CardView>(R.id.profileImageContainer).setOnClickListener {
            // findNavController().navigate(R.id.navigation_edit_image)
        }

        val data = arrayListOf<CardObject>()
        data.addAll(user.cards!!)

        // data of user cards
        val postAdapter = CardAdapter(parentFragmentManager, data)

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

}