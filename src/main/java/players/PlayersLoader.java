package players;

import io.ConsoleReader;
import players.Player;
import players.PlayersRegister;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class PlayersLoader {

    private final Consumer<String> consoleWriter;
    private final ConsoleReader consoleReader;

    public PlayersLoader(Consumer<String> consoleWriter, ConsoleReader consoleReader) {
        this.consoleWriter = consoleWriter;
        this.consoleReader = consoleReader;
    }

    public PlayersRegister load(){
        PlayersRegister playersRegister = new PlayersRegister(2);
        consoleWriter.accept("Podaj imię pierwszego gracza");
        playersRegister.registerPlayer(new Player(consoleReader.getString(), "x"));
        consoleWriter.accept("Podaj imię drugiego gracza");
        playersRegister.registerPlayer(new Player(consoleReader.getString(), "o"));
        return playersRegister;
    }

}
