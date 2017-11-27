package game.settings;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SettingsValidatorTest {

    @DataProvider(name = "dataProviderForBordDimensionValidation")
    public Object[][] provideDataForBoardDimensionValidation(){
        return new Object[][]{
            {-1000, false},
            {-100, false},
            {-10, false},
            {-1, false},
            {0, false},
            {1, false},
            {2, false},
            {3, true},
            {10, true},
            {50, true},
            {100, true},
            {101, true},
            {102, false},
            {110, false},
            {500, false},
            {1000, false}
        };
    }

    @Test(dataProvider = "dataProviderForBordDimensionValidation")
    public void given_minBorderDimension3_and_maxBorderDimension101_testBoardDimensionValidation(Integer borderDimension, Boolean isValid){
        SettingsValidator settingsValidator = new SettingsValidator(3, 101, 3);
        Assert.assertEquals(settingsValidator.validBorderDimension(borderDimension), isValid.booleanValue());
    }

    @DataProvider(name = "dataProviderForWinningConditionValidation")
    public Object[][] provideDataForWinningConditionValidation(){

        return new Object[][]{
        {new Settings(new BoardDimensions(-100, -100), -100), false},
        {new Settings(new BoardDimensions(0, 0), 0), false},
        {new Settings(new BoardDimensions(3, 3), -10), false},
        {new Settings(new BoardDimensions(3, 3), -1), false},
        {new Settings(new BoardDimensions(3, 3), 0), false},
        {new Settings(new BoardDimensions(3, 3), 1), false},

        {new Settings(new BoardDimensions(3, 3), 2), false},
        {new Settings(new BoardDimensions(3, 3), 3), true},
        {new Settings(new BoardDimensions(3, 3), 4), false},
        {new Settings(new BoardDimensions(3, 3), 10), false},
        {new Settings(new BoardDimensions(3, 5), 3), true},
        {new Settings(new BoardDimensions(3, 5), 5), false},
        {new Settings(new BoardDimensions(3, 5), 15), false},

        {new Settings(new BoardDimensions(10, 10), 1), false},
        {new Settings(new BoardDimensions(10, 10), 2), false},
        {new Settings(new BoardDimensions(10, 10), 3), true},
        {new Settings(new BoardDimensions(10, 10), 5), true},
        {new Settings(new BoardDimensions(10, 10), 10), true},
        {new Settings(new BoardDimensions(10, 10), 15), false},

        {new Settings(new BoardDimensions(100, 100), -100), false},
        {new Settings(new BoardDimensions(100, 100), 0), false},
        {new Settings(new BoardDimensions(100, 100), 1), false},
        {new Settings(new BoardDimensions(100, 100), 2), false},
        {new Settings(new BoardDimensions(100, 100), 3), true},
        {new Settings(new BoardDimensions(100, 100), 5), true},
        {new Settings(new BoardDimensions(100, 100), 50), true},
        {new Settings(new BoardDimensions(100, 100), 100), true},
        {new Settings(new BoardDimensions(100, 100), 101), false},
        {new Settings(new BoardDimensions(100, 100), 150), false},

        {new Settings(new BoardDimensions(100, 50), -10), false},
        {new Settings(new BoardDimensions(100, 50), -1), false},
        {new Settings(new BoardDimensions(100, 50), 0), false},
        {new Settings(new BoardDimensions(100, 50), 1), false},
        {new Settings(new BoardDimensions(100, 50), 2), false},
        {new Settings(new BoardDimensions(100, 50), 3), true},
        {new Settings(new BoardDimensions(100, 50), 10), true},
        {new Settings(new BoardDimensions(100, 50), 50), true},
        {new Settings(new BoardDimensions(100, 50), 55), false},
        {new Settings(new BoardDimensions(100, 50), 100), false},
        {new Settings(new BoardDimensions(100, 50), 150), false}
        };
       
        
    }

    @Test(dataProvider = "dataProviderForWinningConditionValidation")
    public void given_minBorderDimension3_and_maxBorderDimension101_and_minWinningCondition3_winningConditionValidation(Settings settings, boolean isWinningconditionProper){
        SettingsValidator settingsValidator = new SettingsValidator(3, 101, 3);
        Assert.assertEquals(settingsValidator.validWinningCondition(settings.getBoardDimensions(), settings.getWinningCondition()), isWinningconditionProper);
    }
}