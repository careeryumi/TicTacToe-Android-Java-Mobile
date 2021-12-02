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

public class EditDeletePlayerActivity extends AppCompatActivity {

    private EditText etName;
    private Button btnDelete,btnUpdate;
    int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete_player);

        etName = (EditText) findViewById(R.id.etName);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        Intent intent = getIntent();
        ID = intent.getExtras().getInt("ID");
        etName.setText(intent.getExtras().getString("etName"));

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataBaseHelper dataBaseHelper = new DataBaseHelper(EditDeletePlayerActivity.this);
                dataBaseHelper.deleteRecord(ID);
                Intent i = new Intent(EditDeletePlayerActivity.this, ScoreboardActivity.class);
                startActivity(i);
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataBaseHelper dataBaseHelper = new DataBaseHelper(EditDeletePlayerActivity.this);
                dataBaseHelper.updateRecord(ID, etName.getText().toString());

                Toast.makeText(EditDeletePlayerActivity.this, "Game Record Updated..", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(EditDeletePlayerActivity.this, ScoreboardActivity.class);
                startActivity(i);
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
                Toast.makeText(this, "New Game", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(EditDeletePlayerActivity.this,GameActivity.class);
                startActivity(intent1);
                return true;
            case R.id.scoreBoard:
                Toast.makeText(this, "Score", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(EditDeletePlayerActivity.this,ScoreboardActivity.class);
                startActivity(intent2);
                return true;
            case R.id.selectPlayer:
                Toast.makeText(this, "Select Player", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(EditDeletePlayerActivity.this,SelectPlayer1Activity.class);
                startActivity(intent3);
                return true;
            case R.id.addPlayer:
                Toast.makeText(this, "Add Player", Toast.LENGTH_SHORT).show();
                Intent intent4 = new Intent(EditDeletePlayerActivity.this,AddPlayer.class);
                startActivity(intent4);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}