package com.daniyaliqbal.d_scrabbletimer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.daniyaliqbal.d_scrabbletimer.databinding.ActivityDictionaryBinding;

public class DictionaryActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityDictionaryBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDictionaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonNextD.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_next_d: {
                Intent intent = new Intent(this, AnagramffActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}




