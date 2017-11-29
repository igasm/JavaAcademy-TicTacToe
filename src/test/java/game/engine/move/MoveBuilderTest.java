package game.engine.move;

import game.engine.ScoresManager;
import game.io.ConsoleReader;
import game.io.Writer;
import game.io.WriterBuilder;
import game.players.PlayersRegister;
import game.settings.BoardDimensions;
import game.settings.Settings;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MoveBuilderTest {

    @Test
    public void whenAllWithSet_thenBuildIsOK(){
        Writer writer = new WriterBuilder().buildByDefault();
        Move move = new MoveBuilder().withConsoleReader(new ConsoleReader(writer))
                .withMovesRegistry(new MovesRegistry())
                .withScoresManager(new ScoresManager(new PlayersRegister(2)))
                .withSettings(new Settings(new BoardDimensions(3,3), 3))
                .withWriter(writer)
                .build();
        assertTrue(true);
    }

    @Test(expectedExceptions = AssertionError.class)
    public void whenConsoleReaderNotSet_thenBuildFails(){
        Writer writer = new WriterBuilder().buildByDefault();
        Move move = new MoveBuilder()
                .withMovesRegistry(new MovesRegistry())
                .withScoresManager(new ScoresManager(new PlayersRegister(2)))
                .withSettings(new Settings(new BoardDimensions(3,3), 3))
                .withWriter(writer)
                .build();
    }

    @Test(expectedExceptions = AssertionError.class)
    public void whenMovesRegistryNotSet_thenBuildFails(){
        Writer writer = new WriterBuilder().buildByDefault();
        Move move = new MoveBuilder()
                .withConsoleReader(new ConsoleReader(writer))
                .withScoresManager(new ScoresManager(new PlayersRegister(2)))
                .withSettings(new Settings(new BoardDimensions(3,3), 3))
                .withWriter(writer)
                .build();
    }

    @Test(expectedExceptions = AssertionError.class)
    public void whenScoresManagerNotSet_thenBuildFails(){
        Writer writer = new WriterBuilder().buildByDefault();
        Move move = new MoveBuilder()
                .withConsoleReader(new ConsoleReader(writer))
                .withMovesRegistry(new MovesRegistry())
                .withSettings(new Settings(new BoardDimensions(3,3), 3))
                .withWriter(writer)
                .build();
    }

    @Test(expectedExceptions = AssertionError.class)
    public void whenSettingsNotSet_thenBuildFails(){
        Writer writer = new WriterBuilder().buildByDefault();
        Move move = new MoveBuilder().withConsoleReader(new ConsoleReader(writer))
                .withMovesRegistry(new MovesRegistry())
                .withScoresManager(new ScoresManager(new PlayersRegister(2)))
                .withWriter(writer)
                .build();
    }

    @Test(expectedExceptions = AssertionError.class)
    public void whenWriterNotSet_thenBuildFails(){
        Writer writer = new WriterBuilder().buildByDefault();
        Move move = new MoveBuilder().withConsoleReader(new ConsoleReader(writer))
                .withMovesRegistry(new MovesRegistry())
                .withScoresManager(new ScoresManager(new PlayersRegister(2)))
                .withSettings(new Settings(new BoardDimensions(3,3), 3))
                .build();
    }

}