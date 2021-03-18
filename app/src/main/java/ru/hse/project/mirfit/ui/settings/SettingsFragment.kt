package ru.hse.project.mirfit.ui.settings

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat.finishAfterTransition
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import ru.hse.project.mirfit.MainActivity
import ru.hse.project.mirfit.R
import ru.hse.project.mirfit.ui.auth.AuthActivity


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


        layContent.findViewById<ConstraintLayout>(R.id.btn_sign_out)
            .setOnClickListener {
                AuthActivity.client.signOut()
                val intent = Intent(context, AuthActivity::class.java)
                startActivity(intent)
                if (activity != null) {
                    activity?.finishAfterTransition()
                }
            }


        layContent.findViewById<ConstraintLayout>(R.id.btn_delete_user)
            .setOnClickListener {
                AlertDialog.Builder(context)
                    .setMessage("Вы точно хотите удалить свой аккаунт?\nВосстановить нельзя его будет!")
                    .setNegativeButton("Отмена") { dialogInterface: DialogInterface, i: Int -> dialogInterface.dismiss() }
                    .setPositiveButton("Подтвердить") { dialogInterface: DialogInterface, i: Int ->
                        dialogInterface.dismiss()
                        AuthActivity.client.deleteUser().addOnSuccessListener {
                            val intent = Intent(context, AuthActivity::class.java)
                            startActivity(intent)
                            if (activity != null) {
                                activity?.finishAfterTransition()
                            }
                        }.addOnFailureListener {
                            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                    .create()
                    .show()
            }

        val layNonContent = root.findViewById<ConstraintLayout>(R.id.prof_set_back_content)
        // настройка поведения нижнего экрана
        val bottomSheetBehavior = BottomSheetBehavior.from<View>(layNonContent)
        layContent.findViewById<ConstraintLayout>(R.id.btn_about_mir_fit)
            .setOnClickListener {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
            }



        return root
    }
}