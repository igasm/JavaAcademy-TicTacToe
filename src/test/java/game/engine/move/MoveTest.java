package game.engine.move;

import game.engine.MarkType;
import game.engine.ScoresManager;
import game.io.ConsoleReader;
import game.io.Writer;
import game.io.WriterBuilder;
import game.players.Player;
import game.players.PlayersRegister;
import game.settings.BoardDimensions;
import game.settings.Settings;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class MoveTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream originalOutStream;

    @BeforeMethod
    public void setUpStream(){
        originalOutStream = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @AfterMethod
    public void cleanUpStream(){
        System.setOut(originalOutStream);
    }

    @Test
    public void givenBoardDimension3x3_movesRegistryEmpty_whenMovingSomeFieldInBord_moveIsCorrect(){
        Writer writer = new WriterBuilder().byDefault();
        PlayersRegister playersRegister = new PlayersRegister(2);
        Player playerA = new Player("playerA", MarkType.CROSS);
        Player playerB = new Player("playerB", MarkType.NAUGHT);
        playersRegister.registerPlayer(playerA);
        playersRegister.registerPlayer(playerB);
        Move move = new MoveBuilder()
                .withWriter(writer)
                .withSettings(new Settings(new BoardDimensions(3, 3), 3))
                .withScoresManager(new ScoresManager(new PlayersRegister(2)))
                .withMovesRegistry(new MovesRegistry())
                .withConsoleReader(new ConsoleReader(writer))
                .build();
        assertTrue(move.isMoveCorrect(playerA, 0));
    }

    @Test
    public void givenBoardDimension3x3_movesRegistryEmpty_whenMovingSomeFieldOutsideBord_moveIsIncorrect(){
        Writer writer = new WriterBuilder().byDefault();
        PlayersRegister playersRegister = new PlayersRegister(2);
        Player playerA = new Player("playerA", MarkType.CROSS);
        Player playerB = new Player("playerB", MarkType.NAUGHT);
        playersRegister.registerPlayer(playerA);
        playersRegister.registerPlayer(playerB);
        Move move = new MoveBuilder()
                .withWriter(writer)
                .withSettings(new Settings(new BoardDimensions(3, 3), 3))
                .withScoresManager(new ScoresManager(new PlayersRegister(2)))
                .withMovesRegistry(new MovesRegistry())
                .withConsoleReader(new ConsoleReader(writer))
                .build();
        assertFalse(move.isMoveCorrect(playerA, 22));
    }

    @Test
    public void  givenBoardDimension3x3_movesRegistryEmpty_playerRegisterWithSomeDummyPlayer_offerPlayerMoveMethodPrintCheck(){
        ByteArrayInputStream in = new ByteArrayInputStream("5".getBytes());
        InputStream orgInStream = System.in;
        System.setIn(in);

        Writer writer = new WriterBuilder().byDefault();
        PlayersRegister playersRegister = new PlayersRegister(2);
        Player dummy = new Player("dummyPlayer", MarkType.CROSS);
        playersRegister.registerPlayer(dummy);
        Move move = new MoveBuilder()
                .withWriter(writer)
                .withSettings(new Settings(new BoardDimensions(3, 3), 3))
                .withScoresManager(new ScoresManager(playersRegister))
                .withMovesRegistry(new MovesRegistry())
                .withConsoleReader(new ConsoleReader(writer))
                .build();
        move.offerPlayerMove(dummy);
        assertEquals(outContent.toString().trim(), "Ruch dla dummyPlayer (x), podaj numer pola");

        outContent.reset();
        System.setIn(orgInStream);
    }

    @Test
    public void  givenBoardDimension3x3_playerRegisterWithSomeDummyPlayer_whenAllMovesMade_matchEnds(){
        Writer writer = new WriterBuilder().byDefault();
        PlayersRegister playersRegister = new PlayersRegister(2);
        Player dummy = new Player("dummyPlayer1", MarkType.CROSS);
        Player dummy2 = new Player("dummyPlayer2", MarkType.NAUGHT);
        playersRegister.registerPlayer(dummy);
        playersRegister.registerPlayer(dummy2);
        MovesRegistry movesRegistry = new MovesRegistry();
        movesRegistry.addMove(0, dummy.getMark());
        movesRegistry.addMove(1, dummy.getMark());
        movesRegistry.addMove(2, dummy2.getMark());
        movesRegistry.addMove(3, dummy2.getMark());
        movesRegistry.addMove(4, dummy.getMark());
        movesRegistry.addMove(5, dummy2.getMark());
        movesRegistry.addMove(6, dummy.getMark());
        movesRegistry.addMove(7, dummy2.getMark());
        movesRegistry.addMove(8, dummy.getMark());
        Move move = new MoveBuilder()
                .withWriter(writer)
                .withSettings(new Settings(new BoardDimensions(3, 3), 3))
                .withScoresManager(new ScoresManager(playersRegister))
                .withMovesRegistry(movesRegistry)
                .withConsoleReader(new ConsoleReader(writer))
                .build();

        //when
        move.checkDraw();
        //then
        assertEquals(move.getMatchState(), MatchState.MATCH_END);
    }


}