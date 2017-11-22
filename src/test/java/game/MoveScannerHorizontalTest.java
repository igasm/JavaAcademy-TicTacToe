package game;

import board.Board;
import board.BoardBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import settings.BoardDimensions;
import settings.Settings;

import java.util.function.Consumer;

import static org.testng.Assert.*;

public class MoveScannerHorizontalTest {

    MoveScanner boardScanner;
    Board board;
    MoveSupervisor moveSupervisor;

    @BeforeMethod
    public void beforeMethod(){
        //given
        Consumer<Exception> exceptionHandler = e -> System.out.println("Wystąpił błąd " + e.getMessage());
        Consumer<String> consoleWriter = System.out::println;
        BoardDimensions boardDimensions = new BoardDimensions(3, 3);
        BoardBuilder boardBuilder = new BoardBuilder(boardDimensions);
        board = new Board(boardBuilder.viaList(), boardDimensions);
        MovesRegistry movesRegistry = new MovesRegistry();
        Settings settings = new Settings(exceptionHandler, boardDimensions, 3);
        boardScanner = new MoveScanner(movesRegistry, settings);
        moveSupervisor = new MoveSupervisor(movesRegistry, settings);
    }

    @Test
    public void givenBoard3x3With_oxo_AtFirstRow_whenScanningField2_SequenceShouldBe_oxo(){
        //given
        moveSupervisor.move(MarkType.NAUGHT, 0);
        moveSupervisor.move(MarkType.CROSS, 1);
        moveSupervisor.move(MarkType.NAUGHT, 2);
        moveSupervisor.move(MarkType.CROSS, 3);
        moveSupervisor.move(MarkType.CROSS, 4);
        moveSupervisor.move(MarkType.CROSS, 7);
        moveSupervisor.move(MarkType.CROSS, 8);

        //when
        Sequence sequence = boardScanner.scanHorizontally(2);
        //then
        assertEquals(sequence.toString(), "oxo");
    }

}