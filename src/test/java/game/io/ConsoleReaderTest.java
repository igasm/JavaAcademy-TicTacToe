package game.io;

import org.testng.annotations.Test;

import java.io.*;

import static org.testng.Assert.*;

public class ConsoleReaderTest {

    private String newline = System.getProperty("line.separator");

    @Test
    public void whenUsingGetString_firstLineFromInputIsReturned() throws UnsupportedEncodingException {
        String input = "first line" + newline + "second line";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF-8"));
        InputStream orgInStream = System.in;
        System.setIn(in);

        Writer writer = new WriterBuilder().buildByDefault();
        ConsoleReader consoleReader = new ConsoleReader(writer);
        assertEquals(consoleReader.getString(), "first line");

        System.setIn(orgInStream);
    }


    @Test
    public void usingGetInt_whenInputContainOnlyIntegerValue_integerIsReturned() throws UnsupportedEncodingException {
        String input = "1";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF-8"));
        InputStream orgInStream = System.in;
        System.setIn(in);

        Writer writer = new WriterBuilder().buildByDefault();
        ConsoleReader consoleReader = new ConsoleReader(writer);
        assertEquals(consoleReader.getInt(), 1);

        System.setIn(orgInStream);
    }

    @Test
    public void usingGetInt_whenInputContainsLetters_messageIsPrinted() throws UnsupportedEncodingException {
        //redirecting I/O
        ByteArrayInputStream in = new ByteArrayInputStream(("aaaaa"+newline+"1").getBytes("UTF-8"));
        InputStream orgInStream = System.in;
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOutStream = System.out;
        System.setOut(new PrintStream(outContent, true, "UTF-8"));

        //test
        Writer writer = new WriterBuilder().buildByDefault();
        ConsoleReader consoleReader = new ConsoleReader(writer);
        consoleReader.getInt();
        assertEquals(outContent.toString("UTF-8").trim(), "Podaj liczbę całkowitą");

        //cleaning Up
        outContent.reset();
        System.setOut(originalOutStream);

        System.setIn(orgInStream);
    }

}