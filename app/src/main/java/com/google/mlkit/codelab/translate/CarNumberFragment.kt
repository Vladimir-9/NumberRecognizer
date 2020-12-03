package com.google.mlkit.codelab.translate

import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.*

class CarNumberFragment : Fragment(R.layout.car_number_fragment) {

    companion object {
        private const val RETURN_LIST_CAR_NUMBER = "putListCarNumber"

        fun saveArgumentCarNumberFragment(
            listCarNumber: ArrayList<String>
        ): CarNumberFragment {
            return CarNumberFragment().withArguments {
                putStringArrayList(
                    RETURN_LIST_CAR_NUMBER, listCarNumber
                )
            }
        }
    }

    private lateinit var arrayAdapterNumber: ArrayAdapter<String>
    private var listViewPosition = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listCarNumber: ArrayList<String> =
            requireArguments().getStringArrayList(RETURN_LIST_CAR_NUMBER)!!
        val button: Button = view.findViewById(R.id.bt_delete)
        val listViewNumber: ListView =
            view.findViewById(R.id.listViewNumber)

        arrayAdapterNumber =
            ArrayAdapter(requireContext(), R.layout.list_car_item_single_choice, listCarNumber)
        listViewNumber.adapter = arrayAdapterNumber

        listViewNumber.onItemClickListener =
            OnItemClickListener { _, _, position, _ -> listViewPosition = position }

        button.setOnClickListener {
            if (listCarNumber.isEmpty()) {
                toast("Список пуст")
            } else {
                if (listViewPosition < listCarNumber.size) {
                    listCarNumber.removeAt(listViewPosition)
                    arrayAdapterNumber.notifyDataSetChanged()
                } else {
                    toast("Вы ничего, не выбрали")
                }
            }
        }
    }

    private fun toast(massage: String) {
        Toast.makeText(requireContext(), massage, Toast.LENGTH_SHORT).show()
    }
}