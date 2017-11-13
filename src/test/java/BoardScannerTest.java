import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BoardScannerTest {

    BoardScanner boardScanner;
    Board board;

    @BeforeMethod
    public void beforeMethod(){
        //given
        BoardDimensions boardDimensions = new BoardDimensions(3, 3);
        BoardBuilder boardBuilder = new BoardBuilder(boardDimensions);
        board = new Board(boardBuilder.buildBoardWithFieldNumbers(),
                boardBuilder.buildBoardWithMarks(),
                boardDimensions);
        boardScanner = new BoardScanner(board);
    }

    @Test
    public void givenClearBoard3x3_whenScanningVerticallyField4_shouldEquals_eee(){
        //when
        Sequence sequence = boardScanner.scanVertically(4);
        //then
        assertEquals(sequence.toString(), "eee");
    }

    @Test
    public void givenBoard3x3WithCrossAtField4_whenScanningVerticallyField4_shouldEquals_exe(){
        //given
        MoveSupervisor moveSupervisor = new MoveSupervisor(board);
        moveSupervisor.move("x", 4);
        //when
        Sequence sequence = boardScanner.scanVertically(4);
        //then
        assertEquals(sequence.toString(), "exe");
    }

    @Test
    public void givenClearBoard3x3_ScanningHorizontallyField4_shouldEquals_eee(){
        //when
        Sequence sequence = boardScanner.scanHorizontally(4);
        //then
        assertEquals(sequence.toString(), "eee");
    }

    @Test
    public void givenBoard3x3WithCrossAtField4_whenScanningHorizontallyField4_shouldEquals_exe(){
        //given
        MoveSupervisor moveSupervisor = new MoveSupervisor(board);
        moveSupervisor.move("x", 4);
        //when
        Sequence sequence = boardScanner.scanHorizontally(4);
        //then
        assertEquals(sequence.toString(), "exe");
    }

    @Test
    public void givenBoard3x3WithCrossAtField7_whenScanningHorizontallyField4_shouldEquals_eex(){
        //given
        MoveSupervisor moveSupervisor = new MoveSupervisor(board);
        moveSupervisor.move("x", 7);
        //when
        Sequence sequence = boardScanner.scanHorizontally(4);
        //then
        assertEquals(sequence.toString(), "eex");
    }

    @Test
    public void givenBoard3x3WithCrossesAtDiagonal_scanTest(){
        //given
        MoveSupervisor moveSupervisor = new MoveSupervisor(board);
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
        MoveSupervisor moveSupervisor = new MoveSupervisor(board);
        moveSupervisor.move("x", 6);
        moveSupervisor.move("x", 4);
        moveSupervisor.move("x", 2);
        //when
        Sequence sequence = boardScanner.scanFromLeftTopToRightBottom(4);
        //then
        assertEquals(sequence.toString(), "exe");
    }

    @Test
    public void givenBoard3x3WithCrossesAtDiagonal_scanTest3(){
        //given
        MoveSupervisor moveSupervisor = new MoveSupervisor(board);
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
        MoveSupervisor moveSupervisor = new MoveSupervisor(board);
        moveSupervisor.move("x", 6);
        moveSupervisor.move("x", 4);
        moveSupervisor.move("x", 2);
        //when
        Sequence sequence = boardScanner.scanFromLeftTopToRightBottom(4);
        //then
        assertEquals(sequence.toString(), "exe");
    }


}