package game.settings;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SettingsReaderTest {

    @Test
    public void givenJSONFileWithSettings_bordWidth5_boardHeight5_winningCondition5_whenLoadingFile_suchSettingsAreReturned()
            throws SettingsLoadingException {
        String settingsFilePath = "./src/test/resources/settings.json";
        SettingsReader settingsReader = new SettingsReader(settingsFilePath);
        Settings settings = settingsReader.load();
        assertEquals(settings.getWinningCondition(), 5);
        assertEquals(settings.getBoardDimensions().getWidth(), 5);
        assertEquals(settings.getBoardDimensions().getHeight(), 5);
    }

    @Test(expectedExceptions = SettingsLoadingException.class)
    public void givenWrongPathToJSONFileWithSettings_errorExpected() throws SettingsLoadingException {
        String settingsFilePath = "./dummy_path/settings.json";
        SettingsReader settingsReader = new SettingsReader(settingsFilePath);
        settingsReader.load();
    }

}