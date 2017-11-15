import java.util.function.Consumer;

public class BoardManager {
    private Board board;
    private final PlayersQueue playersQueue;
    private final MoveSupervisor moveSupervisor;
    private final BoardScanner boardScanner;
    private final Consumer<String> consoleWriter;
    private final Settings settings;
    private final ScoresManager scoresManager;

    public BoardManager(PlayersQueue playersQueue, MoveSupervisor moveSupervisor, Board board, Consumer<String> consoleWriter, Settings settings, ScoresManager scoresManager) {
        this.playersQueue = playersQueue;
        this.moveSupervisor = moveSupervisor;
        this.board = board;
        boardScanner = new BoardScanner(board);
        this.consoleWriter = consoleWriter;
        this.settings = settings;
        this.scoresManager = scoresManager;
    }

    public void runGame(){
        Match match = new Match(consoleWriter, playersQueue, board, moveSupervisor, boardScanner, settings, scoresManager);
        for(int i=0; i<3; i++) {
            consoleWriter.accept("Mecz nr " + i);
            match.run();
        }
    }


}
