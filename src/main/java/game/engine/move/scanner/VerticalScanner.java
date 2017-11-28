package game.engine.move.scanner;

import game.engine.move.MovesRegistry;
import game.settings.Settings;

import java.util.LinkedList;

public class VerticalScanner implements MoveScanner {

    private final MovesRegistry movesRegistry;
    private final Settings settings;
    private LinkedList<String> column;
    private final Integer step;

    public VerticalScanner(MovesRegistry movesRegistry, Settings settings) {
        this.movesRegistry = movesRegistry;
        this.settings = settings;
        this.column = new LinkedList<>();
        this.step = settings.getBoardDimensions().getWidth();
    }

    private Integer upperField(Integer fieldNumber){
        return fieldNumber-step;
    }

    private Integer downField(Integer fieldNumber){
        return fieldNumber+step;
    }

    private Boolean onTopBorder(Integer fieldNumber){
        return fieldNumber - settings.getBoardDimensions().getWidth() < 0;
    }

    private Boolean onBottomBorder(Integer fieldNumber){
        return fieldNumber + settings.getBoardDimensions().getWidth() > settings.getBoardElementsCount();
    }

    private void scanUp(Integer fieldNumber){
        Integer currentFieldNumber = fieldNumber;

        while(column.size() < settings.getWinningCondition()
                && movesRegistry.moveExists(upperField(currentFieldNumber))
                && !onTopBorder(currentFieldNumber)){
            column.addFirst(movesRegistry.getMove(upperField(currentFieldNumber)).toString());
            currentFieldNumber = upperField(currentFieldNumber);
        }
    }

    private void scanDown(Integer fieldNumber){
        Integer currentFieldNumber = fieldNumber;

        while(column.size() < settings.getWinningCondition()
                && movesRegistry.moveExists(downField(currentFieldNumber))
                && !onBottomBorder(currentFieldNumber)){
            column.addFirst(movesRegistry.getMove(downField(currentFieldNumber)).toString());
            currentFieldNumber = downField(currentFieldNumber);
        }
    }

    private Boolean scanCurrent(Integer fieldNumber){
        boolean added = true;
        if(movesRegistry.moveExists(fieldNumber)){
            column.add(movesRegistry.getMove(fieldNumber).toString());
        }else{
            added = false;
        }
        return added;
    }

    @Override
    public Sequence scan(Integer fieldNumber) {
        column.clear();
        scanUp(fieldNumber);
        if (scanCurrent(fieldNumber)) {
            scanDown(fieldNumber);
        }
        return new Sequence(column);
    }
}
