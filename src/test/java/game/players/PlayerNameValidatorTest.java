package game.players;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PlayerNameValidatorTest {

    private PlayerNameValidator playerNameValidator;

    @BeforeTest
    public void beforeTest(){
        playerNameValidator = new PlayerNameValidator();
    }

    @Test(expectedExceptions = InvalidPlayerNameException.class)
    public void whenValidatingPlayerNameWithOnlyWhitespacesInName_errorExpected() throws InvalidPlayerNameException {
        playerNameValidator.validate("     ");
    }

    @Test(expectedExceptions = InvalidPlayerNameException.class)
    public void whenValidatingPlayerNameWithEmptyString_errorExpected() throws InvalidPlayerNameException {
        playerNameValidator.validate("");
    }

    @Test(expectedExceptions = InvalidPlayerNameException.class)
    public void whenValidatingPlayerNameWithSpecialCharacters_errorExpected() throws InvalidPlayerNameException {
        playerNameValidator.validate("John?*_");
    }

    @Test
    public void whenValidatingPlayerNameWithAlphanumerical_nameIsValid() throws InvalidPlayerNameException {
        assertTrue(playerNameValidator.validate("John23"));
    }

    @Test
    public void whenValidatingPlayerNameWithOnlyLetter_nameIsValid() throws InvalidPlayerNameException {
        assertTrue(playerNameValidator.validate("John"));
    }
}