package com.wheeelcustoms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class DiceActivity extends AppCompatActivity {
    ImageView im_cube1, im_cube2, iv_setLevel;
    Button im_dice;
    TextView im_setScoreMy, im_setScoreAI, im_scoreMy, im_scoreAI;
    private int[] mImageIds = { R.drawable.cube1, R.drawable.cube2,
            R.drawable.cube3,  R.drawable.cube4, R.drawable.cube5,
            R.drawable.cube6};
    int[] mImIdRec = new int[6];
    int[] mass = new int[6];
    int[] oldMass = new int[6];
    int myScore = 0;
    int aiScore = 0;
    Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
        Intent intent = getIntent();
        int temp = intent.getIntExtra("int_value", 1);
        im_cube1 = findViewById(R.id.im_cube1);
        im_cube2 = findViewById(R.id.im_cube2);

        im_setScoreMy = findViewById(R.id.im_setScoreMy);
        im_setScoreAI = findViewById(R.id.im_setScoreAI);
        im_scoreMy = findViewById(R.id.im_scoreMy);
        im_scoreAI = findViewById(R.id.im_scoreAI);
        im_dice = findViewById(R.id.im_dice);
        iv_setLevel = findViewById(R.id.iv_set_level);





        im_dice.setOnClickListener((View v) -> {
            dice();

                oldMass = mass;
                mass = randomArray();
                int aisc = mass[1] + mass[0] +2;
                int mysc = oldMass[1] + oldMass[0] +2;
                im_setScoreMy.setText(String.valueOf(mysc));
                im_setScoreAI.setText(String.valueOf(aisc));
                if(aisc > mysc){
                    aiScore++;
                    im_scoreAI.setText(String.valueOf(aiScore));
                }else if(aisc < mysc){
                    myScore++;
                    im_scoreMy.setText(String.valueOf(myScore));
                }


            if (myScore > 14){
               /* AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.win_dialog, null);
                dialogView.setBackgroundDrawable(null);
                builder.setView(dialogView);
                TextView restart = dialogView.findViewById(R.id.tv_btn_continue);
                final AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                restart.setOnClickListener((View view) -> {
                    finish();
                    dialog.dismiss();
                });
                dialog.show();

                */
            }
            //mass = randomArray();
        });
    }
    void dice(){
        mass = randomArray();
        for(int i = 0; i<2; i++) {
            mImIdRec[i] = mImageIds[mass[i]];
        }
        im_cube1.setImageResource(mImIdRec[0]);
        im_cube2.setImageResource(mImIdRec[1]);

    }
    public int[] randomArray() {
        int[] array = new int[2];
        //boolean numberAlreadyExists;
        for (int i = 0; i < array.length;) {
            int newRandomValue = random.nextInt(6);
            array[i] = newRandomValue;
            i++;
        }
        return array;
    }
}
