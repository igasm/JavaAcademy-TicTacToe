import java.util.function.Consumer;
import java.util.function.Supplier;

public class PlayersLoader {

    private final Consumer<String> consoleWriter;
    private final Supplier<String> consoleReader;

    public PlayersLoader(Consumer<String> consoleWriter, Supplier<String> consoleReader) {
        this.consoleWriter = consoleWriter;
        this.consoleReader = consoleReader;
    }

    public PlayersRegister load(){
        PlayersRegister playersRegister = new PlayersRegister(2);
        consoleWriter.accept("Podaj imię pierwszego gracza");
        playersRegister.registerPlayer(new Player(consoleReader.get(), "x"));
        consoleWriter.accept("Podaj imię drugiego gracza");
        playersRegister.registerPlayer(new Player(consoleReader.get(), "o"));
        return playersRegister;
    }

}
