package com.wheeelcustoms;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class RandomComputerActivity extends AppCompatActivity {
    EditText edTxtGuess;
    int i;
    Button btnGuess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_computer);

        edTxtGuess = findViewById(R.id.edTxtGuess);
        btnGuess = findViewById(R.id.btnGuess);
        int min = 1;
        //int min = Integer.parseInt(edTxtGuess.getText().toString());
        int max = 10;
        int diff = max - min;


        Random random = new Random();
        i = random.nextInt(diff + 1);
        i += min;
        btnGuess.setOnClickListener((View v) -> {



            if(i == Integer.parseInt(edTxtGuess.getText().toString())){
                Toast.makeText(getApplicationContext(), "Bingo", Toast.LENGTH_SHORT).show();
                i = random.nextInt(diff + 1);
                i += min;
            }
            else
                Toast.makeText(getApplicationContext(), "No, try again!", Toast.LENGTH_SHORT).show();
        });

    }
}