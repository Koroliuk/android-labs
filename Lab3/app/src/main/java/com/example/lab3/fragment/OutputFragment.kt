package com.example.lab3.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.lab3.enumerable.ETextFont
import com.example.lab3.R

class OutputFragment : Fragment() {

    private var mText: String? = null
    private var mFont: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mText = it.getString(ARG_TEXT)
            mFont = it.getString(ARG_FONT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_output, container, false)
        val textView = view.findViewById<TextView>(R.id.input_text)
        mText?.let {
            mFont?.let {
                val typeface = ETextFont.getTextFontByName(mFont!!).typeface
                textView.text = mText
                textView.textSize = TEXT_SIZE
                textView.typeface = typeface
            }
        }
        return view
    }

    companion object {

        private const val TEXT_SIZE = 22f

        private const val ARG_TEXT = "text"
        private const val ARG_FONT = "font"

        @JvmStatic
        fun newInstance(text: String, font: String) =
            OutputFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TEXT, text)
                    putString(ARG_FONT, font)
                }
            }
    }
}