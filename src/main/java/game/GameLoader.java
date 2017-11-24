package game;

import io.ConsoleReader;
import players.PlayersLoader;
import players.PlayersQueue;
import players.PlayersRegister;
import settings.Settings;

import java.util.HashMap;
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

    GameManager load(){
        PlayersLoader playersLoader = new PlayersLoader(consoleWriter, consoleReader);
        PlayersRegister playersRegister = playersLoader.load();
        PlayersQueue playersQueue = new PlayersQueue(playersRegister);
        playersQueue = playersOrderSetting(playersQueue, playersRegister);
        ScoresManager scoresManager = new ScoresManager(playersRegister);
        return new GameManager(playersQueue, consoleWriter, settings, scoresManager, consoleReader);
    }

    PlayersQueue playersOrderSetting(PlayersQueue playersQueue, PlayersRegister playersRegister){
        String newline = System.getProperty("line.separator");

        consoleWriter.accept(newline);
        consoleWriter.accept("Gracze:");
        consoleWriter.accept(playersRegister.toString());

        MarkType markType = null;
        while (markType == null){
            consoleWriter.accept("Wybierz gracza, który zaczyna grę (x/o)");
            markType = getMarkType(consoleReader.getString());
        }

        playersQueue.changeQueueOrder(playersRegister.getPlayerByMark(markType));

        return playersQueue;
    }

    MarkType getMarkType(String mark){
        HashMap<String, MarkType> markTypeHashMap = new HashMap<>();
        markTypeHashMap.put("X", MarkType.CROSS);
        markTypeHashMap.put("O", MarkType.NAUGHT);
        MarkType markType = markTypeHashMap.get(mark.toUpperCase());
        return markType;
    }
}
