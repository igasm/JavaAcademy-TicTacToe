package game.io;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TranslatorTest {

    @Test
    public void loadingTranslatorWithLanguageCode_en_translatorLanguageIsEn(){
        Translator translator = new Translator();
        translator.load("en");
        assertEquals(translator.getTranslatorLanguage(), "en");
    }

    @Test
    public void loadingTranslatorWithLanguageCode_pl_translatorLanguageIsPl(){
        Translator translator = new Translator();
        translator.load("pl");
        assertEquals(translator.getTranslatorLanguage(), "pl");
    }

    @Test
    public void loadingTranslatorWithEmptyLanguageCode_translatorLanguageIsPl(){
        Translator translator = new Translator();
        translator.load("");
        assertEquals(translator.getTranslatorLanguage(), "pl");
    }

    @Test
    public void loadingTranslatorWithNonExistingLanguageCode_translatorLanguageIsPl(){
        Translator translator = new Translator();
        translator.load("xx");
        assertEquals(translator.getTranslatorLanguage(), "pl");
    }
}