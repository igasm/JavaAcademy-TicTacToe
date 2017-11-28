package game.io;

import game.settings.BoardDimensions;
import game.settings.Settings;
import game.settings.SettingsLoadingException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class Translator {

    private final HashMap<String, String> translationList;
    private final String languageFilePath = "./src/main/resources/messages_template.json";


    Translator() {
        translationList = null;
    }

    public void load(String languageCode){
        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse(new FileReader(languageFilePath));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray messagesArray = (JSONArray) jsonObject.get("messages");

            Iterator<String> iterator = messagesArray.iterator();
            while (iterator.hasNext()){
                String messageCode = (String) jsonObject.get("code");
                String message = (String) jsonObject.get(languageCode.toLowerCase());
                translationList.put(messageCode, message);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
