package game.settings;

import game.io.ConsoleReader;
import game.io.Writer;
import game.io.WriterBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public class SettingsManagerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream originalOutStream;
    private String newline = System.getProperty("line.separator");

    @BeforeMethod
    public void setUpStream(){
        originalOutStream = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @AfterMethod
    public void cleanUpStream(){
        System.setOut(originalOutStream);
    }

    @Test
    public void given_minBoardDimension3_maxBoardDimension101_whenTypingBoardDimension5_ThenItIsOk(){
        ByteArrayInputStream in = new ByteArrayInputStream("5".getBytes());
        InputStream orgInStream = System.in;
        System.setIn(in);

        Writer writer = new WriterBuilder().byDefault();
        SettingsManager settingsManager = new SettingsManager(writer, new ConsoleReader(writer));
        Integer validatedDimension = settingsManager.askForBoardDimension("some message", new SettingsValidator(3, 101, 3));

        assertEquals(validatedDimension.intValue(), 5);

        outContent.reset();
        System.setIn(orgInStream);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void given_minBoardDimension3_maxBoardDimension101_whenTypingBoardDimension2_exceptionExpected(){
        ByteArrayInputStream in = new ByteArrayInputStream("2".getBytes());
        InputStream orgInStream = System.in;
        System.setIn(in);

        Writer writer = new WriterBuilder().byDefault();
        SettingsManager settingsManager = new SettingsManager(writer, new ConsoleReader(writer));
        settingsManager.askForBoardDimension("some message", new SettingsValidator(3, 101, 3));

        outContent.reset();
        System.setIn(orgInStream);
    }

    @Test
    public void given_minBoardDimension3_maxBoardDimension101_whenTypingBoardDimensionFirstly2Then5_ThenItIsOk(){
        String dimensions = "2" + newline + "5";
        ByteArrayInputStream in = new ByteArrayInputStream(dimensions.getBytes());
        InputStream orgInStream = System.in;
        System.setIn(in);

        Writer writer = new WriterBuilder().byDefault();
        SettingsManager settingsManager = new SettingsManager(writer, new ConsoleReader(writer));
        Integer validatedDimension = settingsManager.askForBoardDimension("some message", new SettingsValidator(3, 101, 3));

        assertEquals(validatedDimension.intValue(), 5);

        outContent.reset();
        System.setIn(orgInStream);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void given_minBoardDimension3_maxBoardDimension101_boardWidth3_and_boardHeight5_whenTypingWinningConditionLargerFromOneOfThis_exceptionExpected(){
        ByteArrayInputStream in = new ByteArrayInputStream("4".getBytes());
        InputStream orgInStream = System.in;
        System.setIn(in);

        Writer writer = new WriterBuilder().byDefault();
        SettingsManager settingsManager = new SettingsManager(writer, new ConsoleReader(writer));
        settingsManager.askForWinningCondition(new SettingsValidator(3, 101, 3), new BoardDimensions(3, 5));

        outContent.reset();
        System.setIn(orgInStream);
    }

    @Test
    public void given_minBoardDimension3_maxBoardDimension101_boardWidth3_and_boardHeight5_whenTypingWinningConditionAsMinOfBoardDimensions_thenIsOK(){
        ByteArrayInputStream in = new ByteArrayInputStream("3".getBytes());
        InputStream orgInStream = System.in;
        System.setIn(in);

        Writer writer = new WriterBuilder().byDefault();
        SettingsManager settingsManager = new SettingsManager(writer, new ConsoleReader(writer));
        Integer winningCondition = settingsManager.askForWinningCondition(new SettingsValidator(3, 101, 3), new BoardDimensions(3, 5));
        assertEquals(winningCondition.intValue(), 3);

        outContent.reset();
        System.setIn(orgInStream);
    }

}