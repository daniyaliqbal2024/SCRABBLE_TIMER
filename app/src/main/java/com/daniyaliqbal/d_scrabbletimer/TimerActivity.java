package com.daniyaliqbal.d_scrabbletimer;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

import com.daniyaliqbal.d_scrabbletimer.databinding.ActivityTimerBinding;

public class TimerActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private ActivityTimerBinding binding;

    private static final int DEFAULT_NUM_MINS = 25;
    private static final int MILLIS_PER_MIN = 60000;
    private static final int MILLIS_PER_SEC = 1000;
    private static final int SECS_PER_MIN = 60;
    private CountDownTimer countDownTimer1;
    private CountDownTimer countDownTimer2;

    private long timeRemaining1 = DEFAULT_NUM_MINS * MILLIS_PER_MIN;
    private long timeRemaining2 = DEFAULT_NUM_MINS * MILLIS_PER_MIN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTimerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.switchPlayer1.setOnCheckedChangeListener(this);
        binding.switchPlayer2.setOnCheckedChangeListener(this);
        binding.switchGameClock.setOnCheckedChangeListener(this);
        binding.buttonAdd1.setOnClickListener(this);
        binding.buttonAdd2.setOnClickListener(this);
        binding.buttonWordChecker.setOnClickListener(this);
        binding.buttonTileTracker.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_word_checker: {
                Intent intent = new Intent(this, WordCheckerActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.button_tile_tracker:{
                Intent intent= new Intent(this, TileTrackingActivity.class);
                startActivity(intent);
                break;
            }
        }
        switch (v.getId()) {
            case R.id.button_add1: {
                if (!TextUtils.isEmpty(binding.editTextNumberMoveScore1.getText().toString())) {
                    String editTextMove1 = binding.editTextNumberMoveScore1.getText().toString();
                    int totalScore = 0;
                    int score = Integer.valueOf(editTextMove1);
                    if (!TextUtils.isEmpty(binding.editTextNumberTotalScore1.getText().toString())) {
                        int currentScore = Integer.valueOf(binding.editTextNumberTotalScore1.getText().toString());
                        totalScore = currentScore + score;
                    } else {
                        int currentScore = 0;
                        totalScore = currentScore + score;
                    }
                    binding.editTextNumberTotalScore1.setText(String.valueOf(totalScore));
                }
            }
            case R.id.button_add2: {
                if (!TextUtils.isEmpty(binding.editTextNumberMoveScore2.getText().toString())) {
                    String editTextMove2 = binding.editTextNumberMoveScore2.getText().toString();
                    int totalScore = 0;
                    int score = Integer.valueOf(editTextMove2);
                    if (!TextUtils.isEmpty(binding.editTextNumberTotalScore2.getText().toString())) {
                        int currentScore = Integer.valueOf(binding.editTextNumberTotalScore2.getText().toString());
                        totalScore = currentScore + score;
                    } else {
                        int currentScore = 0;
                        totalScore = currentScore + score;
                    }
                    binding.editTextNumberTotalScore2.setText(String.valueOf(totalScore));
                }
            }

        }
    }

    public void changeTime1Settings() {
        binding.textViewTimeRemaining1.setTextColor(getResources().getColor(R.color.red, getTheme()));
        binding.textViewTimeRemaining2.setTextColor(getResources().getColor(R.color.black, getTheme()));

    }

    public void changeTime2Settings() {
        binding.textViewTimeRemaining2.setTextColor(getResources().getColor(R.color.red, getTheme()));
        binding.textViewTimeRemaining1.setTextColor(getResources().getColor(R.color.black, getTheme()));

    }

    public CountDownTimer getNewTimer1(long timerTotalLength, long timerTickLength) {
        return new CountDownTimer(timerTotalLength, timerTickLength) {
            @Override
            public void onTick(long l) {
                long minuteValue = (l / MILLIS_PER_SEC) / SECS_PER_MIN;
                long secondValue = (l / MILLIS_PER_SEC) % SECS_PER_MIN;

                timeRemaining1 = timeRemaining1 - MILLIS_PER_SEC;
                binding.textViewTimeRemaining1.setText(minuteValue + ":" + secondValue);
            }

            @Override
            public void onFinish() {
            }
        };
    }

    public CountDownTimer getNewTimer2(long timerTotalLength, long timerTickLength) {
        return new CountDownTimer(timerTotalLength, timerTickLength) {
            @Override
            public void onTick(long l) {
                long minuteValue = (l / MILLIS_PER_SEC) / SECS_PER_MIN;
                long secondValue = (l / MILLIS_PER_SEC) % SECS_PER_MIN;

                timeRemaining2 = timeRemaining2 - MILLIS_PER_SEC;
                binding.textViewTimeRemaining2.setText(minuteValue + ":" + secondValue);
            }

            @Override
            public void onFinish() {
            }
        };
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {


            case R.id.switch_player2: {
                    if (binding.switchPlayer2.isChecked()) {
                    binding.switchPlayer1.setChecked(false);
                    changeTime1Settings();
                    countDownTimer1 = getNewTimer1(timeRemaining1, MILLIS_PER_SEC);
                    countDownTimer1.start();
                } else {
                    String label = binding.textViewTimeRemaining1.getText().toString();
                    String[] temp = label.split(":");
                    long minutesRemaining = Integer.valueOf(temp[0]) * MILLIS_PER_MIN; //0 denotes the first index in our temp array
                    long secondsRemaining = Integer.valueOf(temp[1]) * MILLIS_PER_SEC; //1 denotes the second index in our temp array
                    timeRemaining1 = minutesRemaining + secondsRemaining;
                    countDownTimer1.cancel();

            }
        }
    }

        switch(buttonView.getId()) {

            case R.id.switch_player1: {
                    if (binding.switchPlayer1.isChecked()) {
                        binding.switchPlayer2.setChecked(false);
                        changeTime2Settings();
                        countDownTimer2 = getNewTimer2(timeRemaining2, MILLIS_PER_SEC);
                        countDownTimer2.start();
                    } else {

                        String label = binding.textViewTimeRemaining2.getText().toString();
                        String[] temp = label.split(":");
                        long minutesRemaining = Integer.valueOf(temp[0]) * MILLIS_PER_MIN; //0 denotes the first index in our temp array
                        long secondsRemaining = Integer.valueOf(temp[1]) * MILLIS_PER_SEC; //1 denotes the second index in our temp array
                        timeRemaining2 = minutesRemaining + secondsRemaining;
                        countDownTimer2.cancel();
                    }
                }
            }



        switch(buttonView.getId()) {

            case R.id.switch_gameClock: {
                if (binding.switchGameClock.isChecked()) {
                    if (binding.switchPlayer1.isChecked()) {
                        binding.switchPlayer1.setChecked(false);
                    }
                    if (binding.switchPlayer2.isChecked()) {
                        binding.switchPlayer2.setChecked(false);
                    }
                }
                }
            }

        }

    }

