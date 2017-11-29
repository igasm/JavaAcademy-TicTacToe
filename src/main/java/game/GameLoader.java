package game;

import game.engine.MarkType;
import game.engine.ScoresManager;
import game.io.ConsoleReader;
import game.io.Writer;
import game.players.PlayersLoader;
import game.players.PlayersQueue;
import game.players.PlayersRegister;
import game.settings.Settings;

import java.util.HashMap;

class GameLoader {

    final private Writer consoleWriter;
    final private ConsoleReader consoleReader;
    final private Settings settings;

    GameLoader(Writer consoleWriter, ConsoleReader consoleReader, Settings settingsLoader) {
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

    private PlayersQueue playersOrderSetting(PlayersQueue playersQueue, PlayersRegister playersRegister){
        consoleWriter.addNewLine();
        consoleWriter.printlnViaTranslator("players_header");
        consoleWriter.println(playersRegister.toString());

        MarkType markType = null;
        while (markType == null){
            consoleWriter.printlnViaTranslator("ask_for_first_player");
            markType = getMarkType(consoleReader.getString());
        }

        playersQueue.changeQueueOrder(playersRegister.getPlayerByMark(markType));

        return playersQueue;
    }

    MarkType getMarkType(String mark){
        HashMap<String, MarkType> markTypeHashMap = new HashMap<>();
        markTypeHashMap.put("X", MarkType.CROSS);
        markTypeHashMap.put("O", MarkType.NAUGHT);
        return markTypeHashMap.get(mark.toUpperCase());
    }
}
