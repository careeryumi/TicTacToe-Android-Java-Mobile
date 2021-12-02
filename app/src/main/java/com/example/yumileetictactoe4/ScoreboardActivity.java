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

public class ScoreboardActivity extends AppCompatActivity {

    private ListView listView;
    private TextView textView;
    String[] listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        listView = (ListView) findViewById(R.id.list_item);
        textView = (TextView) findViewById(R.id.text);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(ScoreboardActivity.this);

        List<GameModel> records = dataBaseHelper.viewRecords();
        int recordsSize = records.size();

        int[] ids = new int[recordsSize];
        String[] names = new String[recordsSize];
        String[] wins = new String[recordsSize];
        String[] losses = new String[recordsSize];
        String[] ties = new String[recordsSize];

        for(int i=0; i<recordsSize; i++){
            ids[i] = records.get(i).getId();
            names[i] = records.get(i).getName();
            wins[i] = String.valueOf(records.get(i).getWins());
            losses[i] = String.valueOf(records.get(i).getLosses());
            ties[i] = String.valueOf(records.get(i).getTies());
        }

        CustomGameList customCountryList = new CustomGameList(this, names, wins, losses, ties);
        listView.setAdapter(customCountryList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int clicked = ids[i];
                String nameClicked = names[i];

                Intent intent = new Intent(ScoreboardActivity.this,EditDeletePlayerActivity.class);
                intent.putExtra("ID",clicked);
                intent.putExtra("etName",nameClicked);
                startActivity(intent);

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

                Intent intent = new Intent(ScoreboardActivity.this,GameActivity.class);
                startActivity(intent);

                return true;
            case R.id.scoreBoard:
                Toast.makeText(this, "Score", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(ScoreboardActivity.this,ScoreboardActivity.class);
                startActivity(intent2);
                return true;
            case R.id.selectPlayer:
                Toast.makeText(this, "Select Player", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(ScoreboardActivity.this,SelectPlayer1Activity.class);
                startActivity(intent3);
                return true;
            case R.id.addPlayer:
                Toast.makeText(this, "Add Player", Toast.LENGTH_SHORT).show();
                Intent intent4 = new Intent(ScoreboardActivity.this,AddPlayer.class);
                startActivity(intent4);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}