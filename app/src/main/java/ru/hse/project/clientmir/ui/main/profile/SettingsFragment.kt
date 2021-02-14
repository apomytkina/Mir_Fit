package ru.hse.project.clientmir.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.hse.project.clientmir.MainActivity
import ru.hse.project.clientmir.R


class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile_settings, container, false)

//        val actionBar = (activity as MainActivity?)!!.supportActionBar
//        actionBar!!.setHomeButtonEnabled(true)
//        actionBar!!.setDisplayHomeAsUpEnabled(true)

        return root;
    }
}