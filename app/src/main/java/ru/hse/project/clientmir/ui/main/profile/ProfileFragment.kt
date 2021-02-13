package ru.hse.project.clientmir.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.hse.project.clientmir.R
import ru.hse.project.clientmir.clientAuth.BaseClientAuth
import ru.hse.project.clientmir.clientAuth.User

class ProfileFragment : Fragment() {

    private var user: User? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        user = BaseClientAuth(inflater.context).currentUser
        val root = inflater.inflate(R.layout.fragment_profile, container, false)


        val data = arrayListOf(CardObject("123","122222222"))
        val postAdapter = CardAdapter(data)

        root.findViewById<RecyclerView>(R.id.card_recycler)?.apply {
            layoutManager = LinearLayoutManager(root.context)
            adapter = postAdapter
        }


        return root
    }
}