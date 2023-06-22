package com.daniyaliqbal.d_scrabbletimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.daniyaliqbal.d_scrabbletimer.databinding.ActivityMainBinding;

//GOING BACK TO THAT ACTIVITY? Would it restart
//Tile tracking question??
//day 13 mobile dev the basic views
// day 16 Mobile dev for the onclick listeners
//timing bucket tracker
//day 24 for alert dialogue builder- we can use that to keep track of the score
//day 27 for inningtracker


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonTimer.setOnClickListener(this);
        binding.buttonAnagramSearcher.setOnClickListener(this);
        binding.buttonWordCheck.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_timer:{
                Intent intent = new Intent(MainActivity.this, TimerActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.button_anagram_searcher:{
                Intent intent = new Intent(this, AnagramerActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.button_word_check:{
                Intent intent = new Intent(this, WordCheckerActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}