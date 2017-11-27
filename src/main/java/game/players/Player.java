package game.players;

import game.engine.MarkType;

public class Player {

    private String name;
    private int score;
    private MarkType mark;

    public Player(String name, MarkType mark){
        this.name = name;
        this.score = 0;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void addPoints(int value){
        this.score += value;
    }

    public MarkType getMark() {
        return mark;
    }

    public String toString(){
        return name + " (" + mark + ")";
    }

}
