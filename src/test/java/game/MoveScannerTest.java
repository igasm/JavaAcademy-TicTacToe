package game;

import board.Board;
import board.BoardBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import settings.BoardDimensions;
import settings.Settings;

import java.util.function.Consumer;

import static org.testng.Assert.*;

public class MoveScannerTest {

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
    public void givenBoard3x3WithCrossesAtDiagonal_scanTest(){
        //given
        moveSupervisor.move("x", 0);
        moveSupervisor.move("x", 4);
        moveSupervisor.move("x", 8);
        //when
        Sequence sequence = boardScanner.scanFromLeftTopToRightBottom(4);
        //then
        assertEquals(sequence.toString(), "xxx");
    }

    @Test
    public void givenBoard3x3WithCrossesAtDiagonal_scanTest2(){
        //given
        moveSupervisor.move("x", 6);
        moveSupervisor.move("x", 4);
        moveSupervisor.move("x", 2);
        //when
        Sequence sequence = boardScanner.scanFromLeftTopToRightBottom(4);
        //then
        assertEquals(sequence.toString(), "x");
    }

    @Test
    public void givenBoard3x3WithCrossesAtDiagonal_scanTest3(){
        //given
        moveSupervisor.move("x", 6);
        moveSupervisor.move("x", 4);
        moveSupervisor.move("x", 2);
        //when
        Sequence sequence = boardScanner.scanFromLeftBottomToRightTop(4);
        //then
        assertEquals(sequence.toString(), "xxx");
    }

    @Test
    public void givenBoard3x3WithCrossesAtDiagonal_scanTest4(){
        //given
        moveSupervisor.move("x", 6);
        moveSupervisor.move("x", 4);
        moveSupervisor.move("x", 2);
        //when
        Sequence sequence = boardScanner.scanFromLeftTopToRightBottom(4);
        //then
        assertEquals(sequence.toString(), "x");
    }

    @Test
    public void givenBoard3x3WithCrossAtTopLeft_SequenceIs_x(){
        //given
        moveSupervisor.move("x", 0);
        //when
        Sequence sequence = boardScanner.scanFromLeftTopToRightBottom(0);
        assertEquals(sequence.toString(), "x");
    }

    @Test
    public void givenBoard3x3WithCrossAtTopRight_SequenceIs_x(){
        //given
        moveSupervisor.move("x", 2);
        //when
        Sequence sequence = boardScanner.scanFromLeftTopToRightBottom(2);
        assertEquals(sequence.toString(), "x");
    }

    @Test
    public void givenBoard3x3WithCrossAtBottomLeft_SequenceIs_x(){
        //given
        moveSupervisor.move("x", 6);
        //when
        Sequence sequence = boardScanner.scanFromLeftTopToRightBottom(6);
        assertEquals(sequence.toString(), "x");
    }

    @Test
    public void givenBoard3x3WithCrossAtBottomRight_SequenceIs_x(){
        //given
        moveSupervisor.move("x", 8);
        //when
        Sequence sequence = boardScanner.scanFromLeftTopToRightBottom(8);
        assertEquals(sequence.toString(), "x");
    }
}