package game;

import board.Board;
import board.BoardBuilder;
import game.MoveSupervisor;
import game.MovesRegistry;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import settings.BoardDimensions;
import settings.Settings;

import java.util.function.Consumer;

import static org.testng.Assert.*;

public class MoveSupervisorTest {

    MoveSupervisor moveSupervisor;

    @BeforeMethod
    public void beforeMethod(){
        Consumer<Exception> exceptionHandler = e -> System.out.println("Exception occured " + e.getMessage());
        BoardDimensions boardDimensions = new BoardDimensions(3, 3);
        BoardBuilder boardBuilder = new BoardBuilder(boardDimensions);
        Consumer<String> consoleWriter = System.out::println;
        Board board = new Board(boardBuilder.buildBoardWithFieldNumbers(), boardDimensions, consoleWriter);
        Settings settings = new Settings(exceptionHandler, boardDimensions, 3);
        moveSupervisor = new MoveSupervisor(board, 3, new MovesRegistry(), settings);
    }

    @Test
    public void givenBoard3x3_WhenOneMoveIsMade_ThenThereAreFreeMoves(){
        moveSupervisor.move("x", 0);
        assertTrue(moveSupervisor.isFreeMoveExists());
    }

    @Test
    public void givenBoard3x3_When3MovesAreMade_ThenThereAreFreeMoves(){
        moveSupervisor.move("x", 0);
        moveSupervisor.move("x", 3);
        moveSupervisor.move("x", 8);
        assertTrue(moveSupervisor.isFreeMoveExists());
    }

    @Test
    public void givenBoard3x3_When9MovesAreMade_ThenThereAreNoFreeMoves(){
                for(int i=0; i<9; i++){
            moveSupervisor.move("x", i);
        }
        assertFalse(moveSupervisor.isFreeMoveExists());
    }

}