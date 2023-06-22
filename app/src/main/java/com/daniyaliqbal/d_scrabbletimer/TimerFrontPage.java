package com.daniyaliqbal.d_scrabbletimer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.daniyaliqbal.d_scrabbletimer.databinding.ActivityTimerFrontPageBinding;

public class TimerFrontPage extends AppCompatActivity implements View.OnClickListener {

    private ActivityTimerFrontPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTimerFrontPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonNextT.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_nextT: {
                Intent intent = new Intent(this, DictionaryActivity.class);
                startActivity(intent);
                break;
            }
        }

    }
}