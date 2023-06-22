package com.daniyaliqbal.d_scrabbletimer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;


public class TileTrackingActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private TextView textView;
    private Map<Character, Integer> lettersMap;

    private static final String LETTERS_MAP_KEY = "letters_map";
    private static final String SHARED_PREFS_NAME = "my_shared_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_tracking);

        editText = findViewById(R.id.edit_text);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.text_view);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
        if (prefs.contains(LETTERS_MAP_KEY)) {
            String lettersMapJson = prefs.getString(LETTERS_MAP_KEY, null);
            lettersMap = new Gson().fromJson(lettersMapJson, new TypeToken<HashMap<Character, Integer>>() {}.getType());
        } else {
            lettersMap = new HashMap<>();
            lettersMap.put('A', 9);
            lettersMap.put('B', 2);
            lettersMap.put('C', 2);
            lettersMap.put('D', 4);
            lettersMap.put('E', 12);
            lettersMap.put('F', 2);
            lettersMap.put('G', 3);
            lettersMap.put('H', 2);
            lettersMap.put('I', 9);
            lettersMap.put('J', 1);
            lettersMap.put('K', 1);
            lettersMap.put('L', 4);
            lettersMap.put('M', 2);
            lettersMap.put('N', 6);
            lettersMap.put('O', 7);
            lettersMap.put('P', 2);
            lettersMap.put('Q', 1);
            lettersMap.put('R', 6);
            lettersMap.put('S', 4);
            lettersMap.put('T', 6);
            lettersMap.put('U', 4);
            lettersMap.put('V', 2);
            lettersMap.put('W', 2);
            lettersMap.put('X', 1);
            lettersMap.put('Y', 2);
            lettersMap.put('Z', 1);
            lettersMap.put('.', 2);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String letters = editText.getText().toString().toUpperCase();
                Map<Character, Integer> letterCounts = new HashMap<>(lettersMap);

                for (int i = 0; i < letters.length(); i++) {
                    char c = letters.charAt(i);
                    if (letterCounts.containsKey(c)) {
                        letterCounts.put(c, letterCounts.get(c) - 1);
                    }
                }

                StringBuilder sb = new StringBuilder();
                for (char c = 'A'; c <= 'Z'; c++) {
                    if (lettersMap.containsKey(c)) {
                        sb.append(c).append(" = ").append(letterCounts.get(c)).append("\n");
                    }
                }
                sb.append('.').append("=").append(letterCounts.get('.')).append("\n");



                textView.setText(sb.toString());
                lettersMap = letterCounts;
                saveLettersMapToPrefs();
            }
        });
    }

    private void saveLettersMapToPrefs() {
        String lettersMapJson = new Gson().toJson(lettersMap);
        SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString(LETTERS_MAP_KEY, lettersMapJson);
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveLettersMapToPrefs();
    }
}
