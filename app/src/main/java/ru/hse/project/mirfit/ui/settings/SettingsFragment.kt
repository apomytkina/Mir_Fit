package ru.hse.project.mirfit.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import ru.hse.project.mirfit.R


class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile_settings, container, false)


        val layContent = root.findViewById<ConstraintLayout>(R.id.prof_set_front_content)
        layContent.findViewById<ConstraintLayout>(R.id.btn_change_login_password)
            .setOnClickListener {
                DialogChoseEditFragment().show(parentFragmentManager, "EDIT_LOG_PAS")
            }

        val layNonContent = root.findViewById<ConstraintLayout>(R.id.prof_set_back_content)
        // настройка поведения нижнего экрана
        val bottomSheetBehavior = BottomSheetBehavior.from<View>(layNonContent)
        layContent.findViewById<ConstraintLayout>(R.id.btn_about_mir_fit).setOnClickListener {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
        }



        return root
    }
}