package game.engine.move;

import game.settings.Settings;

import java.util.LinkedList;

public class HorizontalScanner implements MoveScanner{

    private final MovesRegistry movesRegistry;
    private final Settings settings;
    private LinkedList<String> row;

    public HorizontalScanner(MovesRegistry movesRegistry, Settings settings) {
        this.movesRegistry = movesRegistry;
        this.settings = settings;
        this.row = new LinkedList<>();
    }

    private Integer leftField(Integer fieldNumber){
        return --fieldNumber;
    }

    private Integer rightField(Integer fieldNumber){
        return ++fieldNumber;
    }

    private Boolean onLeftBorder(Integer fieldNumber){
        return fieldNumber % settings.getBoardDimensions().getWidth() == 0;
    }

    private Boolean onRightBorder(Integer fieldNumber){
        return fieldNumber % settings.getBoardDimensions().getWidth() == settings.getBoardDimensions().getWidth()-1;
    }

    private void scanLeft(Integer fieldNumber){
        Integer currentFieldNumber = fieldNumber;

        while(row.size() < settings.getWinningCondition()
                && movesRegistry.moveExists(leftField(currentFieldNumber))
                && !onLeftBorder(currentFieldNumber)){
            row.addFirst(movesRegistry.getMove(leftField(currentFieldNumber)).toString());
            currentFieldNumber = leftField(currentFieldNumber);
        }
    }

    private void scanRight(Integer fieldNumber){
        Integer currentFieldNumber = fieldNumber;

        while(row.size() < settings.getWinningCondition()
                && movesRegistry.moveExists(rightField(currentFieldNumber))
                && !onRightBorder(currentFieldNumber)){
            row.addFirst(movesRegistry.getMove(rightField(currentFieldNumber)).toString());
            currentFieldNumber = rightField(currentFieldNumber);
        }
    }

    private Boolean scanCurrent(Integer fieldNumber){
        boolean added = true;
        if(movesRegistry.moveExists(fieldNumber)){
            row.add(movesRegistry.getMove(fieldNumber).toString());
        }else{
            added = false;
        }
        return added;
    }

    @Override
    public Sequence scan(Integer fieldNumber) {
        row.clear();
        scanLeft(fieldNumber);
        if(scanCurrent(fieldNumber)){
            scanRight(fieldNumber);
        }
        return new Sequence(row);
    }
}
