package players;

import game.MarkType;

public class Player {

    private String name;
    private int score;
    private MarkType mark;

    public Player(String name, MarkType mark) throws InvalidPlayerNameException {
        if(name.matches("^\\s+$")){
            throw new InvalidPlayerNameException("Imię gracza nie może składac się wyłącznie z baiłych znaków");
        }else if(name == ""){
            throw new InvalidPlayerNameException("Imię gracza nie może byc puste");
        }else if(!name.matches("[a-zA-Z0-9]+")){
            throw new InvalidPlayerNameException("Imię gracza może się składać wyłącznie ze znaków alfanumerycznych");
        }
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
