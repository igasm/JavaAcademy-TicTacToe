package game.players;

import game.engine.MarkType;
import game.io.ConsoleReader;
import game.io.Writer;

public class PlayersLoader {

    private final Writer consoleWriter;
    private final ConsoleReader consoleReader;
    private PlayersRegister playersRegister;

    public PlayersLoader(Writer consoleWriter, ConsoleReader consoleReader) {
        this.consoleWriter = consoleWriter;
        this.consoleReader = consoleReader;
        playersRegister = new PlayersRegister(2);
    }

    public PlayersRegister load(){
        PlayerNameValidator validator = new PlayerNameValidator();
        registerPlayer(MarkType.CROSS, validator);
        registerPlayer(MarkType.NAUGHT, validator);
        return playersRegister;
    }

    private void registerPlayer(MarkType markType, PlayerNameValidator playerNameValidator){
        boolean valid = false;
        String playerName = "";
        while (!valid){
            try {
                consoleWriter.printViaTranslator("player_name_for_mark");
                consoleWriter.println(" " + markType.toString());
                playerName = consoleReader.getString();
                valid = playerNameValidator.validate(playerName);
            } catch (InvalidPlayerNameException e) {
                consoleWriter.printlnViaTranslator("bad_characters_in_player_name");
            }
        }
        playersRegister.registerPlayer(new Player(playerName, markType));
    }

}
