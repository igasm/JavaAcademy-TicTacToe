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

public class VerticalScannerTest {
    VerticalScanner verticalScanner;
    MoveSupervisor moveSupervisor;

    @BeforeMethod
    public void beforeMethod(){
        //given
        BoardDimensions boardDimensions = new BoardDimensions(3, 3);
        MovesRegistry movesRegistry = new MovesRegistry();
        Settings settings = new Settings(boardDimensions, 3);
        verticalScanner = new VerticalScanner(movesRegistry, settings);
        moveSupervisor = new MoveSupervisor(movesRegistry, settings, new WriterBuilder().buildByDefault());
    }

    public void setWinningSequenceInFirstColumn(){
        moveSupervisor.move(MarkType.CROSS, 0);
        moveSupervisor.move(MarkType.CROSS, 3);
        moveSupervisor.move(MarkType.CROSS, 6);
    }

    public void setWinningSequenceInSecondColumn(){
        moveSupervisor.move(MarkType.CROSS, 1);
        moveSupervisor.move(MarkType.CROSS, 4);
        moveSupervisor.move(MarkType.CROSS, 7);
    }

    public void setWinningSequenceInThirdColumn(){
        moveSupervisor.move(MarkType.CROSS, 2);
        moveSupervisor.move(MarkType.CROSS, 5);
        moveSupervisor.move(MarkType.CROSS, 8);
    }

    @DataProvider
    public Object[][] dataProviderForWinningSequenceInFirstColumn(){
        return new Object[][] {
            {0, "xxx"},
            {1, ""},
            {2, ""},
            {3, "xxx"},
            {4, ""},
            {5, ""},
            {6, "xxx"},
            {7, ""},
            {8, ""}
        };
    }

    @DataProvider
    public Object[][] dataProviderForWinningSequenceInSecondColumn(){
        return new Object[][] {
                {0, ""},
                {1, "xxx"},
                {2, ""},
                {3, ""},
                {4, "xxx"},
                {5, ""},
                {6, ""},
                {7, "xxx"},
                {8, ""}
        };
    }

    @DataProvider
    public Object[][] dataProviderForWinningSequenceInThirdColumn(){
        return new Object[][] {
                {0, ""},
                {1, ""},
                {2, "xxx"},
                {3, ""},
                {4, ""},
                {5, "xxx"},
                {6, ""},
                {7, ""},
                {8, "xxx"}
        };
    }

    @Test(dataProvider = "dataProviderForWinningSequenceInFirstColumn")
    public void givenWinningSeqenceInFirstColumn_whenScanningField_fieldNumber_thenSequence_ShouldBe_expectedSequence(Integer fieldNumber, String expectedSequence){
        //given
        setWinningSequenceInFirstColumn();
        //when
        Sequence sequence = verticalScanner.scan(fieldNumber);
        //then
        assertEquals(sequence.toString(), expectedSequence);
    }

    @Test(dataProvider = "dataProviderForWinningSequenceInSecondColumn")
    public void givenWinningSeqenceInSecondColumn_whenScanningField_fieldNumber_thenSequence_ShouldBe_expectedSequence(Integer fieldNumber, String expectedSequence){
        //given
        setWinningSequenceInSecondColumn();
        //when
        Sequence sequence = verticalScanner.scan(fieldNumber);
        //then
        assertEquals(sequence.toString(), expectedSequence);
    }

    @Test(dataProvider = "dataProviderForWinningSequenceInThirdColumn")
    public void givenWinningSeqenceInThirdColumn_whenScanningField_fieldNumber_thenSequence_ShouldBe_expectedSequence(Integer fieldNumber, String expectedSequence){
        //given
        setWinningSequenceInThirdColumn();
        //when
        Sequence sequence = verticalScanner.scan(fieldNumber);
        //then
        assertEquals(sequence.toString(), expectedSequence);
    }
}