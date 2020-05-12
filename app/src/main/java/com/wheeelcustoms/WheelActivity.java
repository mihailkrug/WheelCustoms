package com.wheeelcustoms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.paperdb.Paper;

public class WheelActivity extends AppCompatActivity {

    @BindView(R.id.spinBtn)
    TextView spinBtn;



    @BindView(R.id.wheel)
    ImageView wheel;




    private static final Random RANDOM = new Random();
    private int degree = 0, degreeOld = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel);




        ButterKnife.bind(this);
        Paper.init(this);



    }

    @OnClick(R.id.spinBtn)
    public void spin(View v) {
        degreeOld = degree % 360;

        degree = RANDOM.nextInt(360) + 720;

        RotateAnimation rotateAnim = new RotateAnimation(degreeOld, degree,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim.setDuration(3600);
        rotateAnim.setFillAfter(true);
        rotateAnim.setInterpolator(new DecelerateInterpolator());
        rotateAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {


                spinBtn.setBackgroundResource(R.drawable.but);

                spinBtn.setClickable(false);
                new CountDownTimer(15000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        String time = new SimpleDateFormat("mm:ss").format(new Date(millisUntilFinished));
                        spinBtn.setText(time);
                    }

                    public void onFinish() {
                        //spinBtn.setBackgroundResource(R.mipmap.btn_play);
                        spinBtn.setText("PLAY");

                        spinBtn.setClickable(true);
                    }

                }.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        wheel.startAnimation(rotateAnim);
    }



    private void shareApp() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}

