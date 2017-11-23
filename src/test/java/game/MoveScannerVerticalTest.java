package game;

import board.Board;
import board.BoardBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import settings.BoardDimensions;
import settings.Settings;

import java.util.function.Consumer;

import static org.testng.Assert.*;

public class MoveScannerVerticalTest {

    MoveScanner boardScanner;
    Board board;
    MoveSupervisor moveSupervisor;

    @BeforeMethod
    public void beforeMethod(){
        BoardDimensions boardDimensions = new BoardDimensions(3, 3);
        BoardBuilder boardBuilder = new BoardBuilder(boardDimensions);
        board = new Board(boardBuilder.viaList(), boardDimensions);
        MovesRegistry movesRegistry = new MovesRegistry();
        Settings settings = new Settings(boardDimensions, 3);
        boardScanner = new MoveScanner(movesRegistry, settings);
        moveSupervisor = new MoveSupervisor(movesRegistry, settings);
    }

    @Test
    public void givenBoard3x3With_xxx_AtFirstColumn_whenScanningField3_SequenceShouldBe_xxx(){
        //given
        moveSupervisor.move(MarkType.CROSS, 0);
        moveSupervisor.move(MarkType.CROSS, 3);
        moveSupervisor.move(MarkType.CROSS, 6);

        //when
        Sequence sequence = boardScanner.scanVertically(3);
        //then
        assertEquals(sequence.toString(), "xxx");
    }

}