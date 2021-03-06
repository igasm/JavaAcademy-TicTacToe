package game;

import game.engine.MarkType;
import game.io.ConsoleReader;
import game.io.Writer;
import game.io.WriterBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import game.settings.BoardDimensions;
import game.settings.Settings;

public class GameLoaderTest {

    private GameLoader gameLoader;

    @BeforeTest
    public void beforeTest(){
        Writer writer = new WriterBuilder().buildByDefault();
        gameLoader = new GameLoader(
                writer,
                new ConsoleReader(writer),
                new Settings(new BoardDimensions(3, 3), 3)
        );
    }

    @DataProvider(name = "dataProviderForMarkTypeValidation")
    public Object[][] provideDataForMarkTypeValidation(){
        return new Object[][] {
                {"x", MarkType.CROSS},
                {"o", MarkType.NAUGHT},
                {"X", MarkType.CROSS},
                {"O", MarkType.NAUGHT},
                {"", null},
                {" ", null},
                {"\t", null},
                {"\n", null},
                {"\r", null},
                {System.getProperty("line.separator"), null},
                {"*", null},
                {"?", null},
                {"\\", null},
                {"-", null},
                {"_", null},
                {"t", null},
                {"T", null},
                {"aa", null},
                {"abecadło", null},
                {"1", null},
                {"1234", null}
        };
    }

    @Test(dataProvider = "dataProviderForMarkTypeValidation")
    public void givenStringForMarkType_expectedMarkType(String markString, MarkType markType){
        Assert.assertEquals(gameLoader.getMarkType(markString), markType);
    }

}