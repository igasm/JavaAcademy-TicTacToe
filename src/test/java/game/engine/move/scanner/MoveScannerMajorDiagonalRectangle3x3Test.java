package game.engine.move.scanner;

import game.engine.MarkType;
import game.engine.move.MoveSupervisor;
import game.engine.move.MovesRegistry;
import game.io.WriterBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import game.settings.BoardDimensions;
import game.settings.Settings;

import static org.testng.Assert.*;

public class MoveScannerMajorDiagonalRectangle3x3Test {

    MajorDiagonalScanner boardScanner;
    MoveSupervisor moveSupervisor;

    @BeforeMethod
    public void beforeMethod(){
        //given
        BoardDimensions boardDimensions = new BoardDimensions(3, 3);
        MovesRegistry movesRegistry = new MovesRegistry();
        Settings settings = new Settings(boardDimensions, 3);
        boardScanner = new MajorDiagonalScanner(movesRegistry, settings);
        moveSupervisor = new MoveSupervisor(movesRegistry, settings, new WriterBuilder().buildByDefault());
    }

    @Test
    public void givenBoard3x3WithNoughtsAtMajorDiagonal_whenScanningField8_SequenceShouldBe_ooo(){
        //given
        moveSupervisor.move(MarkType.NAUGHT, 0);
        moveSupervisor.move(MarkType.CROSS, 3);
        moveSupervisor.move(MarkType.NAUGHT, 4);
        moveSupervisor.move(MarkType.CROSS, 6);
        moveSupervisor.move(MarkType.CROSS, 7);
        moveSupervisor.move(MarkType.NAUGHT, 8);
        //when
        Sequence sequence = boardScanner.scan(8);
        //then
        assertEquals(sequence.toString(), "ooo");
    }

    @Test
    public void givenBoard3x3WithNoughtsAtMajorDiagonal_whenScanningField4_SequenceShouldBe_ooo(){
        //given
        moveSupervisor.move(MarkType.NAUGHT, 0);
        moveSupervisor.move(MarkType.CROSS, 3);
        moveSupervisor.move(MarkType.NAUGHT, 4);
        moveSupervisor.move(MarkType.CROSS, 6);
        moveSupervisor.move(MarkType.CROSS, 7);
        moveSupervisor.move(MarkType.NAUGHT, 8);
        //when
        Sequence sequence = boardScanner.scan(4);
        //then
        assertEquals(sequence.toString(), "ooo");
    }

    @Test
    public void givenBoard3x3WithNoughtsAtMajorDiagonal_whenScanningField0_SequenceShouldBe_ooo(){
        //given
        moveSupervisor.move(MarkType.NAUGHT, 0);
        moveSupervisor.move(MarkType.CROSS, 3);
        moveSupervisor.move(MarkType.NAUGHT, 4);
        moveSupervisor.move(MarkType.CROSS, 6);
        moveSupervisor.move(MarkType.CROSS, 7);
        moveSupervisor.move(MarkType.NAUGHT, 8);
        //when
        Sequence sequence = boardScanner.scan(4);
        //then
        assertEquals(sequence.toString(), "ooo");
    }

    @Test
    public void givenBoard3x3WithNoughtsAboveDiagonal_whenScanningField1_SequenceShouldBe_oo(){
        //given
        moveSupervisor.move(MarkType.NAUGHT, 1);
        moveSupervisor.move(MarkType.NAUGHT, 5);
        //when
        Sequence sequence = boardScanner.scan(1);
        //then
        assertEquals(sequence.toString(), "oo");
    }

    @Test
    public void givenBoard3x3WithNoughtsAboveDiagonal_whenScanningField5_SequenceShouldBe_oo(){
        //given
        moveSupervisor.move(MarkType.NAUGHT, 1);
        moveSupervisor.move(MarkType.NAUGHT, 5);
        //when
        Sequence sequence = boardScanner.scan(5);
        //then
        assertEquals(sequence.toString(), "oo");
    }

    @Test
    public void givenBoard3x3WithNoughtsBelowDiagonal_whenScanningField3_SequenceShouldBe_oo(){
        //given
        moveSupervisor.move(MarkType.NAUGHT, 3);
        moveSupervisor.move(MarkType.NAUGHT, 7);
        //when
        Sequence sequence = boardScanner.scan(3);
        //then
        assertEquals(sequence.toString(), "oo");
    }

    @Test
    public void givenBoard3x3WithNoughtsBelowDiagonal_whenScanningField7_SequenceShouldBe_oo(){
        //given
        moveSupervisor.move(MarkType.NAUGHT, 3);
        moveSupervisor.move(MarkType.NAUGHT, 7);
        //when
        Sequence sequence = boardScanner.scan(7);
        //then
        assertEquals(sequence.toString(), "oo");
    }

    @Test
    public void givenBoard3x3WithCrossesAtMinorDiagonal_whenScanningField4_SequenceShouldBe_x(){
        //given
        moveSupervisor.move(MarkType.CROSS, 6);
        moveSupervisor.move(MarkType.CROSS, 4);
        moveSupervisor.move(MarkType.CROSS, 2);
        //when
        Sequence sequence = boardScanner.scan(4);
        //then
        assertEquals(sequence.toString(), MarkType.CROSS.toString());
    }


    @Test
    public void givenBoard3x3WithCrossAtTopLeftCorner_SequenceIs_x(){
        //given
        moveSupervisor.move(MarkType.CROSS, 0);
        //when
        Sequence sequence = boardScanner.scan(0);
        assertEquals(sequence.toString(), MarkType.CROSS.toString());
    }

    @Test
    public void givenBoard3x3WithCrossAtTopRightCorner_SequenceIs_x(){
        //given
        moveSupervisor.move(MarkType.CROSS, 2);
        //when
        Sequence sequence = boardScanner.scan(2);
        assertEquals(sequence.toString(), MarkType.CROSS.toString());
    }

    @Test
    public void givenBoard3x3WithCrossAtBottomLeftCorner_SequenceIs_x(){
        //given
        moveSupervisor.move(MarkType.CROSS, 6);
        //when
        Sequence sequence = boardScanner.scan(6);
        assertEquals(sequence.toString(), MarkType.CROSS.toString());
    }

    @Test
    public void givenBoard3x3WithCrossAtBottomRightCorner_SequenceIs_x(){
        //given
        moveSupervisor.move(MarkType.CROSS, 8);
        //when
        Sequence sequence = boardScanner.scan(8);
        assertEquals(sequence.toString(), MarkType.CROSS.toString());
    }

}