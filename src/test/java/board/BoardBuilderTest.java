package board;

import org.testng.annotations.Test;
import settings.BoardDimensions;

import java.util.List;

import static org.testng.Assert.*;

public class BoardBuilderTest {

    @Test
    public void whenBoardSize3x3_ThenBoardElementCountIs9(){
        //given
        BoardDimensions boardDimensions = new BoardDimensions(3,3);
        BoardBuilder bulider = new BoardBuilder(boardDimensions);
        List<Integer> board = bulider.viaList();
        //when - then
        assertEquals(board.size(), 9);
    }

    @Test
    public void boardSize3x3_boardElementsValuesTest(){
        //given
        BoardDimensions boardDimensions = new BoardDimensions(3,3);
        BoardBuilder builder = new BoardBuilder(boardDimensions);
        List<Integer> board = builder.viaList();
        //when - then
        int[] expected = {0,1,2,3,4,5,6,7,8};
        assertEquals(board.toArray(), expected);
    }
}