package com.example.yumileetictactoe4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import android.os.Bundle;

import java.util.List;

public class CustomGameList extends ArrayAdapter{

    private String[] names;
    private String[] wins;
    private String[] losses;
    private String[] ties;
    private Activity context;

    public CustomGameList(Activity context, String[] names, String[] wins, String[] losses, String[] ties) {
        super(context, R.layout.row_item, names);
        this.context = context;
        this.names = names;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.row_item, null, true);
        TextView textViewName = (TextView) row.findViewById(R.id.textViewName);
        TextView textViewWins = (TextView) row.findViewById(R.id.textViewWins);
        TextView textViewLosses = (TextView) row.findViewById(R.id.textViewLosses);
        TextView textViewTies = (TextView) row.findViewById(R.id.textViewTies);

        textViewName.setText(names[position]);
        textViewWins.setText(wins[position]);
        textViewLosses.setText(losses[position]);
        textViewTies.setText(ties[position]);
        return  row;
    }
}