import java.util.List;
import java.util.Scanner;

public class BoardManager {
    private Board board;
    private final PlayersQueue playersQueue;
    private final MoveSupervisor moveSupervisor;
    private final BoardScanner boardScanner;

    public BoardManager(PlayersQueue playersQueue, MoveSupervisor moveSupervisor, Board board) {
        this.playersQueue = playersQueue;
        this.moveSupervisor = moveSupervisor;
        this.board = board;
        boardScanner = new BoardScanner(board);
    }

    public void runGame(){
        Scanner sc = new Scanner(System.in);
        boolean gameOn = true;
        while(gameOn){
            Player currentPlayer = playersQueue.getNextPlayer();
            System.out.println("\nRuch dla " + currentPlayer.getName());
            System.out.println(board.getView());
            System.out.println("\nPodaj numer pola");
            int fieldNumber = sc.nextInt();
            moveSupervisor.move(currentPlayer.getMark(), fieldNumber);
            List<Sequence> sequences = boardScanner.scanAllDirections(fieldNumber);
            Arbiter arbiter = new Arbiter(3);
            for(Sequence sequence : sequences ){
                if (arbiter.isXWins(sequence.toString())) {
                    System.out.println("x wins");
                    gameOn=false;
                }
            }
            if(!moveSupervisor.isFreeMoveExists()){
                System.out.println("Game's over");
                gameOn=false;
            }
        }

    }


}
