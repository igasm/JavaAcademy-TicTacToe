package game;

import board.Board;
import board.BoardBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import settings.BoardDimensions;
import settings.Settings;

import java.util.function.Consumer;

import static org.testng.Assert.*;

public class MoveScannerHorizontalTest {

    MoveScanner boardScanner;
    Board board;
    MoveSupervisor moveSupervisor;

    @BeforeMethod
    public void beforeMethod(){
        //given
        Consumer<Exception> exceptionHandler = e -> System.out.println("Exception occured " + e.getMessage());
        Consumer<String> consoleWriter = System.out::println;
        BoardDimensions boardDimensions = new BoardDimensions(3, 3);
        BoardBuilder boardBuilder = new BoardBuilder(boardDimensions);
        board = new Board(boardBuilder.buildBoardWithFieldNumbers(), boardDimensions, consoleWriter);
        MovesRegistry movesRegistry = new MovesRegistry();
        Settings settings = new Settings(exceptionHandler, boardDimensions, 3);
        boardScanner = new MoveScanner(movesRegistry, settings);
        moveSupervisor = new MoveSupervisor(board, 3, movesRegistry, settings);
    }

    @Test
    public void givenBoard3x3With_oxo_AtFirstRow_whenScanningField2_SequenceShouldBe_oxo(){
        //given
        moveSupervisor.move("o", 0);
        moveSupervisor.move("x", 1);
        moveSupervisor.move("o", 2);
        moveSupervisor.move("o", 3);
        moveSupervisor.move("o", 4);
        moveSupervisor.move("x", 7);
        moveSupervisor.move("x", 8);

        //when
        Sequence sequence = boardScanner.scanHorizontally(2);
        //then
        assertEquals(sequence.toString(), "oxo");
    }

}