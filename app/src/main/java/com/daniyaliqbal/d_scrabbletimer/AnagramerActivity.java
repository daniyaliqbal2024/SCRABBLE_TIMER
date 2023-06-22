package com.daniyaliqbal.d_scrabbletimer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;




public class AnagramerActivity extends AppCompatActivity {

    private EditText inputEditText;
    private Button searchButton;
    private TextView resultTextView;

    private Set<String> wordSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anagramer);

        inputEditText = findViewById(R.id.input_text);
        searchButton = findViewById(R.id.submit_button);
        resultTextView = findViewById(R.id.output_text);

        wordSet = loadWordSet(); // use a HashSet for faster lookup

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputWord = inputEditText.getText().toString().toUpperCase();
                Set<String> anagrams = generateAnagrams(inputWord);
                List<String> matchingWords = filterValidWords(anagrams);

                if (matchingWords.isEmpty()) {
                    resultTextView.setText("No matching words found.");
                } else {
                    String result = String.join("\n", matchingWords);
                    resultTextView.setText(result);
                }
            }
        });
    }

    private Set<String> loadWordSet() {
        Set<String> wordSet = new HashSet<>();

        try {
            InputStream is = getAssets().open("scrabble.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                wordSet.add(line.trim().toUpperCase());
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordSet;
    }

    private Set<String> generateAnagrams(String input) {
        Set<String> result = new HashSet<>();

        if (input.length() <= 1) {
            result.add(input);
            return result;
        }

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            String remainingChars = input.substring(0, i) + input.substring(i + 1);
            Set<String> permutations = generateAnagrams(remainingChars);

            if (currentChar == '.') {
                // if the current character is a wildcard, replace it with every letter from A to Z
                for (char c = 'A'; c <= 'Z'; c++) {
                    for (String permutation : permutations) {
                        result.add(c + permutation);
                    }
                }
            } else {
                // otherwise, add the current character to every permutation of the remaining characters
                for (String permutation : permutations) {
                    result.add(currentChar + permutation);
                }
            }
        }

        return result;
    }

    private List<String> filterValidWords(Set<String> words) {
        return words.stream()
                .filter(wordSet::contains)
                .collect(Collectors.toList());
    }
}




