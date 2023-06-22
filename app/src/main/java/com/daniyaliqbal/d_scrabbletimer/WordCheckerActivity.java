package com.daniyaliqbal.d_scrabbletimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.daniyaliqbal.d_scrabbletimer.databinding.ActivityMainBinding;
import com.daniyaliqbal.d_scrabbletimer.databinding.ActivityWordCheckerBinding;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class WordCheckerActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityWordCheckerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityWordCheckerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonCheck.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_check: {
                String text = binding.textViewWords.getText().toString();
                String[] texts= text.split(",");
                try {
                    InputStream is = getAssets().open("scrabble.txt");
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    String[] words = new String(buffer).split("\n");
                    boolean found = false;
                    for (String iss : texts) {
                        found= false;
                        for (String word : words) {
                            if (word.trim().equalsIgnoreCase(iss.trim())) {
                                found = true;
                                break;
                            }
                        }
                        if (found == false)
                            break;
                    }
                    if (found) {
                        binding.textViewCorrect.setTextColor(getResources().getColor(R.color.green, getTheme()));
                        binding.textViewIncorrect.setTextColor(getResources().getColor(R.color.grey, getTheme()));
                    } else {
                        binding.textViewIncorrect.setTextColor(getResources().getColor(R.color.red, getTheme()));
                        binding.textViewCorrect.setTextColor(getResources().getColor(R.color.grey, getTheme()));
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    binding.textViewIncorrect.setTextColor(getResources().getColor(R.color.red, getTheme()));
                }
            }
        }
    }

}




