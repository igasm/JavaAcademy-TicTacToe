package game;

import board.Board;
import board.BoardBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import settings.BoardDimensions;
import settings.Settings;

import java.util.function.Consumer;

import static org.testng.Assert.assertEquals;

public class MoveScannerMajorDiagonalRectangle5x3BoardTest {

    MoveScanner boardScanner;
    Board board;
    MoveSupervisor moveSupervisor;
    Settings settings;

    @BeforeMethod
    public void beforeMethod(){
        //given
        Consumer<Exception> exceptionHandler = e -> System.out.println("Exception occured " + e.getMessage());
        Consumer<String> consoleWriter = System.out::println;
        BoardDimensions boardDimensions = new BoardDimensions(5, 3);
        BoardBuilder boardBuilder = new BoardBuilder(boardDimensions);
        board = new Board(boardBuilder.viaList(), boardDimensions);
        MovesRegistry movesRegistry = new MovesRegistry();
        settings = new Settings(exceptionHandler, boardDimensions, 3);
        boardScanner = new MoveScanner(movesRegistry, settings);
        moveSupervisor = new MoveSupervisor(movesRegistry, settings);
    }

    @Test
    public void givenBoard5x3WithCrossesAtMajorDiagonal_whenScanningField6_SequenceIs_xxx(){
        //given
        moveSupervisor.move("x", 0);
        moveSupervisor.move("x", 6);
        moveSupervisor.move("x", 12);
        //when
        Sequence sequence = boardScanner.scanMajorDiagonal(6);
        assertEquals(sequence.toString(), "xxx");
    }

    @Test
    public void givenBoard5x3FullOfCrosses_whenScanningField0_SequenceIs_xxx(){
        //given
        for(int i=0; i<settings.getBoardElementsCount(); i++) {
            moveSupervisor.move("x", i);
        }
        //when
        Sequence sequence = boardScanner.scanMajorDiagonal(0);
        assertEquals(sequence.toString(), "xxx");
    }

    @Test
    public void givenBoard5x3FullOfCrosses_whenScanningField12_SequenceIs_xxx(){
        //given
        for(int i=0; i<settings.getBoardElementsCount(); i++) {
            moveSupervisor.move("x", i);
        }
        //when
        Sequence sequence = boardScanner.scanMajorDiagonal(12);
        assertEquals(sequence.toString(), "xxx");
    }

    @Test
    public void givenBoard5x3FullOfCrosses_whenScanningField4_SequenceIs_x(){
        //given
        for(int i=0; i<settings.getBoardElementsCount(); i++) {
            moveSupervisor.move("x", i);
        }
        //when
        Sequence sequence = boardScanner.scanMajorDiagonal(4);
        assertEquals(sequence.toString(), "x");
    }

    @Test
    public void givenBoard5x3FullOfCrosses_whenScanningField3_SequenceIs_xx(){
        //given
        for(int i=0; i<settings.getBoardElementsCount(); i++) {
            moveSupervisor.move("x", i);
        }
        //when
        Sequence sequence = boardScanner.scanMajorDiagonal(3);
        assertEquals(sequence.toString(), "xx");
    }


}