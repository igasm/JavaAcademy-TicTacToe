package game.io;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.testng.Assert.*;

public class WriterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream originalOutStream;
    private String newline = System.getProperty("line.separator");

    @BeforeTest
    public void setUpStream() throws UnsupportedEncodingException {
        originalOutStream = System.out;
        System.setOut(new PrintStream(outContent, true, "UTF-8"));
    }

    @AfterTest
    public void cleanUpStream(){
        System.setOut(originalOutStream);
    }

    @AfterMethod
    public void streamReseting(){
        outContent.reset();
    }

    @Test
    public void acceptMethodTest() throws UnsupportedEncodingException {
        Writer writer = new WriterBuilder().buildByDefault();
        writer.println("this is dummy message");
        assertEquals(outContent.toString("UTF-8"), "this is dummy message" + newline);
    }

    @Test
    public void addNewLineMethodTest() throws UnsupportedEncodingException {
        Writer writer = new WriterBuilder().buildByDefault();
        writer.addNewLine();
        assertEquals(outContent.toString("UTF-8"), newline);
    }

}