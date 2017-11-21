package game;

import io.ConsoleReader;
import players.PlayersLoader;
import players.PlayersQueue;
import players.PlayersRegister;
import settings.Settings;

import java.util.function.Consumer;

class GameLoader {

    final private Consumer<String> consoleWriter;
    final private ConsoleReader consoleReader;
    final private Settings settings;

    GameLoader(Consumer<String> consoleWriter, ConsoleReader consoleReader, Settings settingsLoader) {
        this.consoleWriter = consoleWriter;
        this.consoleReader = consoleReader;
        this.settings = settingsLoader;
    }

    BoardManager load(){
        MovesRegistry movesRegistry = new MovesRegistry();
        MoveSupervisor moveSupervisor = new MoveSupervisor(movesRegistry, settings);
        PlayersLoader playersLoader = new PlayersLoader(consoleWriter, consoleReader);
        PlayersRegister playersRegister = playersLoader.load();
        PlayersQueue playersQueue = new PlayersQueue(playersRegister);
        ScoresManager scoresManager = new ScoresManager(playersRegister);
        return new BoardManager(playersQueue, moveSupervisor, consoleWriter, settings, scoresManager, movesRegistry, consoleReader);
    }
}
