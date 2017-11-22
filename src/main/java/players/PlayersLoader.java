package players;

import game.MarkType;
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
        consoleWriter.accept("Podaj imię gracza grającego x");
        playersRegister.registerPlayer(new Player(consoleReader.getString(), MarkType.CROSS));
        consoleWriter.accept("Podaj imię gracza grajacego o");
        playersRegister.registerPlayer(new Player(consoleReader.getString(), MarkType.NAUGHT));
        return playersRegister;
    }

}
