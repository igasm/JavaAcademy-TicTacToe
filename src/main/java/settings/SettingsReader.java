package settings;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.function.Consumer;

class SettingsReader {

    private JSONParser jsonParser;
    private final String settingsFilePath;

    public SettingsReader(String settingsFilePath) {
        this.settingsFilePath = settingsFilePath;
        this.jsonParser = new JSONParser();
    }

    Settings load(){
        Settings settings;
        try {
            Object obj = jsonParser.parse(new FileReader(settingsFilePath));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject jsonObjectDimensions = (JSONObject) jsonObject.get("board_dimensions");
            Long width = (Long) jsonObjectDimensions.get("width");
            Long height = (Long) jsonObjectDimensions.get("height");
            BoardDimensions boardDimensions = new BoardDimensions(width.intValue(), height.intValue());
            int winningCondition = ((Long) jsonObject.get("winning_condition")).intValue();
            settings = new Settings(boardDimensions, winningCondition);
        }catch (Exception e){
            settings = null;
        }

        return settings;
    }
}
