package com.example.notebook


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.Navigation


open class Dailog : DialogFragment(){


    lateinit var  radioGroup: RadioGroup
    lateinit var  radioButton: RadioButton


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView :View =inflater.inflate(R.layout.category_items,container,false)
          radioGroup = rootView.findViewById(R.id.radioCategoryItem)

        rootView.findViewById<TextView>(R.id.cancel_button).setOnClickListener {
            dismiss()
        }
         rootView.findViewById<TextView>(R.id.submit_button).setOnClickListener {
             try {
                 val selectedId = radioGroup.checkedRadioButtonId
                 val radio  = rootView.findViewById<RadioButton>(selectedId)
                 val  selectedString= radio.text.toString()

                 setFragmentResult(AppConstants.REQUEST_KEY,
                     bundleOf(AppConstants.BUNDLE_KEY to selectedString))
                 dismiss()

                }
             catch (e:java.lang.Exception)
             {
                 Toast.makeText(requireContext(), "Please select any one", Toast.LENGTH_SHORT).show()
                 dismiss()
             }
             
             
             dismiss()


         }
        return  rootView


    }

}