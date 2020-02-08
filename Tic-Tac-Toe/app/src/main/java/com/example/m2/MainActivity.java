package com.example.m2;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.os.Bundle;

import android.widget.*;

public class MainActivity extends AppCompatActivity {

    /*
    0 - X;
    1 - O;
     */

    int activePlayer = 0;
    int gameState[] = {2,2,2,2,2,2,2,2,2};
    boolean isGameOn = true;
    /*
    State meanings :

    0 - X
    1 - O
    2 - NULL

     */

    int[][] winPosition= {{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};

    public void playerTap(View view){

        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!isGameOn)
        {
            reset(view);
        }
        if(gameState[tappedImage] == 2)
        {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if(gameState[tappedImage] == 0)
            {
                activePlayer = 1;
                img.setImageResource(R.drawable.x);
                TextView status = findViewById(R.id.status);
                status.setText("O's turn. Tap to play");
            }
            else
            {
                activePlayer = 0;
                img.setImageResource(R.drawable.o);
                TextView status = findViewById(R.id.status);
                status.setText("X's turn. Tap to play");
            }
        }
        img.animate().translationYBy(1000f).setDuration(300);

        for(int[] winPosition : winPosition)
        {
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[2]] != 2)
            {
                String winnerStr;
                isGameOn = false;
                if(gameState[winPosition[0]]==0)
                {
                    winnerStr = "Player X won";
                }
                else {
                    winnerStr = "Player O won";
                }
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }

    }
    public void reset(View view)
    {
        isGameOn = true;
        activePlayer = 0;
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's turn. Tap to play.");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
