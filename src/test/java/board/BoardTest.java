package board;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import settings.BoardDimensions;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class BoardTest {

    Board board;

    @BeforeTest
    public void beforeTest(){
        Integer[] boardArray = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        List<Integer> boardList = Arrays.asList(boardArray);
        BoardDimensions boardDimensions = new BoardDimensions(3, 3);
        board = new Board(boardList, boardDimensions);
    }

    @Test
    public void boardToStringTest(){
        String newline = System.getProperty("line.separator");
        String expected =   "[  0][  1][  2]" + newline +
                "[  3][  4][  5]" + newline +
                "[  6][  7][  8]";
        assertEquals(board.toString(), expected);
    }

    @Test
    public void giveBoardFieldEnumarationStartedFrom0_checkingNumberOfField0_returns0(){
        assertEquals(board.getFieldNumber(0), 0);
    }

}