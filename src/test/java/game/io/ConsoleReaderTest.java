package game.io;

import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.testng.Assert.*;

public class ConsoleReaderTest {

    private String newline = System.getProperty("line.separator");

    @Test
    public void whenUsingGetString_firstLineFromInputIsReturned(){
        String input = "first line" + newline + "second line";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        InputStream orgInStream = System.in;
        System.setIn(in);

        Writer writer = new WriterBuilder().byDefault();
        ConsoleReader consoleReader = new ConsoleReader(writer);
        assertEquals(consoleReader.getString(), "first line");

        System.setIn(orgInStream);
    }


    @Test
    public void usingGetInt_whenInputContainOnlyIntegerValue_integerIsReturned(){
        String input = "1";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        InputStream orgInStream = System.in;
        System.setIn(in);

        Writer writer = new WriterBuilder().byDefault();
        ConsoleReader consoleReader = new ConsoleReader(writer);
        assertEquals(consoleReader.getInt(), 1);

        System.setIn(orgInStream);
    }

    @Test
    public void usingGetInt_whenInputContainsLetters_messegeIsPrinted(){
        //redirecting I/O
        ByteArrayInputStream in = new ByteArrayInputStream(("aaaaa"+newline+"1").getBytes());
        InputStream orgInStream = System.in;
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOutStream = System.out;
        System.setOut(new PrintStream(outContent));

        //test
        Writer writer = new WriterBuilder().byDefault();
        ConsoleReader consoleReader = new ConsoleReader(writer);
        consoleReader.getInt();
        assertEquals(outContent.toString().trim(), "Podaj liczbę całkowitą");

        //cleaning Up
        outContent.reset();
        System.setOut(originalOutStream);

        System.setIn(orgInStream);
    }

}