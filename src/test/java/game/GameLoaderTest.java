package game;

import io.ConsoleReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import settings.BoardDimensions;
import settings.Settings;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class GameLoaderTest {

    @DataProvider(name = "dataProviderForMarkTypeValidation")
    public Object[][] provideDataForMarkTypeValidation(){
        Map<String, MarkType> map = new HashMap<>();
        map.put("x", MarkType.CROSS);
        map.put("o", MarkType.NAUGHT);
        map.put("X", MarkType.CROSS);
        map.put("O", MarkType.NAUGHT);
        map.put("", null);
        map.put(" ", null);
        map.put("\t", null);
        map.put("\n", null);
        map.put("\r", null);
        map.put(System.getProperty("line.separator"), null);
        map.put("*", null);
        map.put("?", null);
        map.put("\\", null);
        map.put("-", null);
        map.put("_", null);
        map.put("t", null);
        map.put("T", null);
        map.put("aa", null);
        map.put("abecadło", null);
        map.put("1", null);
        map.put("1234", null);
        return new Object[][] {{map}};
    }

    @Test(dataProvider = "dataProviderForMarkTypeValidation")
    public void givenStringForMarkType_expectedMarkType(Map<String, MarkType> map){
        GameLoader gameLoader = new GameLoader(System.out::println, new ConsoleReader(), new Settings(new BoardDimensions(3, 3), 3));
        for(Map.Entry<String, MarkType> entry : map.entrySet()){
            Assert.assertEquals(gameLoader.getMarkType(entry.getKey()), entry.getValue());
        }
    }

}