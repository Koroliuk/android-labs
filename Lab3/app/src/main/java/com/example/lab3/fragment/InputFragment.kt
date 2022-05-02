package com.example.lab3.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.example.lab3.enumerable.ETextFont
import com.example.lab3.R

class InputFragment : Fragment() {

    private lateinit var mListener: FormSubmitListener;

    interface FormSubmitListener {
        fun onFormSubmit(text: String, font: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mListener = context as FormSubmitListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement OnInputFormSubmit")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_input, container, false);

        val okButton = view.findViewById<Button>(R.id.button_ok)
        val cancelButton = view.findViewById<Button>(R.id.button_cancel)
        val editText = view.findViewById<EditText>(R.id.edit_text)
        val radioGroup = view.findViewById<RadioGroup>(R.id.options)

        okButton.setOnClickListener {
            val enteredText = editText.text.toString()
            val selectedOptionId = radioGroup.checkedRadioButtonId
            val isEnteredTextEmpty = enteredText == ""
            val isFontOptionChosen = selectedOptionId != -1
            if (!isEnteredTextEmpty && isFontOptionChosen) {
                val radioButton = view.findViewById<RadioButton>(selectedOptionId)
                val typeface = radioButton.typeface
                val font: String = ETextFont.getTextFontByTypeface(typeface).fontName
                mListener.onFormSubmit(enteredText, font)
            } else {
                AlertFragment().show(childFragmentManager, AlertFragment.TAG)
            }
        }

        cancelButton.setOnClickListener {
            mListener.onFormSubmit(
                "",
                "monospace"
            )
        }

        return view
    }
}