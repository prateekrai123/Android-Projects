package com.example.dice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView one1,two2,three3,four4,five5,six6;
    Button roll;
    int min= 0;
    int max = 6;
    int range = max-min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        one1 = findViewById(R.id.one);
        two2 = findViewById(R.id.two);
        three3 = findViewById(R.id.three);
        four4 = findViewById(R.id.four);
        five5 = findViewById(R.id.five);
        six6 = findViewById(R.id.six);
        roll = findViewById(R.id.rolling);


        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int random = (int)(Math.random()*range)-min+1;
                if(random == 1){
                    one1.setVisibility(View.INVISIBLE);
                    two2.setVisibility(View.INVISIBLE);
                    three3.setVisibility(View.INVISIBLE);
                    four4.setVisibility(View.INVISIBLE);
                    five5.setVisibility(View.INVISIBLE);
                    six6.setVisibility(View.INVISIBLE);
                    one1.setVisibility(View.VISIBLE);
                }
                else if(random == 2){
                    one1.setVisibility(View.INVISIBLE);
                    two2.setVisibility(View.INVISIBLE);
                    three3.setVisibility(View.INVISIBLE);
                    four4.setVisibility(View.INVISIBLE);
                    five5.setVisibility(View.INVISIBLE);
                    six6.setVisibility(View.INVISIBLE);
                    two2.setVisibility(View.VISIBLE);
                }
                else if(random==3){
                    one1.setVisibility(View.INVISIBLE);
                    two2.setVisibility(View.INVISIBLE);
                    three3.setVisibility(View.INVISIBLE);
                    four4.setVisibility(View.INVISIBLE);
                    five5.setVisibility(View.INVISIBLE);
                    six6.setVisibility(View.INVISIBLE);
                    three3.setVisibility(View.VISIBLE);
                }
                else if(random==4){
                    one1.setVisibility(View.INVISIBLE);
                    two2.setVisibility(View.INVISIBLE);
                    three3.setVisibility(View.INVISIBLE);
                    four4.setVisibility(View.INVISIBLE);
                    five5.setVisibility(View.INVISIBLE);
                    six6.setVisibility(View.INVISIBLE);
                    four4.setVisibility(View.VISIBLE);
                }
                else if(random==5){
                    one1.setVisibility(View.INVISIBLE);
                    two2.setVisibility(View.INVISIBLE);
                    three3.setVisibility(View.INVISIBLE);
                    four4.setVisibility(View.INVISIBLE);
                    five5.setVisibility(View.INVISIBLE);
                    six6.setVisibility(View.INVISIBLE);
                    five5.setVisibility(View.VISIBLE);
                }
                else if(random==6){
                    one1.setVisibility(View.INVISIBLE);
                    two2.setVisibility(View.INVISIBLE);
                    three3.setVisibility(View.INVISIBLE);
                    four4.setVisibility(View.INVISIBLE);
                    five5.setVisibility(View.INVISIBLE);
                    six6.setVisibility(View.INVISIBLE);
                    six6.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
