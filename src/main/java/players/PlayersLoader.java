package players;

import game.MarkType;
import io.ConsoleReader;

import java.util.function.Consumer;

public class PlayersLoader {

    private final Consumer<String> consoleWriter;
    private final ConsoleReader consoleReader;
    PlayersRegister playersRegister;

    public PlayersLoader(Consumer<String> consoleWriter, ConsoleReader consoleReader) {
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
                consoleWriter.accept("Podaj imię gracza grającego " + markType.toString());
                playerName = consoleReader.getString();
                valid = playerNameValidator.validate(playerName);
            } catch (InvalidPlayerNameException e) {
                consoleWriter.accept(e.getMessage());
            }
        }
        playersRegister.registerPlayer(new Player(playerName, markType));
    }

}
