package game.engine.move.scanner;

import game.engine.MarkType;
import game.engine.move.MoveSupervisor;
import game.engine.move.MovesRegistry;
import game.io.WriterBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import game.settings.BoardDimensions;
import game.settings.Settings;

import static org.testng.Assert.assertEquals;

public class MoveScannerMinorDiagonalRectangleBoardTest {

    private MinorDiagonalScanner boardScanner;
    private MoveSupervisor moveSupervisor;
    private Settings settings;

    @BeforeMethod
    public void beforeMethod(){
        //given
        BoardDimensions boardDimensions = new BoardDimensions(5, 3);
        MovesRegistry movesRegistry = new MovesRegistry();
        settings = new Settings(boardDimensions, 3);
        boardScanner = new MinorDiagonalScanner(movesRegistry, settings);
        moveSupervisor = new MoveSupervisor(movesRegistry, settings, new WriterBuilder().buildByDefault());
    }

    @Test
    public void givenBoard5x3WithCrossesAtMinorDiagonal_whenScanningField6_SequenceIs_xxx(){
        //given
        moveSupervisor.move(MarkType.CROSS, 10);
        moveSupervisor.move(MarkType.CROSS, 6);
        moveSupervisor.move(MarkType.CROSS, 2);
        //when
        Sequence sequence = boardScanner.scan(6);
        assertEquals(sequence.toString(), "xxx");
    }

    @Test
    public void givenBoard5x3FullOfCrosses_whenScanningField0_SequenceIs_x(){
        //given
        for(int i=0; i<settings.getBoardElementsCount(); i++) {
            moveSupervisor.move(MarkType.CROSS, i);
        }
        //when
        Sequence sequence = boardScanner.scan(0);
        assertEquals(sequence.toString(), MarkType.CROSS.toString());
    }

    @Test
    public void givenBoard5x3FullOfCrosses_whenScanningField12_SequenceIs_xxx(){
        //given
        for(int i=0; i<settings.getBoardElementsCount(); i++) {
            moveSupervisor.move(MarkType.CROSS, i);
        }
        //when
        Sequence sequence = boardScanner.scan(12);
        assertEquals(sequence.toString(), "xxx");
    }

    @Test
    public void givenBoard5x3FullOfCrosses_whenScanningField4_SequenceIs_xxx(){
        //given
        for(int i=0; i<settings.getBoardElementsCount(); i++) {
            moveSupervisor.move(MarkType.CROSS, i);
        }
        //when
        Sequence sequence = boardScanner.scan(4);
        assertEquals(sequence.toString(), "xxx");
    }

    @Test
    public void givenBoard5x3FullOfCrosses_whenScanningField3_SequenceIs_xxx(){
        //given
        for(int i=0; i<settings.getBoardElementsCount(); i++) {
            moveSupervisor.move(MarkType.CROSS, i);
        }
        //when
        Sequence sequence = boardScanner.scan(3);
        assertEquals(sequence.toString(), "xxx");
    }

    @Test
    public void givenBoard5x3FullOfCrosses_whenScanningField1_SequenceIs_xx(){
        //given
        for(int i=0; i<settings.getBoardElementsCount(); i++) {
            moveSupervisor.move(MarkType.CROSS, i);
        }
        //when
        Sequence sequence = boardScanner.scan(1);
        assertEquals(sequence.toString(), "xx");
    }


}