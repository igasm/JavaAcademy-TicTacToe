package game.engine.move.scanner;

import game.engine.MarkType;
import game.engine.move.MoveSupervisor;
import game.engine.move.MovesRegistry;
import game.io.WriterBuilder;
import game.settings.BoardDimensions;
import game.settings.Settings;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class HorizontalScannerTest {
    private MoveSupervisor moveSupervisor;
    private HorizontalScanner horizontalScanner;

    @BeforeMethod
    public void beforeMethod(){
        //given
        BoardDimensions boardDimensions = new BoardDimensions(3, 3);
        MovesRegistry movesRegistry = new MovesRegistry();
        Settings settings = new Settings(boardDimensions, 3);
        moveSupervisor = new MoveSupervisor(movesRegistry, settings, new WriterBuilder().buildByDefault());
        horizontalScanner = new HorizontalScanner(movesRegistry, settings);
    }

    private void setWinningSequenceInFirstRow(){
        moveSupervisor.move(MarkType.CROSS, 0);
        moveSupervisor.move(MarkType.CROSS, 1);
        moveSupervisor.move(MarkType.CROSS, 2);
    }

    private void setWinningSequenceInSecondRow(){
        moveSupervisor.move(MarkType.CROSS, 3);
        moveSupervisor.move(MarkType.CROSS, 4);
        moveSupervisor.move(MarkType.CROSS, 5);
    }

    private void setWinningSequenceInThirdRow(){
        moveSupervisor.move(MarkType.CROSS, 6);
        moveSupervisor.move(MarkType.CROSS, 7);
        moveSupervisor.move(MarkType.CROSS, 8);
    }

    @DataProvider
    public Object[][] dataProviderForWinningSequenceInFirstRow(){
        return new Object[][] {
                {0, "xxx"},
                {1, "xxx"},
                {2, "xxx"},
                {3, ""},
                {4, ""},
                {5, ""},
                {6, ""},
                {7, ""},
                {8, ""}
        };
    }

    @DataProvider
    public Object[][] dataProviderForWinningSequenceInSecondRow(){
        return new Object[][] {
                {0, ""},
                {1, ""},
                {2, ""},
                {3, "xxx"},
                {4, "xxx"},
                {5, "xxx"},
                {6, ""},
                {7, ""},
                {8, ""}
        };
    }

    @DataProvider
    public Object[][] dataProviderForWinningSequenceInThirdRow(){
        return new Object[][] {
                {0, ""},
                {1, ""},
                {2, ""},
                {3, ""},
                {4, ""},
                {5, ""},
                {6, "xxx"},
                {7, "xxx"},
                {8, "xxx"}
        };
    }

    @Test(dataProvider = "dataProviderForWinningSequenceInFirstRow")
    public void givenWinningSeqenceInFirstRow_whenScanningField_fieldNumber_thenSequence_ShouldBe_expectedSequence(Integer fieldNumber, String expectedSequence){
        //given
        setWinningSequenceInFirstRow();
        //when
        Sequence sequence = horizontalScanner.scan(fieldNumber);
        //then
        assertEquals(sequence.toString(), expectedSequence);
    }

    @Test(dataProvider = "dataProviderForWinningSequenceInSecondRow")
    public void givenWinningSeqenceInSecondRow_whenScanningField_fieldNumber_thenSequence_ShouldBe_expectedSequence(Integer fieldNumber, String expectedSequence){
        //given
        setWinningSequenceInSecondRow();
        //when
        Sequence sequence = horizontalScanner.scan(fieldNumber);
        //then
        assertEquals(sequence.toString(), expectedSequence);
    }

    @Test(dataProvider = "dataProviderForWinningSequenceInThirdRow")
    public void givenWinningSeqenceInThirdRow_whenScanningField_fieldNumber_thenSequence_ShouldBe_expectedSequence(Integer fieldNumber, String expectedSequence){
        //given
        setWinningSequenceInThirdRow();
        //when
        Sequence sequence = horizontalScanner.scan(fieldNumber);
        //then
        assertEquals(sequence.toString(), expectedSequence);
    }
}