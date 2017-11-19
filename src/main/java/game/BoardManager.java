package game;

import board.Board;
import players.PlayersQueue;
import settings.Settings;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class BoardManager {
    private Board board;
    private final PlayersQueue playersQueue;
    private final MoveSupervisor moveSupervisor;
    private final MoveScanner boardScanner;
    private final Consumer<String> consoleWriter;
    private final Settings settings;
    private final ScoresManager scoresManager;
    private final MovesRegistry movesRegistry;
    private final Supplier<String> consoleReader;

    public BoardManager(PlayersQueue playersQueue, MoveSupervisor moveSupervisor, Board board, Consumer<String> consoleWriter, Settings settings, ScoresManager scoresManager, MovesRegistry movesRegistry, Supplier<String> consoleReader) {
        this.playersQueue = playersQueue;
        this.moveSupervisor = moveSupervisor;
        this.board = board;
        this.consoleWriter = consoleWriter;
        this.settings = settings;
        this.scoresManager = scoresManager;
        this.movesRegistry = movesRegistry;
        this.consoleReader = consoleReader;
        boardScanner = new MoveScanner(movesRegistry, settings);
    }

    public void runGame(){
        String newline = System.getProperty("line.separator");
        Match match = new Match(consoleWriter, playersQueue, board, moveSupervisor, boardScanner, settings, scoresManager, movesRegistry, consoleReader);
        for(int i=1; i<=3; i++) {
            consoleWriter.accept(newline + "==== Runda nr " + i + "====" + newline);
            match.run();
        }
    }


}
