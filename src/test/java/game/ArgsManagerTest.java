package game;

import game.ArgsManager;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArgsManagerTest {

    @Test
    public void givenNullArguments_whenGetingOutputArg_shouldReturnEmptyString(){
        ArgsManager argsManager = new ArgsManager(null);
        assertEquals(argsManager.getOutputArg(), "");
    }

    @Test
    public void givenEmptyArrayAsArguments_whenGetingOutputArg_shouldReturnEmptyString(){
        String[] args = {};
        ArgsManager argsManager = new ArgsManager(args);
        assertEquals(argsManager.getOutputArg(), "");
    }

    @Test
    public void givenEmptyStringAsArguments_whenGetingOutputArg_shouldReturnEmptyString(){
        String[] args = {""};
        ArgsManager argsManager = new ArgsManager(args);
        assertEquals(argsManager.getOutputArg(), "");
    }

    @Test
    public void givenArrayWithDummyString_whenGetingOutputArg_shouldReturnDummyString(){
        String[] args = {"dummy"};
        ArgsManager argsManager = new ArgsManager(args);
        assertEquals(argsManager.getOutputArg(), "dummy");
    }

    @Test
    public void givenArrayWithMultipleStringsAsArguments_whenGetingOutputArg_shouldReturnFirstString(){
        String[] args = {"aa", "bb", "cc"};
        ArgsManager argsManager = new ArgsManager(args);
        assertEquals(argsManager.getOutputArg(), "aa");
    }

}