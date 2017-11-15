import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class Match {

    private final Consumer<String> consoleWriter;
    private final PlayersQueue playersQueue;
    private final Board board;
    private final MoveSupervisor moveSupervisor;
    private final BoardScanner boardScanner;
    private final Settings settings;
    private final ScoresManager scoresManager;
    private boolean matchOn;

    public Match(Consumer<String> consoleWriter, PlayersQueue playersQueue, Board board, MoveSupervisor moveSupervisor, BoardScanner boardScanner, Settings settings, ScoresManager scoresManager) {
        this.consoleWriter = consoleWriter;
        this.playersQueue = playersQueue;
        this.board = board;
        this.moveSupervisor = moveSupervisor;
        this.boardScanner = boardScanner;
        this.settings = settings;
        this.scoresManager = scoresManager;
        this.matchOn = true;
    }


    public void run(){
        Scanner sc = new Scanner(System.in);
        String newline = System.getProperty("line.separator");
        board.clearMarks();
        matchOn = true;
        consoleWriter.accept(board.toString());
        while(matchOn){
            Player currentPlayer = playersQueue.getNextPlayer();
            consoleWriter.accept(newline + "Ruch dla " + currentPlayer.getName() + " (" + currentPlayer.getMark() +"), podaj numer pola" );
            int fieldNumber = sc.nextInt();
            moveSupervisor.move(currentPlayer.getMark(), fieldNumber);
            List<Sequence> sequences = boardScanner.scanAllDirections(fieldNumber);
            Arbiter arbiter = new Arbiter(settings.getWinningCondition());
            for(Sequence sequence : sequences ){
                if (arbiter.isWin(currentPlayer.getMark(), sequence.toString())) {
                    consoleWriter.accept(currentPlayer.getName() + " wygrywa mecz");
                    scoresManager.addWin(currentPlayer);
                    matchOn=false;
                }
            }
            if(!moveSupervisor.isFreeMoveExists()){
                consoleWriter.accept("Koniec meczu - remis");
                scoresManager.addDraw();
                matchOn=false;
            }
        }
        consoleWriter.accept("Wyniki meczu");
        consoleWriter.accept(scoresManager.getSubmit());
    }

}
