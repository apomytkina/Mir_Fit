package ru.hse.project.mirfit.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import ru.hse.project.mirfit.R
import ru.hse.project.mirfit.clientAuth.BaseClient

class ProfileEditImageFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Юзер приходит null, но в релизе всегда будет
        val root = inflater.inflate(R.layout.fragment_edit_image_profile, container, false)


        root.findViewById<ConstraintLayout>(R.id.btn_edit_image_prof).setOnClickListener {


        }

        root.findViewById<ConstraintLayout>(R.id.btn_delete_image_prof).setOnClickListener {
           // BaseClient(inflater.context).updateImage(null)

        }





        return root
    }


}