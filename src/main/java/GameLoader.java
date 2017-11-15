import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameLoader {

    final private Consumer<String> consoleWriter;
    final private Supplier<String> consoleReader;
    final private Settings settings;

    public GameLoader(Consumer<String> consoleWriter, Supplier<String> consoleReader, Settings settingsLoader) {
        this.consoleWriter = consoleWriter;
        this.consoleReader = consoleReader;
        this.settings = settingsLoader;
    }

    public BoardManager load(){
        BoardBuilder boardBuilder = new BoardBuilder(settings.getBoardDimensions());
        Board board = new Board(boardBuilder.buildBoardWithFieldNumbers(), boardBuilder.buildBoardWithMarks(), settings.getBoardDimensions());
        MoveSupervisor moveSupervisor = new MoveSupervisor(board, settings.getWinningCondition());
        PlayersRegister playersRegister = new PlayersRegister(2);
        consoleWriter.accept("Podaj imię pierwszego gracza");
        playersRegister.registerPlayer(new Player(consoleReader.get(), "x"));
        consoleWriter.accept("Podaj imię drugiego gracza");
        playersRegister.registerPlayer(new Player(consoleReader.get(), "o"));
        PlayersQueue playersQueue = new PlayersQueue(playersRegister);
        ScoresManager scoresManager = new ScoresManager(playersRegister);
        return new BoardManager(playersQueue, moveSupervisor, board, consoleWriter, settings, scoresManager);
    }
}
