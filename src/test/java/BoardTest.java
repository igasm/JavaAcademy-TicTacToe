import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BoardTest {

    @Test
    public void whenClearBoardWithSize3x3_ExpectedBoardPrint(){
        BoardDimensions boardDimensions = new BoardDimensions(3,3);
        BoardBuilder builder = new BoardBuilder(boardDimensions);
        Board board = new Board(builder.buildBoardWithFieldNumbers(), builder.buildBoardWithMarks() ,boardDimensions);
        //when - then
        String expectedPrint = "012\n345\n678";
        assertEquals(board.getView(), expectedPrint);
    }

    @Test
    public void whenSettingCrossOnFirstField_ExpectedBoardPrint(){
        BoardDimensions boardDimensions = new BoardDimensions(3,3);
        BoardBuilder builder = new BoardBuilder(boardDimensions);
        Board board = new Board(builder.buildBoardWithFieldNumbers(), builder.buildBoardWithMarks() ,boardDimensions);
        //when
        board.setCross(0);
        //then
        String expectedPrint = "x12\n345\n678";
        assertEquals(board.getView(), expectedPrint);
    }

    @Test
    public void whenSettingNoughtOnSeventhField_ExpectedBoardPrint(){
        BoardDimensions boardDimensions = new BoardDimensions(3,3);
        BoardBuilder builder = new BoardBuilder(boardDimensions);
        Board board = new Board(builder.buildBoardWithFieldNumbers(), builder.buildBoardWithMarks() ,boardDimensions);
        //when
        board.setNought(7);
        //then
        String expectedPrint = "012\n345\n6o8";
        assertEquals(board.getView(), expectedPrint);
    }



}