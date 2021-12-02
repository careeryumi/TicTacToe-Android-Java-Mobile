package com.example.yumileetictactoe4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPlayer extends AppCompatActivity {

    private EditText etName;
    private Button btnAddANewPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        etName = (EditText) findViewById(R.id.etName);
        btnAddANewPlayer = (Button) findViewById(R.id.btnAddANewPlayer);

        // Insert Student Records in Database
        btnAddANewPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GameModel game;
                DataBaseHelper dataBaseHelper = new DataBaseHelper(AddPlayer.this);

                int wins = 0;
                int losses =0;
                int ties = 0;

                try {
                    game = new GameModel(etName.getText().toString(), wins, losses, ties);

                    dataBaseHelper.addRecord(game);

                    Toast.makeText(getApplicationContext(), "Success "+game.toString(), Toast.LENGTH_SHORT).show();

                    etName.setText("");
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Error Adding player Record!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
                Intent intent = new Intent(AddPlayer.this,GameActivity.class);
                startActivity(intent);
                return true;
            case R.id.scoreBoard:
                Toast.makeText(this, "Score", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(AddPlayer.this,ScoreboardActivity.class);
                startActivity(intent2);
                return true;
            case R.id.selectPlayer:
                Toast.makeText(this, "Select Player", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(AddPlayer.this,SelectPlayer1Activity.class);
                startActivity(intent3);
                return true;
            case R.id.addPlayer:
                Toast.makeText(this, "Add Player", Toast.LENGTH_SHORT).show();
                Intent intent4 = new Intent(AddPlayer.this,AddPlayer.class);
                startActivity(intent4);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}