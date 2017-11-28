package game.engine.move;

import game.engine.MarkType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import game.settings.BoardDimensions;
import game.settings.Settings;

import static org.testng.Assert.assertEquals;

public class MoveScannerMajorDiagonalRectangle5x3BoardTest {

    MajorDiagonalScanner scanner;
    MoveSupervisor moveSupervisor;
    Settings settings;

    @BeforeMethod
    public void beforeMethod(){
        //given
        BoardDimensions boardDimensions = new BoardDimensions(5, 3);
        MovesRegistry movesRegistry = new MovesRegistry();
        settings = new Settings(boardDimensions, 3);
        scanner = new MajorDiagonalScanner(movesRegistry, settings);
        moveSupervisor = new MoveSupervisor(movesRegistry, settings);
    }

    @Test
    public void givenBoard5x3WithCrossesAtMajorDiagonal_whenScanningField6_SequenceIs_xxx(){
        //given
        moveSupervisor.move(MarkType.CROSS, 0);
        moveSupervisor.move(MarkType.CROSS, 6);
        moveSupervisor.move(MarkType.CROSS, 12);
        //when
        Sequence sequence = scanner.scan(6);
        assertEquals(sequence.toString(), "xxx");
    }

    @Test
    public void givenBoard5x3FullOfCrosses_whenScanningField0_SequenceIs_xxx(){
        //given
        for(int i=0; i<settings.getBoardElementsCount(); i++) {
            moveSupervisor.move(MarkType.CROSS, i);
        }
        //when
        Sequence sequence = scanner.scan(0);
        assertEquals(sequence.toString(), "xxx");
    }

    @Test
    public void givenBoard5x3FullOfCrosses_whenScanningField12_SequenceIs_xxx(){
        //given
        for(int i=0; i<settings.getBoardElementsCount(); i++) {
            moveSupervisor.move(MarkType.CROSS, i);
        }
        //when
        Sequence sequence = scanner.scan(12);
        assertEquals(sequence.toString(), "xxx");
    }

    @Test
    public void givenBoard5x3FullOfCrosses_whenScanningField4_SequenceIs_x(){
        //given
        for(int i=0; i<settings.getBoardElementsCount(); i++) {
            moveSupervisor.move(MarkType.CROSS, i);
        }
        //when
        Sequence sequence = scanner.scan(4);
        assertEquals(sequence.toString(), MarkType.CROSS.toString());
    }

    @Test
    public void givenBoard5x3FullOfCrosses_whenScanningField3_SequenceIs_xx(){
        //given
        for(int i=0; i<settings.getBoardElementsCount(); i++) {
            moveSupervisor.move(MarkType.CROSS, i);
        }
        //when
        Sequence sequence = scanner.scan(3);
        assertEquals(sequence.toString(), "xx");
    }


}