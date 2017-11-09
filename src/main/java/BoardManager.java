import java.util.List;

public class BoardManager {
    private final Board board;
    private Translator translator;
    private int matchPlayed;

    public BoardManager(Board board, Player playerA, Player playerB, Translator translator) {
        this.board = board;
        this.translator = translator;
        this.matchPlayed = 0;
    }

    public void welcome(){
        //language
        //size of board
        //player's names
        //player's signs
        //who begins
        //who's turn
    }

    public void runGame() {
        while(matchPlayed < 3){
            runMatch();
            matchPlayed++;
        }
    }

    private void runMatch() {

    }
}
