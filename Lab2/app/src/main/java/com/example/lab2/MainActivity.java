package com.example.lab2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.lab2.fragment.InputFragment;
import com.example.lab2.fragment.OutputFragment;


public class MainActivity extends AppCompatActivity implements InputFragment.OnInputFormSubmit {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onInputFormSubmit(String text, String font) {
        OutputFragment outputFragment = OutputFragment.newInstance(text, font);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.output_fragment, outputFragment)
                .commit();
    }
}