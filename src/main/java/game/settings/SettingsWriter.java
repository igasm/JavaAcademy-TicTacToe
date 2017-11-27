package game.settings;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

class SettingsWriter {

    private final String settingsFilePath;

    SettingsWriter(String settingsFilePath) {
        this.settingsFilePath = settingsFilePath;
    }

    public boolean save(Settings settings) {
        boolean saved = true;

        JSONObject mainObj = new JSONObject();
        JSONObject boarDimensionsObj = new JSONObject();
        boarDimensionsObj.put("width", settings.getBoardDimensions().getWidth());
        boarDimensionsObj.put("height", settings.getBoardDimensions().getHeight());
        mainObj.put("board_dimensions", boarDimensionsObj);
        mainObj.put("winning_condition", settings.getWinningCondition());

        try (FileWriter file = new FileWriter(settingsFilePath)) {
            file.write(mainObj.toJSONString());
            file.flush();
        } catch (IOException e) {
            saved = false;
        }

        return saved;
    }
}
