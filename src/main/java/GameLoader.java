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
        Board board = new Board(boardBuilder.buildBoardWithFieldNumbers(), boardBuilder.buildBoardWithMarks(), settings.getBoardDimensions(), consoleWriter);
        MovesRegistry movesRegistry = new MovesRegistry();
        movesRegistry.register(board);
        MoveSupervisor moveSupervisor = new MoveSupervisor(board, settings.getWinningCondition(), movesRegistry);
        PlayersLoader playersLoader = new PlayersLoader(consoleWriter, consoleReader);
        PlayersRegister playersRegister = playersLoader.load();
        PlayersQueue playersQueue = new PlayersQueue(playersRegister);
        ScoresManager scoresManager = new ScoresManager(playersRegister);
        return new BoardManager(playersQueue, moveSupervisor, board, consoleWriter, settings, scoresManager);
    }
}
