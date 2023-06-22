package com.daniyaliqbal.d_scrabbletimer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.daniyaliqbal.d_scrabbletimer.databinding.ActivityAnagramffBinding;

public class AnagramffActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityAnagramffBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAnagramffBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonAnagramer.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_anagramer: {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
