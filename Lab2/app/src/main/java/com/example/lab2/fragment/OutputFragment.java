package com.example.lab2.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.lab2.ETextFont;
import com.example.lab2.R;


public class OutputFragment extends Fragment {

    private static final String ARG_TEXT = "text";
    private static final String ARG_FONT = "font";

    private String mText;
    private String mFont;

    public OutputFragment() {

    }

    public static OutputFragment newInstance(String text, String font) {
        OutputFragment fragment = new OutputFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        args.putString(ARG_FONT, font);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mText = getArguments().getString(ARG_TEXT);
            mFont = getArguments().getString(ARG_FONT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_output, container, false);
        TextView textView = view.findViewById(R.id.infoMessage);
        ETextFont eTextFont = ETextFont.getTextFontByName(mFont);
        if (eTextFont != null) {
            Typeface typeface = ETextFont.getTextFontByName(mFont).getTypeface();
            textView.setText(mText);
            textView.setTextSize(22);
            textView.setTypeface(typeface);
        }
        return view;
    }
}