package com.example.yumileetictactoe4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SelectPlayer2Activity extends AppCompatActivity {

    private ListView listView;
    private TextView textView;
    String[] listItem;
    private int idPlayer1 = -1;
    private String namePlayer1 = "";
    private String namePlayer2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_player2);

        listView = (ListView) findViewById(R.id.list_item);
        textView = (TextView) findViewById(R.id.text);


        DataBaseHelper dataBaseHelper = new DataBaseHelper(SelectPlayer2Activity.this);

        List<GameModel> names = dataBaseHelper.viewNames();
        ArrayAdapter adapter = new ArrayAdapter<GameModel>(SelectPlayer2Activity.this, android.R.layout.simple_list_item_1, android.R.id.text1, names);

        listView.setAdapter(adapter);


        Intent intent = getIntent();

        if (!intent.hasExtra("player1Name")){
            Intent intentPlayer2 = new Intent(SelectPlayer2Activity.this,SelectPlayer1Activity.class);
            startActivity(intentPlayer2);

            Toast.makeText(getApplicationContext(), "Select player1 first", Toast.LENGTH_SHORT).show();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                GameModel gameModel = (GameModel) adapterView.getItemAtPosition(i);

                Toast.makeText(getApplicationContext(), "Player 2 "+gameModel.getName().toString() + " added! ", Toast.LENGTH_SHORT).show();

                Intent intent = getIntent();


                    idPlayer1 = intent.getExtras().getInt("player1ID");
                    namePlayer1 = intent.getExtras().getString("player1Name");

                    Intent intentPlayer2 = new Intent(SelectPlayer2Activity.this,GameActivity.class);
                    intentPlayer2.putExtra("player1ID",idPlayer1);
                    intentPlayer2.putExtra("player1Name",namePlayer1);
                    intentPlayer2.putExtra("player2ID",gameModel.getId());
                    intentPlayer2.putExtra("player2Name",gameModel.getName());
                    startActivity(intentPlayer2);


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
                Intent intent1 = new Intent(SelectPlayer2Activity.this,GameActivity.class);
                startActivity(intent1);
                return true;
            case R.id.scoreBoard:
                Toast.makeText(this, "Score", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(SelectPlayer2Activity.this,ScoreboardActivity.class);
                startActivity(intent2);
                return true;
            case R.id.selectPlayer:
                Toast.makeText(this, "Select Player", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(SelectPlayer2Activity.this,SelectPlayer1Activity.class);
                startActivity(intent3);
                return true;
            case R.id.addPlayer:
                Toast.makeText(this, "Add Player", Toast.LENGTH_SHORT).show();
                Intent intent4 = new Intent(SelectPlayer2Activity.this,AddPlayer.class);
                startActivity(intent4);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}