package com.daniyaliqbal.d_scrabbletimer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.daniyaliqbal.d_scrabbletimer.databinding.ActivityFirstBinding;


public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityFirstBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonNext.setOnClickListener(this);
        binding.textViewSkip.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_next: {
                Intent intent = new Intent(this, TimerFrontPage.class);
                startActivity(intent);
                break;
            }
            case R.id.textView_skip:{
                Intent intent= new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
