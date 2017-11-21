package game;

import board.Board;
import board.BoardBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import settings.BoardDimensions;
import settings.Settings;

import java.util.function.Consumer;

import static org.testng.Assert.*;

public class MoveScannerMinorDiagonalTest {

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
        moveSupervisor = new MoveSupervisor(movesRegistry, settings);
    }

    @Test
    public void givenBoard3x3WithCrossesAtMinorDiagonal_whenScanningField4_seqenceIs_xxx(){
        //given
        moveSupervisor.move("x", 6);
        moveSupervisor.move("x", 4);
        moveSupervisor.move("x", 2);
        //when
        Sequence sequence = boardScanner.scanMinorDiagonal(4);
        //then
        assertEquals(sequence.toString(), "xxx");
    }

    @Test
    public void givenBoard3x3WithCrossesBelowMinorDiagonal_whenScanningField5_seqenceIs_xx(){
        //given
        moveSupervisor.move("x", 7);
        moveSupervisor.move("x", 5);
        //when
        Sequence sequence = boardScanner.scanMinorDiagonal(5);
        //then
        assertEquals(sequence.toString(), "xx");
    }

    @Test
    public void givenBoard3x3WithCrossesAboveMinorDiagonal_whenScanningField1_seqenceIs_xx(){
        //given
        moveSupervisor.move("x", 3);
        moveSupervisor.move("x", 1);
        //when
        Sequence sequence = boardScanner.scanMinorDiagonal(1);
        //then
        assertEquals(sequence.toString(), "xx");
    }

    @Test
    public void givenBoard3x3WithCrossAtTopLeftCorner_whenScanningIt_seqenceIs_x(){
        //given
        moveSupervisor.move("x", 0);
        //when
        Sequence sequence = boardScanner.scanMinorDiagonal(0);
        //then
        assertEquals(sequence.toString(), "x");
    }

    @Test
    public void givenBoard3x3WithCrossAtTopRightCorner_whenScanningIt_seqenceIs_x(){
        //given
        moveSupervisor.move("x", 2);
        //when
        Sequence sequence = boardScanner.scanMinorDiagonal(2);
        //then
        assertEquals(sequence.toString(), "x");
    }

    @Test
    public void givenBoard3x3WithCrossAtBottomRightCorner_whenScanningIt_seqenceIs_x(){
        //given
        moveSupervisor.move("x", 8);
        //when
        Sequence sequence = boardScanner.scanMinorDiagonal(8);
        //then
        assertEquals(sequence.toString(), "x");
    }

    @Test
    public void givenBoard3x3WithCrossAtBottomLeftCorner_whenScanningIt_seqenceIs_x(){
        //given
        moveSupervisor.move("x", 6);
        //when
        Sequence sequence = boardScanner.scanMinorDiagonal(6);
        //then
        assertEquals(sequence.toString(), "x");
    }
}
