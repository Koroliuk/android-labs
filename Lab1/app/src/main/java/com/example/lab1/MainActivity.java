package com.example.lab1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int TEXT_SIZE = 22;
    private static final String DEFAULT_STRING = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.inputText);
        EditText editText = findViewById(R.id.editText);
        RadioGroup radioGroup = findViewById(R.id.options);
        Button okButton = findViewById(R.id.button_ok);
        Button cancelButton = findViewById(R.id.button_cancel);

        okButton.setOnClickListener(view -> {
            String enteredText = editText.getText().toString();
            int selectedOptionId = radioGroup.getCheckedRadioButtonId();
            boolean isEnteredTextEmpty = enteredText.equals("");
            boolean isFontOptionChosen = selectedOptionId != -1;
            if (!isEnteredTextEmpty && isFontOptionChosen) {
                RadioButton radioButton = findViewById(selectedOptionId);
                Typeface typeface = radioButton.getTypeface();
                textView.setText(enteredText);
                textView.setTypeface(typeface);
                textView.setTextSize(TEXT_SIZE);
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setMessage(R.string.dialog_message)
                        .setTitle(R.string.dialog_title);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        cancelButton.setOnClickListener(view -> textView.setText(DEFAULT_STRING));
    }
}