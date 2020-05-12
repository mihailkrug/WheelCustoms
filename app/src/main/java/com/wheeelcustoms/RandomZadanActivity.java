package com.wheeelcustoms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class RandomZadanActivity extends AppCompatActivity {
EditText edTxtLeft, edTxtRight;
TextView txtRandom;
Button btnRandomize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_zadan);

        edTxtLeft = findViewById(R.id.edTxtLeft);
        edTxtRight = findViewById(R.id.edTxtRight);
        txtRandom = findViewById(R.id.txtRnd);
        btnRandomize = findViewById(R.id.btnRandomize);

        btnRandomize.setOnClickListener((View v) -> {

            int min = Integer.parseInt(edTxtLeft.getText().toString());
            int max = Integer.parseInt(edTxtRight.getText().toString());
            int diff = max - min;
            int i;
            if(diff<0) {
                diff = min - max;
                Random random = new Random();
                i = random.nextInt(diff + 1);
                i += max;
            }else{
                Random random = new Random();
                i = random.nextInt(diff + 1);
                i += min;
            }

            txtRandom.setText(String.valueOf(i));
        });

    }
}