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
    PlayersRegister playersRegister;

    public PlayersLoader(Consumer<String> consoleWriter, ConsoleReader consoleReader) {
        this.consoleWriter = consoleWriter;
        this.consoleReader = consoleReader;
        playersRegister = new PlayersRegister(2);
    }

    public PlayersRegister load(){
        boolean registered = false;
        while (!registered){
            registered = registerPlayer(MarkType.CROSS);
        }
        registered = false;
        while (!registered){
            registered = registerPlayer(MarkType.NAUGHT);
        }
        return playersRegister;
    }

    private boolean registerPlayer(MarkType markType){
        boolean registered = false;
        try {
            consoleWriter.accept("Podaj imię gracza grającego " + markType.toString());
            playersRegister.registerPlayer(new Player(consoleReader.getString(), markType));
            registered = true;
        } catch (InvalidPlayerNameException e) {
            consoleWriter.accept(e.getMessage());
        }
        return registered;
    }

}
