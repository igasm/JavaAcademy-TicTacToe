package endtoend;

import game.Main;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.*;

import static org.testng.Assert.*;

public class Board3x3_Wins_Test {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream originalOutStream;
    private String nl = System.getProperty("line.separator");

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
    public void horizontalWinsCheck_playerAWinsAllMatches_playerAShouldWinGame() throws UnsupportedEncodingException {
        String settings = "y" + nl + "3" + nl + "3" + nl + "3" + nl;
        String playersRegistration = "playerA" + nl + "playerB" + nl + "x" + nl; //playerA starts
        //win by 1st column
        String match1Moves = "0" + nl + "1" + nl + "3" + nl + "5" + nl + "6" + nl + nl;
        //win by 2nd column
        String match2Moves = "0" + nl + "1" + nl + "5" + nl + "3" + nl + "2" + nl + "4" + nl + "6" + nl + "7" + nl + nl;
        //win by 3rd column
        String match3Moves = "7" + nl + "2" + nl + "1" + nl + "5" + nl + "3" + nl + "8" + nl + nl;

        String all = settings + playersRegistration + match1Moves + match2Moves + match3Moves;

        ByteArrayInputStream in = new ByteArrayInputStream(all.getBytes("UTF-8"));
        InputStream orgInStream = System.in;
        System.setIn(in);

        Main.main(new String[]{});

        String expectedGameSubmit = "Podsumowanie gry" + nl + "Wygrywa playerA (x)" + nl + "playerA: 9" + nl + "playerB: 0";

        assertTrue(outContent.toString("UTF-8").contains(expectedGameSubmit));

        System.setIn(orgInStream);
    }

    @Test
    public void verticalWinsCheck_playerAWinsAllMatches_playerAShouldWinGame() throws UnsupportedEncodingException {
        String settings = "y" + nl + "3" + nl + "3" + nl + "3" + nl;
        String playersRegistration = "playerA" + nl + "playerB" + nl + "x" + nl; //playerA starts
        //win by 1st row
        String match1Moves = "0" + nl + "3" + nl + "1" + nl + "8" + nl + "2" + nl + nl;
        //win by 2nd row
        String match2Moves = "0" + nl + "3" + nl + "6" + nl + "4" + nl + "8" + nl + "5" + nl + nl;
        //win by 3rd row
        String match3Moves = "0" + nl + "6" + nl + "3" + nl + "7" + nl + "2" + nl + "8" + nl + nl;

        String all = settings + playersRegistration + match1Moves + match2Moves + match3Moves;

        ByteArrayInputStream in = new ByteArrayInputStream(all.getBytes("UTF-8"));
        InputStream orgInStream = System.in;
        System.setIn(in);

        Main.main(new String[]{});

        String expectedGameSubmit = "Podsumowanie gry" + nl + "Wygrywa playerA (x)" + nl + "playerA: 9" + nl + "playerB: 0";

        assertTrue(outContent.toString("UTF-8").contains(expectedGameSubmit));

        System.setIn(orgInStream);
    }

}
