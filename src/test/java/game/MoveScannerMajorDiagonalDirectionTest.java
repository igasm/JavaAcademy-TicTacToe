package game;

import board.Board;
import board.BoardBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import settings.BoardDimensions;
import settings.Settings;

import java.util.function.Consumer;

import static org.testng.Assert.*;

public class MoveScannerMajorDiagonalDirectionTest {

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
    public void givenBoard3x3WithNoughtsAtDiagonal_whenScanningField8_SequenceShouldBe_ooo(){
        //given
        moveSupervisor.move("o", 0);
        moveSupervisor.move("x", 3);
        moveSupervisor.move("o", 4);
        moveSupervisor.move("x", 6);
        moveSupervisor.move("x", 7);
        moveSupervisor.move("o", 8);
        //when
        Sequence sequence = boardScanner.scanFromLeftTopToRightBottom(8);
        //then
        assertEquals(sequence.toString(), "ooo");
    }

    @Test
    public void givenBoard3x3WithNoughtsAtDiagonal_whenScanningField4_SequenceShouldBe_ooo(){
        //given
        moveSupervisor.move("o", 0);
        moveSupervisor.move("x", 3);
        moveSupervisor.move("o", 4);
        moveSupervisor.move("x", 6);
        moveSupervisor.move("x", 7);
        moveSupervisor.move("o", 8);
        //when
        Sequence sequence = boardScanner.scanFromLeftTopToRightBottom(4);
        //then
        assertEquals(sequence.toString(), "ooo");
    }

    @Test
    public void givenBoard3x3WithNoughtsAtDiagonal_whenScanningField0_SequenceShouldBe_ooo(){
        //given
        moveSupervisor.move("o", 0);
        moveSupervisor.move("x", 3);
        moveSupervisor.move("o", 4);
        moveSupervisor.move("x", 6);
        moveSupervisor.move("x", 7);
        moveSupervisor.move("o", 8);
        //when
        Sequence sequence = boardScanner.scanFromLeftTopToRightBottom(4);
        //then
        assertEquals(sequence.toString(), "ooo");
    }

    @Test
    public void givenBoard3x3WithNoughtsAboveDiagonal_whenScanningField1_SequenceShouldBe_oo(){
        //given
        moveSupervisor.move("o", 1);
        moveSupervisor.move("o", 5);
        //when
        Sequence sequence = boardScanner.scanFromLeftTopToRightBottom(1);
        //then
        assertEquals(sequence.toString(), "oo");
    }

    @Test
    public void givenBoard3x3WithNoughtsAboveDiagonal_whenScanningField5_SequenceShouldBe_oo(){
        //given
        moveSupervisor.move("o", 1);
        moveSupervisor.move("o", 5);
        //when
        Sequence sequence = boardScanner.scanFromLeftTopToRightBottom(5);
        //then
        assertEquals(sequence.toString(), "oo");
    }

    @Test
    public void givenBoard3x3WithNoughtsBelowDiagonal_whenScanningField3_SequenceShouldBe_oo(){
        //given
        moveSupervisor.move("o", 3);
        moveSupervisor.move("o", 7);
        //when
        Sequence sequence = boardScanner.scanFromLeftTopToRightBottom(3);
        //then
        assertEquals(sequence.toString(), "oo");
    }

    @Test
    public void givenBoard3x3WithNoughtsBelowDiagonal_whenScanningField7_SequenceShouldBe_oo(){
        //given
        moveSupervisor.move("o", 3);
        moveSupervisor.move("o", 7);
        //when
        Sequence sequence = boardScanner.scanFromLeftTopToRightBottom(7);
        //then
        assertEquals(sequence.toString(), "oo");
    }

}