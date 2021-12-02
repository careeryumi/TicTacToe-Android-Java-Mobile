package com.example.yumileetictactoe4;

public class GameModel {

    private int _id;
    private String name;
    private int wins = -1;
    private int losses;
    private int ties;

    public GameModel(int _id, String name, int wins, int losses, int ties){
        this._id = _id;
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
    }

    public GameModel(){

    }

    public GameModel(String name, int wins, int losses, int ties){
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
    }


    public GameModel(int _id, String name){
        this._id = _id;
        this.name = name;
    }


    // Getters and Setters

    public int getId() {
        return _id;
    }
    public void setId(int _id) { this._id = _id; }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }
    public void setwins(int wins) { this.wins = wins; }

    public int getLosses() {
        return losses;
    }
    public void setLosses(int losses) { this.losses = losses; }

    public int getTies() {
        return ties;
    }
    public void setTies(int ties) { this.ties = ties; }

    // Prints the Contents of a class
    @Override
    public String toString() {

        if (wins == -1){
            return name;
        }
        return name;
    }

}
