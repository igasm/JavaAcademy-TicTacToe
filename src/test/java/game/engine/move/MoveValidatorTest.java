package game.engine.move;

import game.engine.MarkType;
import game.engine.move.MoveValidator;
import game.engine.move.MovesRegistry;
import org.testng.annotations.Test;
import game.settings.BoardDimensions;
import game.settings.Settings;

import static org.testng.Assert.*;

public class MoveValidatorTest {

    @Test
    public void givenClearBoard3x3_whenMovingToField3_moveIsCorrect() throws NegativeFieldNumberException, FieldAlreadyMarkedException, TooBigFieldNumberException {
        //given
        Settings settings = new Settings(new BoardDimensions(3, 3), 3);
        MovesRegistry movesRegistry = new MovesRegistry();
        MoveValidator moveValidator = new MoveValidator(settings, movesRegistry);
        //when - then
        assertEquals(moveValidator.moveIsValid(3), true);
    }

    @Test(expectedExceptions = NegativeFieldNumberException.class)
    public void givenClearBoard3x3_whenMovingToFieldWithNumberLowerThen0_exceptionExpected() throws NegativeFieldNumberException, FieldAlreadyMarkedException, TooBigFieldNumberException {
        //given
        Settings settings = new Settings(new BoardDimensions(3, 3), 3);
        MovesRegistry movesRegistry = new MovesRegistry();
        MoveValidator moveValidator = new MoveValidator(settings, movesRegistry);
        //when - then
        moveValidator.moveIsValid(-1); //here -> exception expected
    }

    @Test(expectedExceptions = TooBigFieldNumberException.class)
    public void givenClearBoard3x3_whenMovingToFieldWithNumberGraterThenLastFieldNumber_exceptionExpected() throws NegativeFieldNumberException, FieldAlreadyMarkedException, TooBigFieldNumberException {
        //given
        Settings settings = new Settings(new BoardDimensions(3, 3), 3);
        MovesRegistry movesRegistry = new MovesRegistry();
        MoveValidator moveValidator = new MoveValidator(settings, movesRegistry);
        //when - then
        moveValidator.moveIsValid(12); //here -> exception expected
    }

    @Test(expectedExceptions = FieldAlreadyMarkedException.class)
    public void givenClearBoard3x3_whenMovingCheckingMoveOnFieldWithMark_exceptionExpected() throws NegativeFieldNumberException, FieldAlreadyMarkedException, TooBigFieldNumberException {
        //given
        Settings settings = new Settings(new BoardDimensions(3, 3), 3);
        MovesRegistry movesRegistry = new MovesRegistry();
        movesRegistry.addMove(5, MarkType.CROSS);
        MoveValidator moveValidator = new MoveValidator(settings, movesRegistry);
        //when - then
        moveValidator.moveIsValid(5); //here -> exception expected
    }

}