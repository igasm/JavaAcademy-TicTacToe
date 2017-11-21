package game;

import io.ConsoleReader;
import players.PlayersQueue;
import settings.Settings;

import java.util.function.Consumer;

class BoardManager {
    private final PlayersQueue playersQueue;
    private final MoveSupervisor moveSupervisor;
    private final MoveScanner boardScanner;
    private final Consumer<String> consoleWriter;
    private final Settings settings;
    private final ScoresManager scoresManager;
    private final MovesRegistry movesRegistry;
    private final ConsoleReader consoleReader;

    BoardManager(PlayersQueue playersQueue, MoveSupervisor moveSupervisor, Consumer<String> consoleWriter, Settings settings, ScoresManager scoresManager, MovesRegistry movesRegistry, ConsoleReader consoleReader) {
        this.playersQueue = playersQueue;
        this.moveSupervisor = moveSupervisor;
        this.consoleWriter = consoleWriter;
        this.settings = settings;
        this.scoresManager = scoresManager;
        this.movesRegistry = movesRegistry;
        this.consoleReader = consoleReader;
        boardScanner = new MoveScanner(movesRegistry, settings);
    }

    void runGame(){
        String newline = System.getProperty("line.separator");
        Match match = new Match(consoleWriter, playersQueue, moveSupervisor, boardScanner, settings, scoresManager, movesRegistry, consoleReader);
        for(int i=1; i<=3; i++) {
            consoleWriter.accept(newline + "==== Runda nr " + i + "====" + newline);
            match.run();
        }
    }


}
