package game;

import board.Board;
import board.BoardBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import settings.BoardDimensions;
import settings.Settings;

import java.util.function.Consumer;

import static org.testng.Assert.assertEquals;

public class MoveScannerMajorDiagonalRectangle5x10BoardTest {

    MoveScanner boardScanner;
    Board board;
    MoveSupervisor moveSupervisor;
    Settings settings;

    @BeforeMethod
    public void beforeMethod(){
        //given
        Consumer<Exception> exceptionHandler = e -> System.out.println("Exception occured " + e.getMessage());
        BoardDimensions boardDimensions = new BoardDimensions(5, 10);
        BoardBuilder boardBuilder = new BoardBuilder(boardDimensions);
        board = new Board(boardBuilder.viaList(), boardDimensions);
        MovesRegistry movesRegistry = new MovesRegistry();
        settings = new Settings(exceptionHandler, boardDimensions, 5);
        boardScanner = new MoveScanner(movesRegistry, settings);
        moveSupervisor = new MoveSupervisor(movesRegistry, settings);
    }

    @Test
    public void givenCrossesAtMajorDiagonal_whenScanningField29_SequenceIs_xxxxx(){
        //given
        moveSupervisor.move("x", 29);
        moveSupervisor.move("x", 23);
        moveSupervisor.move("x", 17);
        moveSupervisor.move("x", 11);
        moveSupervisor.move("x", 5);
        //when
        Sequence sequence = boardScanner.scanMajorDiagonal(29);
        assertEquals(sequence.toString(), "xxxxx");
    }

    @Test
    public void givenCrossesAtMajorDiagonal_whenScanningField5_SequenceIs_xxxxx(){
        //given
        moveSupervisor.move("x", 29);
        moveSupervisor.move("x", 23);
        moveSupervisor.move("x", 17);
        moveSupervisor.move("x", 11);
        moveSupervisor.move("x", 5);
        //when
        Sequence sequence = boardScanner.scanMajorDiagonal(5);
        assertEquals(sequence.toString(), "xxxxx");
    }

    @Test
    public void givenCrossesAtMajorDiagonal_whenScanningField17_SequenceIs_xxxxx(){
        //given
        moveSupervisor.move("x", 29);
        moveSupervisor.move("x", 23);
        moveSupervisor.move("x", 17);
        moveSupervisor.move("x", 11);
        moveSupervisor.move("x", 5);
        //when
        Sequence sequence = boardScanner.scanMajorDiagonal(29);
        assertEquals(sequence.toString(), "xxxxx");
    }

    @Test
    public void givenCrossesAtField23And29_whenScanningField23_SequenceIs_xx(){
        //given
        moveSupervisor.move("x", 29);
        moveSupervisor.move("x", 23);
        //when
        Sequence sequence = boardScanner.scanMajorDiagonal(29);
        assertEquals(sequence.toString(), "xx");
    }

    @Test
    public void givenCrossesAndNoughtsAtMajorDiagonalFrom5To29_whenScanningField23_SequenceIsWithCrossesAndNoughts(){
        //given
        moveSupervisor.move("o", 29);
        moveSupervisor.move("x", 23);
        moveSupervisor.move("o", 17);
        moveSupervisor.move("x", 11);
        moveSupervisor.move("o", 5);
        //when
        Sequence sequence = boardScanner.scanMajorDiagonal(23);
        assertEquals(sequence.toString(), "oxoxo");
    }
}