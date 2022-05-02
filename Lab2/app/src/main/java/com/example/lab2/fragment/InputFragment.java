package com.example.lab2.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.lab2.ETextFont;
import com.example.lab2.R;

public class InputFragment extends Fragment {

    private OnInputFormSubmit mListener;

    public interface OnInputFormSubmit {
        void onInputFormSubmit(String text, String font);
    }

    public InputFragment() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (OnInputFormSubmit) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + "must be implement OnInputFormSubmit");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        EditText editText = view.findViewById(R.id.editText);
        RadioGroup radioGroup = view.findViewById(R.id.options);
        Button okButton = view.findViewById(R.id.button_ok);
        Button cancelButton = view.findViewById(R.id.button_cancel);

        okButton.setOnClickListener(v -> {
            String enteredText = editText.getText().toString();
            int selectedOptionId = radioGroup.getCheckedRadioButtonId();

            boolean isEnteredTextEmpty = enteredText.equals("");
            boolean isFontOptionChosen = selectedOptionId != -1;

            if (!isEnteredTextEmpty && isFontOptionChosen) {
                RadioButton radioButton = view.findViewById(selectedOptionId);
                Typeface typeface = radioButton.getTypeface();
                ETextFont eTextFont = ETextFont.getTextFontByTypeface(typeface);
                if (eTextFont != null) {
                    String font = ETextFont.getTextFontByTypeface(typeface).getName();
                    mListener.onInputFormSubmit(enteredText, font);
                }
            } else {
                new AlertFragment().show(getChildFragmentManager(), AlertFragment.TAG);
            }
        });

        cancelButton.setOnClickListener(v -> mListener.onInputFormSubmit("", "monospace"));

        return view;
    }

}