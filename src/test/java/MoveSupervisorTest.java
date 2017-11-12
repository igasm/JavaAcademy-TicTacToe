import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MoveSupervisorTest {

    MoveSupervisor moveSupervisor;

    @BeforeTest
    public void beforeTest(){
        BoardDimensions boardDimensions = new BoardDimensions(3, 3);
        BoardBuilder boardBuilder = new BoardBuilder(boardDimensions);
        Board board = new Board(boardBuilder.buildBoardWithFieldNumbers(),
                boardBuilder.buildBoardWithMarks(),
                boardDimensions);
        moveSupervisor = new MoveSupervisor(board);
    }

    @Test
    public void givenBoard3x3_WhenOneMoveIsMade_ThenThereAreFreeMoves(){
        BoardDimensions boardDimensions = new BoardDimensions(3, 3);
        BoardBuilder boardBuilder = new BoardBuilder(boardDimensions);
        Board board = new Board(boardBuilder.buildBoardWithFieldNumbers(),
                                boardBuilder.buildBoardWithMarks(),
                                boardDimensions);
        MoveSupervisor moveSupervisor = new MoveSupervisor(board);
        moveSupervisor.move("x", 0);
        assertTrue(moveSupervisor.isFreeMoveExists());
    }

    @Test
    public void givenBoard3x3_When3MovesAreMade_ThenThereAreFreeMoves(){
        BoardDimensions boardDimensions = new BoardDimensions(3, 3);
        BoardBuilder boardBuilder = new BoardBuilder(boardDimensions);
        Board board = new Board(boardBuilder.buildBoardWithFieldNumbers(),
                boardBuilder.buildBoardWithMarks(),
                boardDimensions);
        MoveSupervisor moveSupervisor = new MoveSupervisor(board);
        moveSupervisor.move("x", 0);
        moveSupervisor.move("x", 3);
        moveSupervisor.move("x", 8);
        assertTrue(moveSupervisor.isFreeMoveExists());
    }

    @Test
    public void givenBoard3x3_When9MovesAreMade_ThenThereAreNoFreeMoves(){
        BoardDimensions boardDimensions = new BoardDimensions(3, 3);
        BoardBuilder boardBuilder = new BoardBuilder(boardDimensions);
        Board board = new Board(boardBuilder.buildBoardWithFieldNumbers(),
                boardBuilder.buildBoardWithMarks(),
                boardDimensions);
        MoveSupervisor moveSupervisor = new MoveSupervisor(board);
        for(int i=0; i<9; i++){
            moveSupervisor.move("x", i);
        }
        assertFalse(moveSupervisor.isFreeMoveExists());
    }

}