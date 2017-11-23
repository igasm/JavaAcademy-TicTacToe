package game;

import io.ConsoleReader;
import players.PlayersQueue;
import settings.Settings;

import java.util.function.Consumer;

class GameManager {
    private final PlayersQueue playersQueue;
    private final Consumer<String> consoleWriter;
    private final Settings settings;
    private final ScoresManager scoresManager;
    private final ConsoleReader consoleReader;
    private final GameEnd gameEnd;

    GameManager(PlayersQueue playersQueue, Consumer<String> consoleWriter, Settings settings, ScoresManager scoresManager, ConsoleReader consoleReader) {
        this.playersQueue = playersQueue;
        this.consoleWriter = consoleWriter;
        this.settings = settings;
        this.scoresManager = scoresManager;
        this.consoleReader = consoleReader;
        gameEnd = new GameEnd(scoresManager);
    }

    void runGame(){
        String newline = System.getProperty("line.separator");
        Match match = new Match(consoleWriter, playersQueue, settings, scoresManager, consoleReader);
        for(int i=1; i<=3; i++) {
            consoleWriter.accept(newline + "==== Runda nr " + i + "====" + newline);
            match.run();
        }
        consoleWriter.accept(newline + "=========================");
        consoleWriter.accept("Podsumowanie gry");
        consoleWriter.accept(gameEnd.announce());
    }


}
