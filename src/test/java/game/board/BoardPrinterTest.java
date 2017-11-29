package game.board;

import game.engine.MarkType;
import game.engine.move.MoveSupervisor;
import game.engine.move.MovesRegistry;
import game.io.WriterBuilder;
import game.settings.BoardDimensions;
import game.settings.Settings;
import org.testng.annotations.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.Assert.*;

public class BoardPrinterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private String newline = System.getProperty("line.separator");
    private PrintStream originalOutStream;

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
    public void emptyBord3x3_printBoardTest(){
        BoardDimensions boardDimensions = new BoardDimensions(3, 3);
        Board board = new BoardBuilder(boardDimensions).viaBoard();
        BoardPrinter boardPrinter = new BoardPrinter(board, boardDimensions, new WriterBuilder().buildByDefault());
        boardPrinter.printBord();
        assertEquals(outContent.toString(), "[  0][  1][  2]" + newline + "[  3][  4][  5]" + newline + "[  6][  7][  8]" + newline);
        outContent.reset();
    }

    @Test
    public void emptyBord3x5_printBoardTest(){
        BoardDimensions boardDimensions = new BoardDimensions(3, 5);
        Board board = new BoardBuilder(boardDimensions).viaBoard();
        BoardPrinter boardPrinter = new BoardPrinter(board, boardDimensions, new WriterBuilder().buildByDefault());
        boardPrinter.printBord();
        assertEquals(outContent.toString(), "[  0][  1][  2]" + newline
                + "[  3][  4][  5]" + newline
                + "[  6][  7][  8]" + newline
                + "[  9][ 10][ 11]" + newline
                + "[ 12][ 13][ 14]" + newline
        );
        outContent.reset();
    }

    @Test
    public void emptyBord5x5_printBoardTest(){
        BoardDimensions boardDimensions = new BoardDimensions(5, 5);
        Board board = new BoardBuilder(boardDimensions).viaBoard();
        BoardPrinter boardPrinter = new BoardPrinter(board, boardDimensions, new WriterBuilder().buildByDefault());
        boardPrinter.printBord();
        assertEquals(outContent.toString(), "[  0][  1][  2][  3][  4]" + newline
                + "[  5][  6][  7][  8][  9]" + newline
                + "[ 10][ 11][ 12][ 13][ 14]" + newline
                + "[ 15][ 16][ 17][ 18][ 19]" + newline
                + "[ 20][ 21][ 22][ 23][ 24]" + newline
        );
        outContent.reset();
    }

    @Test
    public void emptyBord5x3_printBoardTest(){
        BoardDimensions boardDimensions = new BoardDimensions(5, 3);
        Board board = new BoardBuilder(boardDimensions).viaBoard();
        BoardPrinter boardPrinter = new BoardPrinter(board, boardDimensions, new WriterBuilder().buildByDefault());
        boardPrinter.printBord();
        assertEquals(outContent.toString(), "[  0][  1][  2][  3][  4]" + newline
                + "[  5][  6][  7][  8][  9]" + newline
                + "[ 10][ 11][ 12][ 13][ 14]" + newline
        );
        outContent.reset();
    }

    @Test
    public void bord3x3_withCrossesAtMajorDiagonal_printBoardTest(){
        BoardDimensions boardDimensions = new BoardDimensions(3, 3);
        Board board = new BoardBuilder(boardDimensions).viaBoard();
        BoardPrinter boardPrinter = new BoardPrinter(board, boardDimensions, new WriterBuilder().buildByDefault());
        MovesRegistry movesRegistry = new MovesRegistry();
        MoveSupervisor moveSupervisor = new MoveSupervisor(movesRegistry, new Settings(boardDimensions, 3), new WriterBuilder().buildByDefault());
        moveSupervisor.move(MarkType.CROSS, 0);
        moveSupervisor.move(MarkType.CROSS, 4);
        moveSupervisor.move(MarkType.CROSS, 8);
        boardPrinter.printBoardWithMoves(movesRegistry);
        assertEquals(outContent.toString(), "[  x][  1][  2]" + newline + "[  3][  x][  5]" + newline + "[  6][  7][  x]" + newline);
        outContent.reset();
    }
}