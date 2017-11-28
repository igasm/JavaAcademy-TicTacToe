package game.engine.move;

import game.engine.MarkType;
import game.engine.move.MoveSupervisor;
import game.engine.move.MovesRegistry;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import game.settings.BoardDimensions;
import game.settings.Settings;

import static org.testng.Assert.*;

public class MoveSupervisorTest {

    MoveSupervisor moveSupervisor;

    @BeforeMethod
    public void beforeMethod(){
        BoardDimensions boardDimensions = new BoardDimensions(3, 3);
        Settings settings = new Settings(boardDimensions, 3);
        moveSupervisor = new MoveSupervisor(new MovesRegistry(), settings);
    }

    @Test
    public void givenBoard3x3_WhenNoMoveIsMade_ThereAreFreeMoves(){
        //when - then
        assertTrue(moveSupervisor.isFreeMoveExists());
    }

    @Test
    public void givenBoard3x3_WhenOneMoveIsMade_ThenThereAreFreeMoves(){
        moveSupervisor.move(MarkType.CROSS, 0);
        assertTrue(moveSupervisor.isFreeMoveExists());
    }

    @Test
    public void givenBoard3x3_When3MovesAreMade_ThenThereAreFreeMoves(){
        moveSupervisor.move(MarkType.CROSS, 0);
        moveSupervisor.move(MarkType.CROSS, 3);
        moveSupervisor.move(MarkType.CROSS, 8);
        assertTrue(moveSupervisor.isFreeMoveExists());
    }

    @Test
    public void givenBoard3x3_When9MovesAreMade_ThenThereAreNoFreeMoves(){
        for(int i=0; i<9; i++){
            moveSupervisor.move(MarkType.CROSS, i);
        }
        assertFalse(moveSupervisor.isFreeMoveExists());
    }
}