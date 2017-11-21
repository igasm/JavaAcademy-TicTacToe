package game;

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
        Settings settings = new Settings(exceptionHandler, boardDimensions, 3);
        moveSupervisor = new MoveSupervisor(new MovesRegistry(), settings);
    }

    @Test
    public void givenBoard3x3_WhenNoMoveIsMade_ThereAreFreeMoves(){
        //when - then
        assertTrue(moveSupervisor.isFreeMoveExists());
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

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void givenBoard3x3_WhenAddingMarkT_ExceptionExpected(){
        moveSupervisor.move("T", 0);
    }
}