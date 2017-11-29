package game.io;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class WriterBuilderTest {

    @Test
    public void whenBuildingWriterByDefault_translatorLanguageIsPl(){
        WriterBuilder writerBuilder = new WriterBuilder();
        Writer writer = writerBuilder.buildByDefault();
        assertEquals(writer.getTranslator().getTranslatorLanguage(), "pl");
    }

    @Test
    public void whenBuildingWriterByLanguagePl_translatorLanguageIs_pl(){
        WriterBuilder writerBuilder = new WriterBuilder();
        Writer writer = writerBuilder.byLanguage("Pl").byOutputName("out").build();
        assertEquals(writer.getTranslator().getTranslatorLanguage(), "pl");
    }

    @Test
    public void whenBuildingWriterByLanguageEn_translatorLanguageIs_en(){
        WriterBuilder writerBuilder = new WriterBuilder();
        Writer writer = writerBuilder.byLanguage("En").byOutputName("out").build();
        assertEquals(writer.getTranslator().getTranslatorLanguage(), "en");
    }

    @Test
    public void whenBuildingWriterByDummyLanguage_translatorLanguageIs_pl(){
        WriterBuilder writerBuilder = new WriterBuilder();
        Writer writer = writerBuilder.byLanguage("dummy").byOutputName("out").build();
        assertEquals(writer.getTranslator().getTranslatorLanguage(), "pl");
    }
}