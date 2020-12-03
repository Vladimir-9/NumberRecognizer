package com.google.mlkit.codelab.translate

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.shape.MaterialShapeDrawable
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class MyDialogFragment : DialogFragment() {

    companion object {
        const val KEY_PUT_LIST_CAR_NUMBER = "keyPutListCarNumber"
        const val KEY_PUT_CAR_NUMBER = "keyPutKarNumber"
        private var itemCarNumber = 1

        fun saveArgumentMyDialogFragment(
            listCarNumber: ArrayList<String>,
            carNumber: String
        ): MyDialogFragment {
            return MyDialogFragment().withArguments {
                putStringArrayList(KEY_PUT_LIST_CAR_NUMBER, listCarNumber)
                putString(KEY_PUT_CAR_NUMBER, carNumber)
            }
        }
    }

    private val materialShapeDrawable: MaterialShapeDrawable = MaterialShapeDrawable()
    private lateinit var listCarNumber: ArrayList<String>
    private var carNumber: String = ""
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val layoutInflater = LayoutInflater.from(requireContext())
        val customDialog: View =
            layoutInflater.inflate(R.layout.custom_view_alert_dialog, null)
        val editText = customDialog.findViewById<EditText>(R.id.et_corrected_number)
        if (arguments != null) {
            listCarNumber =
                requireArguments().getStringArrayList(KEY_PUT_LIST_CAR_NUMBER)!!
            carNumber = requireArguments().getString(KEY_PUT_CAR_NUMBER)!!
        }
        materialShapeDrawable.setCornerSize(44F)
        val dialog = MaterialAlertDialogBuilder(
            requireContext(),
            R.style.MyTheme_MaterialComponents_MaterialAlertDialog
        )
        dialog.apply {
            setView(customDialog)
            background = materialShapeDrawable
            editText.setText(carNumber)
            setPositiveButton("Сохранить") { _, _ ->
                itemCarNumber = listCarNumber.size + 1
                listCarNumber.add(
                    "$itemCarNumber - " + editText.text
                        .toString() + "       " + currentDate()
                )
            }
            setNegativeButton("Отмена") { dialog, _ -> dialog.cancel() }
        }
        return dialog.create()
    }

    private fun currentDate(): String {
        val currentDate = Date()
        val dateFormat: DateFormat =
            SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return dateFormat.format(currentDate)
    }
}