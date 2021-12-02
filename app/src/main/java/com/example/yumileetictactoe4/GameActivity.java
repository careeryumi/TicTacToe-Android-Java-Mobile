package com.example.yumileetictactoe4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;


public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button aButtons[][] = new Button[3][3];
    private boolean firstTurn = true;
    private int roundCount = 0;
    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    private int idPlayer1 = -1;
    private String namePlayer1 = "";
    private int idPlayer2 = -1;
    private String namePlayer2 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textViewPlayer1 = findViewById(R.id.textViewPlayer1);
        textViewPlayer2 = findViewById(R.id.textViewPlayer2);

        aButtons[0][0] = findViewById(R.id.button00);
        aButtons[0][1] = findViewById(R.id.button01);
        aButtons[0][2] = findViewById(R.id.button02);
        aButtons[1][0] = findViewById(R.id.button10);
        aButtons[1][1] = findViewById(R.id.button11);
        aButtons[1][2] = findViewById(R.id.button12);
        aButtons[2][0] = findViewById(R.id.button20);
        aButtons[2][1] = findViewById(R.id.button21);
        aButtons[2][2] = findViewById(R.id.button22);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                aButtons[i][j].setOnClickListener(this);
            }
        }

        Button buttonNewGame = findViewById(R.id.buttonNewGame);
        buttonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame();
            }
        });

        Button buttonScoreBoard = findViewById(R.id.btnScoreBoard);
        buttonScoreBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(GameActivity.this,ScoreboardActivity.class);
                startActivity(intent1);
            }
        });


        Intent intent = getIntent();

        if (intent.hasExtra("player1Name") && intent.hasExtra("player2Name")){

            idPlayer1 = intent.getExtras().getInt("player1ID");
            namePlayer1 = intent.getExtras().getString("player1Name");

            idPlayer2 = intent.getExtras().getInt("player2ID");
            namePlayer2 = intent.getExtras().getString("player2Name");

            textViewPlayer1.setText("Player1:" + namePlayer1);
            textViewPlayer2.setText("Player2:" + namePlayer2);
        }
        else{
            Intent intent1 = new Intent(GameActivity.this,SelectPlayer1Activity.class);
            startActivity(intent1);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.gamemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.newGameMenu:
                Intent intent0 = getIntent();
                if (!intent0.hasExtra("player1ID") || !intent0.hasExtra("player2ID")){
                    Toast.makeText(this, "Please select players first", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "NewGame", Toast.LENGTH_SHORT).show();
                }
                restartGame();
                return true;
            case R.id.scoreBoard:
                Toast.makeText(this, "Score", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(GameActivity.this,ScoreboardActivity.class);
                startActivity(intent);
                return true;
            case R.id.selectPlayer:
                Toast.makeText(this, "Select Player", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(GameActivity.this,SelectPlayer1Activity.class);
                startActivity(intent1);
                return true;
            case R.id.addPlayer:
                Toast.makeText(this, "Add Player", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(GameActivity.this,AddPlayer.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals("")){
            return;
        }

        if(firstTurn){
            ((Button) v).setText("X");
        }else{
            ((Button) v).setText("O");
        }
        roundCount++;

        if (checkWin()){
            if (firstTurn){
                WinPlayer1();
            }else{
                WinPlayer2();
            }
        } else if (roundCount == 9){
            draw();
        }else{
            firstTurn = !firstTurn; //switch turns
        }
    }

    private boolean checkWin(){
        String[][] checkArray = new String[3][3];

        for (int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                checkArray[i][j] = aButtons[i][j].getText().toString();
            }
        }

        //from leftTop to right bottom
        if(!(checkArray[0][0].equals("")) && (checkArray[0][0] == checkArray[1][1])
                && (checkArray[0][0] == checkArray[2][2])
        ){
            return true;
        }

        //from rightTop to left bottom
        if(!(checkArray[0][2].equals("")) && (checkArray[0][2] == checkArray[1][1])
                && (checkArray[0][2] == checkArray[2][0])
        ){
            return true;
        }

        for(int i= 0; i<3; i++){
            if(!(checkArray[i][0].equals("")) && (checkArray[i][0] == checkArray[i][1])
                    && (checkArray[i][0] == checkArray[i][2])
            ){
                return true;
            }
        }

        for(int i= 0; i<3; i++){
            if(!(checkArray[0][i].equals("")) && (checkArray[0][i] == checkArray[1][i])
                    && (checkArray[0][i] == checkArray[2][i])
            ){
                return true;
            }
        }

        return false;
    }

    private void draw(){

        DataBaseHelper dataBaseHelper = new DataBaseHelper(GameActivity.this);
        dataBaseHelper.updateRecordTies(idPlayer1);
        dataBaseHelper.updateRecordTies(idPlayer2);

        Toast.makeText(this, "Game draw!", Toast.LENGTH_SHORT).show();
        clearBoard();
    }

    private void WinPlayer1(){

        DataBaseHelper dataBaseHelper = new DataBaseHelper(GameActivity.this);
        dataBaseHelper.updateRecordWins(idPlayer1);
        dataBaseHelper.updateRecordLosses(idPlayer2);

        scorePlayer1 ++;
        Toast.makeText(this, namePlayer1 + " Wins!!!", Toast.LENGTH_SHORT).show();
        updateScore();
        clearBoard();
    }

    private void WinPlayer2(){

        DataBaseHelper dataBaseHelper = new DataBaseHelper(GameActivity.this);
        dataBaseHelper.updateRecordWins(idPlayer2);
        dataBaseHelper.updateRecordLosses(idPlayer1);

        scorePlayer2 ++;
        Toast.makeText(this, namePlayer2 + " Wins!!!", Toast.LENGTH_SHORT).show();
        updateScore();
        clearBoard();
    }


    private void restartGame(){
        scorePlayer1 = 0;
        scorePlayer2 = 0;
        updateScore();
        clearBoard();
    }

    private void updateScore(){

        Intent intent = getIntent();
        namePlayer1 = intent.getExtras().getString("player1Name");

        textViewPlayer1.setText("Player1: " + namePlayer1 + " " +scorePlayer1);
        textViewPlayer2.setText("Player2:" + namePlayer2 + " " +scorePlayer2);
    }

    private  void clearBoard(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                aButtons[i][j].setText("");
            }
        }
        roundCount = 0;
        firstTurn = true;
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundCount", roundCount); // save round count
        outState.putInt("scorePlayer1", scorePlayer1);
        outState.putInt("scorePlayer2", scorePlayer2);
        outState.putBoolean("firstTurn", firstTurn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        scorePlayer1 = savedInstanceState.getInt("scorePlayer1");
        scorePlayer2 = savedInstanceState.getInt("scorePlayer2");
        firstTurn = savedInstanceState.getBoolean("firstTurn");
    }

}