package com.example.yumileetictactoe4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    private Button btnStartGame;
    private Button btnViewScoreboard;
    private Button btnSelectPlayer1;
    private Button btnSelectPlayer2;
    private Button btnAddPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnStartGame = (Button) findViewById(R.id.btnStartGame);
        btnViewScoreboard = (Button) findViewById(R.id.btnViewScoreboard);
        btnSelectPlayer1 = (Button) findViewById(R.id.btnSelectPlayer1);
        btnSelectPlayer2 = (Button) findViewById(R.id.btnSelectPlayer2);
        btnAddPlayer = (Button) findViewById(R.id.btnAddPlayer);

        btnStartGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),GameActivity.class);
                startActivity(intent);

            }
        });

        btnViewScoreboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ScoreboardActivity.class);
                startActivity(intent);

            }
        });

        btnAddPlayer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddPlayer.class);
                startActivity(intent);

            }
        });


        btnSelectPlayer1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SelectPlayer1Activity.class);
                startActivity(intent);

            }
        });

        btnSelectPlayer2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SelectPlayer2Activity.class);
                startActivity(intent);

            }
        });

    }

}