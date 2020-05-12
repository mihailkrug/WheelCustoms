package com.wheeelcustoms;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ColorRandActivity extends AppCompatActivity {
    LinearLayout top, down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_rand);

        top = findViewById(R.id.top);
        down = findViewById(R.id.down);

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int min = 0;
                        int max = 1;
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
                        if(i==0){
                            top.setBackgroundColor(getResources().getColor(R.color.colorTop));
                            down.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        }else{
                            down.setBackgroundColor(getResources().getColor(R.color.colorDown));
                            top.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        }

                    }
                });
            }
        }, 800, 1000);


    }
}