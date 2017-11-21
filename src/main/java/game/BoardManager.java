package game;

import io.ConsoleReader;
import players.PlayersQueue;
import settings.Settings;

import java.util.function.Consumer;

class BoardManager {
    private final PlayersQueue playersQueue;
    private final Consumer<String> consoleWriter;
    private final Settings settings;
    private final ScoresManager scoresManager;
    private final ConsoleReader consoleReader;

    BoardManager(PlayersQueue playersQueue, Consumer<String> consoleWriter, Settings settings, ScoresManager scoresManager, ConsoleReader consoleReader) {
        this.playersQueue = playersQueue;
        this.consoleWriter = consoleWriter;
        this.settings = settings;
        this.scoresManager = scoresManager;
        this.consoleReader = consoleReader;
    }

    void runGame(){
        String newline = System.getProperty("line.separator");
        Match match = new Match(consoleWriter, playersQueue, settings, scoresManager, consoleReader);
        for(int i=1; i<=3; i++) {
            consoleWriter.accept(newline + "==== Runda nr " + i + "====" + newline);
            match.run();
        }
    }


}
