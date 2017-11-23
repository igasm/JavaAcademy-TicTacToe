package game;

import org.testng.annotations.Test;
import settings.BoardDimensions;
import settings.Settings;

import java.util.function.Consumer;

import static org.testng.Assert.*;

public class MoveValidatorTest {

    @Test
    public void givenClearBoard3x3_whenMovingToField3_moveIsCorrect(){
        //given
        Settings settings = new Settings(new BoardDimensions(3, 3), 3);
        MovesRegistry movesRegistry = new MovesRegistry();
        MoveValidator moveValidator = new MoveValidator(settings, movesRegistry);
        //when - then
        assertEquals(moveValidator.moveIsValid(3), true);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void givenClearBoard3x3_whenMovingToFieldWithNumberLowerThen0_exceptionExpected(){
        //given
        Settings settings = new Settings(new BoardDimensions(3, 3), 3);
        MovesRegistry movesRegistry = new MovesRegistry();
        MoveValidator moveValidator = new MoveValidator(settings, movesRegistry);
        //when - then
        moveValidator.moveIsValid(-1); //here -> exception expected
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void givenClearBoard3x3_whenMovingToFieldWithNumberGraterThenLastFieldNumber_exceptionExpected(){
        //given
        Settings settings = new Settings(new BoardDimensions(3, 3), 3);
        MovesRegistry movesRegistry = new MovesRegistry();
        MoveValidator moveValidator = new MoveValidator(settings, movesRegistry);
        //when - then
        moveValidator.moveIsValid(12); //here -> exception expected
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void givenClearBoard3x3_whenMovingCheckingMoveOnFieldWithMark_exceptionExpected(){
        //given
        Settings settings = new Settings(new BoardDimensions(3, 3), 3);
        MovesRegistry movesRegistry = new MovesRegistry();
        movesRegistry.addMove(5, MarkType.CROSS);
        MoveValidator moveValidator = new MoveValidator(settings, movesRegistry);
        //when - then
        moveValidator.moveIsValid(5); //here -> exception expected
    }

}