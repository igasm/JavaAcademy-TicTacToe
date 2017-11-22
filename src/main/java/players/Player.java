package players;

public class Player {

    private String name;
    private int score;
    private String mark;

    public Player(String name, String mark) {
        if(name.matches("^\\s+$")){
            throw new IllegalArgumentException("players name consist only from whitespaces");
        }else if(name == ""){
            throw new IllegalArgumentException("players name is empty");
        }else if(!name.matches("[a-zA-Z0-9]+")){
            throw new IllegalArgumentException("only alphanumeric characters for player name");
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

    public String getMark() {
        return mark;
    }

    public String toString(){
        return "player: " + name;
    }

}
