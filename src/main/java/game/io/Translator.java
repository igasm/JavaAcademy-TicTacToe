package game.io;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Translator {

    private ResourceBundle resourceBundle = null;

    void load(String languageCode){

        if(languageCode.equalsIgnoreCase("en")) {
            resourceBundle = ResourceBundle.getBundle("messages", new Locale("en", "US"), ResourceBundle.Control.getNoFallbackControl(ResourceBundle.Control.FORMAT_PROPERTIES));
        }else{
            resourceBundle = ResourceBundle.getBundle("messages", new Locale("pl", "PL"), ResourceBundle.Control.getNoFallbackControl(ResourceBundle.Control.FORMAT_PROPERTIES));
        }
    }

    public String messageByCode(String key){

        String message;

        try {
            message = resourceBundle.getString(key);
            message = new String(message.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            message = "";
        }

       return message;
    }

    String getTranslatorLanguage(){
        return resourceBundle.getLocale().toString();
    }

}
