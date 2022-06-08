package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9;
    TextView header;
    int PLAYER_O = 0;
    int PLAYER_X = 1;

    int activePlayer = PLAYER_O;

    int[] filledPos = {-1,-1,-1,-1,-1,-1,-1,-1,-1};

    boolean isGameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)  findViewById(R.id.b1);
        b2 =(Button)  findViewById(R.id.b2);
        b3=(Button)  findViewById(R.id.b3);
        b4=(Button)  findViewById(R.id.b4);
        b5=(Button)  findViewById(R.id.b5);
        b6=(Button)  findViewById(R.id.b6);
        b7=(Button)  findViewById(R.id.b7);
        b8=(Button)  findViewById(R.id.b8);
        b9=(Button)  findViewById(R.id.b9);

        header=(TextView) findViewById(R.id.textView);
        header.setText("O turn");

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(!isGameActive)
            return;

        Button clickedBtn = findViewById(view.getId());
        int clickedTag = Integer.parseInt(view.getTag().toString());

        if(filledPos[clickedTag] != -1){
            return;
        }

        filledPos[clickedTag] = activePlayer;

        if(activePlayer == PLAYER_O){
            clickedBtn.setText("O");
            clickedBtn.setBackground(getDrawable(android.R.color.holo_blue_light));
            activePlayer = PLAYER_X;
            header.setText("X turn");
        }else{
            clickedBtn.setText("X");
            clickedBtn.setBackground(getDrawable(android.R.color.holo_orange_light));
            activePlayer = PLAYER_O;
            header.setText("O turn");
        }

        checkForWin();
    }



    private void checkForWin(){
        //we will check who is winner and show
        int[][] winningPos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        int flag1=1;

        for(int i =0 ;i<8;i++){
            int val0  = winningPos[i][0];
            int val1  = winningPos[i][1];
            int val2  = winningPos[i][2];

            if(filledPos[val0] == filledPos[val1] && filledPos[val1] == filledPos[val2]){
                if(filledPos[val0] != -1){
                    //winner declare

                    isGameActive = false;

                    if(filledPos[val0] == PLAYER_O)
                        showDialog("O is winner");
                    else
                        showDialog("X is winner");

                }

            }
            if(filledPos[val0] != -1 && filledPos[val1] != -1 && filledPos[val2] != -1 && flag1!=0)
            {
                flag1=1;
            }
            else {
                flag1=0;
            }
        }
        if(flag1!=0){
            showDialog("Game draw");
        }


    }
    private void showDialog(String winnerText){
        new AlertDialog.Builder(this)
                .setTitle(winnerText)
                .setPositiveButton("Restart game", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        restartGame();
                    }
                })
                .show();
    }
    private void restartGame(){
        activePlayer = PLAYER_O;
        header.setText("O turn");
        filledPos = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1};
        b1.setText("");
        b2.setText("");
        b3.setText("");
        b4.setText("");
        b5.setText("");
        b6.setText("");
        b7.setText("");
        b8.setText("");
        b9.setText("");

        b1.setBackground(getDrawable(android.R.color.darker_gray));
        b2.setBackground(getDrawable(android.R.color.darker_gray));
        b3.setBackground(getDrawable(android.R.color.darker_gray));
        b4.setBackground(getDrawable(android.R.color.darker_gray));
        b5.setBackground(getDrawable(android.R.color.darker_gray));
        b6.setBackground(getDrawable(android.R.color.darker_gray));
        b7.setBackground(getDrawable(android.R.color.darker_gray));
        b8.setBackground(getDrawable(android.R.color.darker_gray));
        b9.setBackground(getDrawable(android.R.color.darker_gray));
        isGameActive = true;
    }


}