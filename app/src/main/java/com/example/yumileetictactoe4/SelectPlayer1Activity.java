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

public class SelectPlayer1Activity extends AppCompatActivity {

    private ListView listView;
    private TextView textView;
    String[] listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_player1);

        listView = (ListView) findViewById(R.id.list_item);
        textView = (TextView) findViewById(R.id.text);


        DataBaseHelper dataBaseHelper = new DataBaseHelper(SelectPlayer1Activity.this);

        List<GameModel> names = dataBaseHelper.viewNames();
        ArrayAdapter adapter = new ArrayAdapter<GameModel>(SelectPlayer1Activity.this, android.R.layout.simple_list_item_1, android.R.id.text1, names);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                GameModel gameModel = (GameModel) adapterView.getItemAtPosition(i);

                Toast.makeText(getApplicationContext(), "Player 1 "+gameModel.getName().toString() + " added! ", Toast.LENGTH_SHORT).show();

                Intent intentPlayer1 = new Intent(SelectPlayer1Activity.this,SelectPlayer2Activity.class);
                intentPlayer1.putExtra("player1ID",gameModel.getId());
                intentPlayer1.putExtra("player1Name",gameModel.getName());
                startActivity(intentPlayer1);
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
                Intent intent = new Intent(SelectPlayer1Activity.this,GameActivity.class);
                startActivity(intent);

                return true;
            case R.id.scoreBoard:
                Toast.makeText(this, "Score", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(SelectPlayer1Activity.this,ScoreboardActivity.class);
                startActivity(intent2);
                return true;
            case R.id.selectPlayer:
                Toast.makeText(this, "Select Player", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(SelectPlayer1Activity.this,SelectPlayer1Activity.class);
                startActivity(intent3);
                return true;
            case R.id.addPlayer:
                Toast.makeText(this, "Add Player", Toast.LENGTH_SHORT).show();
                Intent intent4 = new Intent(SelectPlayer1Activity.this,AddPlayer.class);
                startActivity(intent4);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}