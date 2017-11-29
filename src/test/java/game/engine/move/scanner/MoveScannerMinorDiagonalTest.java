package game.engine.move.scanner;

import game.board.BoardBuilder;
import game.engine.MarkType;
import game.engine.move.MoveSupervisor;
import game.engine.move.MovesRegistry;
import game.io.WriterBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import game.settings.BoardDimensions;
import game.settings.Settings;

import static org.testng.Assert.*;

public class MoveScannerMinorDiagonalTest {

    MinorDiagonalScanner boardScanner;
    MoveSupervisor moveSupervisor;

    @BeforeMethod
    public void beforeMethod(){
        //given
        BoardDimensions boardDimensions = new BoardDimensions(3, 3);
        BoardBuilder boardBuilder = new BoardBuilder(boardDimensions);
        MovesRegistry movesRegistry = new MovesRegistry();
        Settings settings = new Settings(boardDimensions, 3);
        boardScanner = new MinorDiagonalScanner(movesRegistry, settings);
        moveSupervisor = new MoveSupervisor(movesRegistry, settings, new WriterBuilder().byDefault());
    }

    @Test
    public void givenBoard3x3WithCrossesAtMinorDiagonal_whenScanningField4_seqenceIs_xxx(){
        //given
        moveSupervisor.move(MarkType.CROSS, 6);
        moveSupervisor.move(MarkType.CROSS, 4);
        moveSupervisor.move(MarkType.CROSS, 2);
        //when
        Sequence sequence = boardScanner.scan(4);
        //then
        assertEquals(sequence.toString(), "xxx");
    }

    @Test
    public void givenBoard3x3WithCrossesBelowMinorDiagonal_whenScanningField5_seqenceIs_xx(){
        //given
        moveSupervisor.move(MarkType.CROSS, 7);
        moveSupervisor.move(MarkType.CROSS, 5);
        //when
        Sequence sequence = boardScanner.scan(5);
        //then
        assertEquals(sequence.toString(), "xx");
    }

    @Test
    public void givenBoard3x3WithCrossesAboveMinorDiagonal_whenScanningField1_seqenceIs_xx(){
        //given
        moveSupervisor.move(MarkType.CROSS, 3);
        moveSupervisor.move(MarkType.CROSS, 1);
        //when
        Sequence sequence = boardScanner.scan(1);
        //then
        assertEquals(sequence.toString(), "xx");
    }

    @Test
    public void givenBoard3x3WithCrossAtTopLeftCorner_whenScanningIt_seqenceIs_x(){
        //given
        moveSupervisor.move(MarkType.CROSS, 0);
        //when
        Sequence sequence = boardScanner.scan(0);
        //then
        assertEquals(sequence.toString(), MarkType.CROSS.toString());
    }

    @Test
    public void givenBoard3x3WithCrossAtTopRightCorner_whenScanningIt_seqenceIs_x(){
        //given
        moveSupervisor.move(MarkType.CROSS, 2);
        //when
        Sequence sequence = boardScanner.scan(2);
        //then
        assertEquals(sequence.toString(), MarkType.CROSS.toString());
    }

    @Test
    public void givenBoard3x3WithCrossAtBottomRightCorner_whenScanningIt_seqenceIs_x(){
        //given
        moveSupervisor.move(MarkType.CROSS, 8);
        //when
        Sequence sequence = boardScanner.scan(8);
        //then
        assertEquals(sequence.toString(), MarkType.CROSS.toString());
    }

    @Test
    public void givenBoard3x3WithCrossAtBottomLeftCorner_whenScanningIt_seqenceIs_x(){
        //given
        moveSupervisor.move(MarkType.CROSS, 6);
        //when
        Sequence sequence = boardScanner.scan(6);
        //then
        assertEquals(sequence.toString(), MarkType.CROSS.toString());
    }
}
