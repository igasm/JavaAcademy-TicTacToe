package settings;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class SettingsValidatorTest {

    @DataProvider(name = "dataProviderForBordDimensionValidation")
    public Object[][] provideDataForBoardDimensionValidation(){
        Map<Integer, Boolean> map = new HashMap<>();
        map.put(-1000, false);
        map.put(-100, false);
        map.put(-10, false);
        map.put(-1, false);
        map.put(0, false);
        map.put(1, false);
        map.put(2, false);
        map.put(3, true);
        map.put(10, true);
        map.put(50, true);
        map.put(100, true);
        map.put(101, true);
        map.put(102, false);
        map.put(110, false);
        map.put(500, false);
        map.put(1000, false);
        return new Object[][] {{map}};
    }

    @Test(dataProvider = "dataProviderForBordDimensionValidation")
    public void given_minBorderDimension3_and_maxBorderDimension101_testBoardDimensionValidation(Map<Integer, Boolean> map){
        SettingsValidator settingsValidator = new SettingsValidator(3, 101, 3);
        for(Map.Entry<Integer, Boolean> entry : map.entrySet()){
            Assert.assertEquals(settingsValidator.validBorderDimension(entry.getKey()), entry.getValue().booleanValue());
        }
    }

    @DataProvider(name = "dataProviderForWinningConditionValidation")
    public Object[][] provideDataForWinningConditionValidation(){
        Map<Settings, Boolean> map = new HashMap<>();
        map.put(new Settings(new BoardDimensions(-100, -100), -100), false);
        map.put(new Settings(new BoardDimensions(0, 0), 0), false);
        map.put(new Settings(new BoardDimensions(3, 3), -10), false);
        map.put(new Settings(new BoardDimensions(3, 3), -1), false);
        map.put(new Settings(new BoardDimensions(3, 3), 0), false);
        map.put(new Settings(new BoardDimensions(3, 3), 1), false);

        map.put(new Settings(new BoardDimensions(3, 3), 2), false);
        map.put(new Settings(new BoardDimensions(3, 3), 3), true);
        map.put(new Settings(new BoardDimensions(3, 3), 4), false);
        map.put(new Settings(new BoardDimensions(3, 3), 10), false);
        map.put(new Settings(new BoardDimensions(3, 5), 3), true);
        map.put(new Settings(new BoardDimensions(3, 5), 5), false);
        map.put(new Settings(new BoardDimensions(3, 5), 15), false);

        map.put(new Settings(new BoardDimensions(10, 10), 1), false);
        map.put(new Settings(new BoardDimensions(10, 10), 2), false);
        map.put(new Settings(new BoardDimensions(10, 10), 3), true);
        map.put(new Settings(new BoardDimensions(10, 10), 5), true);
        map.put(new Settings(new BoardDimensions(10, 10), 10), true);
        map.put(new Settings(new BoardDimensions(10, 10), 15), false);

        map.put(new Settings(new BoardDimensions(100, 100), -100), false);
        map.put(new Settings(new BoardDimensions(100, 100), 0), false);
        map.put(new Settings(new BoardDimensions(100, 100), 1), false);
        map.put(new Settings(new BoardDimensions(100, 100), 2), false);
        map.put(new Settings(new BoardDimensions(100, 100), 3), true);
        map.put(new Settings(new BoardDimensions(100, 100), 5), true);
        map.put(new Settings(new BoardDimensions(100, 100), 50), true);
        map.put(new Settings(new BoardDimensions(100, 100), 100), true);
        map.put(new Settings(new BoardDimensions(100, 100), 101), false);
        map.put(new Settings(new BoardDimensions(100, 100), 150), false);

        map.put(new Settings(new BoardDimensions(100, 50), -10), false);
        map.put(new Settings(new BoardDimensions(100, 50), -1), false);
        map.put(new Settings(new BoardDimensions(100, 50), 0), false);
        map.put(new Settings(new BoardDimensions(100, 50), 1), false);
        map.put(new Settings(new BoardDimensions(100, 50), 2), false);
        map.put(new Settings(new BoardDimensions(100, 50), 3), true);
        map.put(new Settings(new BoardDimensions(100, 50), 10), true);
        map.put(new Settings(new BoardDimensions(100, 50), 50), true);
        map.put(new Settings(new BoardDimensions(100, 50), 55), false);
        map.put(new Settings(new BoardDimensions(100, 50), 100), false);
        map.put(new Settings(new BoardDimensions(100, 50), 150), false);

        return new Object[][] {{map}};
    }

    @Test(dataProvider = "dataProviderForWinningConditionValidation")
    public void given_minBorderDimension3_and_maxBorderDimension101_and_minWinningCondition3_winningConditionValidation(Map<Settings, Boolean> map){
        SettingsValidator settingsValidator = new SettingsValidator(3, 101, 3);
        Settings settings;
        for(Map.Entry<Settings, Boolean> entry : map.entrySet()){
            settings = entry.getKey();
            Assert.assertEquals(settingsValidator.validWinningCondition(settings.getBoardDimensions(), settings.getWinningCondition()), entry.getValue().booleanValue());
        }
    }
}