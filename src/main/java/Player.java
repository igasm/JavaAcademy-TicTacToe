public class Player implements Comparable<Player> {

    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String toString(){
        return "player: " + name;
    }

    public int compareTo(Player o) {
       return this.name.compareTo(o.name);
    }

    //TODO implementacja klasy equals, taka że dwaj gracze o tym samym imieniu są equals??

}
