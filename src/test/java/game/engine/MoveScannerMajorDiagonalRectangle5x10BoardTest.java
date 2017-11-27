package game.engine;

import game.board.Board;
import game.board.BoardBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import game.settings.BoardDimensions;
import game.settings.Settings;

import static org.testng.Assert.assertEquals;

public class MoveScannerMajorDiagonalRectangle5x10BoardTest {

    MoveScanner boardScanner;
    Board board;
    MoveSupervisor moveSupervisor;
    Settings settings;

    @BeforeMethod
    public void beforeMethod(){
        //given
        BoardDimensions boardDimensions = new BoardDimensions(5, 10);
        BoardBuilder boardBuilder = new BoardBuilder(boardDimensions);
        board = new Board(boardBuilder.viaList(), boardDimensions);
        MovesRegistry movesRegistry = new MovesRegistry();
        settings = new Settings(boardDimensions, 5);
        boardScanner = new MoveScanner(movesRegistry, settings);
        moveSupervisor = new MoveSupervisor(movesRegistry, settings);
    }

    @Test
    public void givenCrossesAtMajorDiagonal_whenScanningField29_SequenceIs_xxxxx(){
        //given
        moveSupervisor.move(MarkType.CROSS, 29);
        moveSupervisor.move(MarkType.CROSS, 23);
        moveSupervisor.move(MarkType.CROSS, 17);
        moveSupervisor.move(MarkType.CROSS, 11);
        moveSupervisor.move(MarkType.CROSS, 5);
        //when
        Sequence sequence = boardScanner.scanMajorDiagonal(29);
        assertEquals(sequence.toString(), "xxxxx");
    }

    @Test
    public void givenCrossesAtMajorDiagonal_whenScanningField5_SequenceIs_xxxxx(){
        //given
        moveSupervisor.move(MarkType.CROSS, 29);
        moveSupervisor.move(MarkType.CROSS, 23);
        moveSupervisor.move(MarkType.CROSS, 17);
        moveSupervisor.move(MarkType.CROSS, 11);
        moveSupervisor.move(MarkType.CROSS, 5);
        //when
        Sequence sequence = boardScanner.scanMajorDiagonal(5);
        assertEquals(sequence.toString(), "xxxxx");
    }

    @Test
    public void givenCrossesAtMajorDiagonal_whenScanningField17_SequenceIs_xxxxx(){
        //given
        moveSupervisor.move(MarkType.CROSS, 29);
        moveSupervisor.move(MarkType.CROSS, 23);
        moveSupervisor.move(MarkType.CROSS, 17);
        moveSupervisor.move(MarkType.CROSS, 11);
        moveSupervisor.move(MarkType.CROSS, 5);
        //when
        Sequence sequence = boardScanner.scanMajorDiagonal(29);
        assertEquals(sequence.toString(), "xxxxx");
    }

    @Test
    public void givenCrossesAtField23And29_whenScanningField23_SequenceIs_xx(){
        //given
        moveSupervisor.move(MarkType.CROSS, 29);
        moveSupervisor.move(MarkType.CROSS, 23);
        //when
        Sequence sequence = boardScanner.scanMajorDiagonal(29);
        assertEquals(sequence.toString(), "xx");
    }

    @Test
    public void givenCrossesAndNoughtsAtMajorDiagonalFrom5To29_whenScanningField23_SequenceIsWithCrossesAndNoughts(){
        //given
        moveSupervisor.move(MarkType.NAUGHT, 29);
        moveSupervisor.move(MarkType.CROSS, 23);
        moveSupervisor.move(MarkType.NAUGHT, 17);
        moveSupervisor.move(MarkType.CROSS, 11);
        moveSupervisor.move(MarkType.NAUGHT, 5);
        //when
        Sequence sequence = boardScanner.scanMajorDiagonal(23);
        assertEquals(sequence.toString(), "oxoxo");
    }
}