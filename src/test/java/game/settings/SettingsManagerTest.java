package game.settings;

import game.io.ConsoleReader;
import game.io.Writer;
import game.io.WriterBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.*;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public class SettingsManagerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream originalOutStream;
    private String newline = System.getProperty("line.separator");

    @BeforeMethod
    public void setUpStream() throws UnsupportedEncodingException {
        originalOutStream = System.out;
        System.setOut(new PrintStream(outContent, true, "UTF-8"));
    }

    @AfterMethod
    public void cleanUpStream(){
        System.setOut(originalOutStream);
    }

    @Test
    public void given_minBoardDimension3_maxBoardDimension101_whenTypingBoardDimension5_ThenItIsOk() throws UnsupportedEncodingException {
        ByteArrayInputStream in = new ByteArrayInputStream("5".getBytes("UTF-8"));
        InputStream orgInStream = System.in;
        System.setIn(in);

        Writer writer = new WriterBuilder().buildByDefault();
        SettingsManager settingsManager = new SettingsManager(writer, new ConsoleReader(writer));
        Integer validatedDimension = settingsManager.askForBoardDimension("ask_for_border_width", new SettingsValidator(3, 101, 3));

        assertEquals(validatedDimension.intValue(), 5);

        outContent.reset();
        System.setIn(orgInStream);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void given_minBoardDimension3_maxBoardDimension101_whenTypingBoardDimension2_exceptionExpected() throws UnsupportedEncodingException {
        ByteArrayInputStream in = new ByteArrayInputStream("2".getBytes("UTF-8"));
        InputStream orgInStream = System.in;
        System.setIn(in);

        Writer writer = new WriterBuilder().buildByDefault();
        SettingsManager settingsManager = new SettingsManager(writer, new ConsoleReader(writer));
        settingsManager.askForBoardDimension("ask_for_border_width", new SettingsValidator(3, 101, 3));

        outContent.reset();
        System.setIn(orgInStream);
    }

    @Test
    public void given_minBoardDimension3_maxBoardDimension101_whenTypingBoardDimensionFirstly2Then5_ThenItIsOk() throws UnsupportedEncodingException {
        String dimensions = "2" + newline + "5";
        ByteArrayInputStream in = new ByteArrayInputStream(dimensions.getBytes("UTF-8"));
        InputStream orgInStream = System.in;
        System.setIn(in);

        Writer writer = new WriterBuilder().buildByDefault();
        SettingsManager settingsManager = new SettingsManager(writer, new ConsoleReader(writer));
        Integer validatedDimension = settingsManager.askForBoardDimension("ask_for_winning_condition", new SettingsValidator(3, 101, 3));

        assertEquals(validatedDimension.intValue(), 5);

        outContent.reset();
        System.setIn(orgInStream);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void given_minBoardDimension3_maxBoardDimension101_boardWidth3_and_boardHeight5_whenTypingWinningConditionLargerFromOneOfThis_exceptionExpected() throws UnsupportedEncodingException {
        ByteArrayInputStream in = new ByteArrayInputStream("4".getBytes("UTF-8"));
        InputStream orgInStream = System.in;
        System.setIn(in);

        Writer writer = new WriterBuilder().buildByDefault();
        SettingsManager settingsManager = new SettingsManager(writer, new ConsoleReader(writer));
        settingsManager.askForWinningCondition(new SettingsValidator(3, 101, 3), new BoardDimensions(3, 5));

        outContent.reset();
        System.setIn(orgInStream);
    }

    @Test
    public void given_minBoardDimension3_maxBoardDimension101_boardWidth3_and_boardHeight5_whenTypingWinningConditionAsMinOfBoardDimensions_thenIsOK() throws UnsupportedEncodingException {
        ByteArrayInputStream in = new ByteArrayInputStream("3".getBytes("UTF-8"));
        InputStream orgInStream = System.in;
        System.setIn(in);

        Writer writer = new WriterBuilder().buildByDefault();
        SettingsManager settingsManager = new SettingsManager(writer, new ConsoleReader(writer));
        Integer winningCondition = settingsManager.askForWinningCondition(new SettingsValidator(3, 101, 3), new BoardDimensions(3, 5));
        assertEquals(winningCondition.intValue(), 3);

        outContent.reset();
        System.setIn(orgInStream);
    }

}