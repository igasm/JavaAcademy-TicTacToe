public class Player implements Comparable<Player> {

    private String name;
    private int score;
    private String mark;

    public Player(String name, String mark) {
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

    public String getMark() {
        return mark;
    }

    public String toString(){
        return "player: " + name;
    }

    public int compareTo(Player o) {
       return this.name.compareTo(o.name);
    }

    //TODO implementacja klasy equals, taka że dwaj gracze o tym samym imieniu są equals??

}
