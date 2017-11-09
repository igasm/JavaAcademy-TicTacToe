public class Player {

    private String name;
    private int score;
    public boolean nextTurn;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.nextTurn = false;
    }

    public String toString(){
        return "player: " + name;
    }

}
