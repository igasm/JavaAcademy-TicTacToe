package game.engine.move.scanner;

import game.engine.move.MovesRegistry;
import game.settings.Settings;

import java.util.LinkedList;
import java.util.List;

public abstract class DiagonalScanner implements MoveScanner {

    private final MovesRegistry movesRegistry;
    final Settings settings;
    private LinkedList<String> diagonal = new LinkedList<>();
    private final Integer step;

    DiagonalScanner(MovesRegistry movesRegistry, Settings settings, Integer step) {
        this.movesRegistry = movesRegistry;
        this.settings = settings;
        this.step = step;
    }

    private List<String> scanBackward(Integer fieldNumber){
        LinkedList<String> sequence = new LinkedList<>();
        Integer currentFieldNumber = fieldNumber;

        while(sequence.size() < settings.getWinningCondition()
                && movesRegistry.moveExists(previousField(currentFieldNumber))
                && !onBackwardBorder(currentFieldNumber)){
            sequence.addFirst(movesRegistry.getMove(previousField(currentFieldNumber)).toString());
            currentFieldNumber = previousField(currentFieldNumber);
        }

        return sequence;
    }

    private List<String> scanForward(Integer fieldNumber){
        LinkedList<String> sequence = new LinkedList<>();
        Integer currentFieldNumber = fieldNumber;

        while((sequence.size() < settings.getWinningCondition())
                && movesRegistry.moveExists(nextField(currentFieldNumber))
                && !onForwardBorder(currentFieldNumber)){
            sequence.addFirst(movesRegistry.getMove(nextField(currentFieldNumber)).toString());
            currentFieldNumber = nextField(currentFieldNumber);
        }

        return sequence;
    }

    private Integer nextField(Integer fieldNumber){
        return fieldNumber + step;
    }

    private Integer previousField(Integer fieldNumber){
        return fieldNumber - step;
    }

    protected abstract boolean onForwardBorder(Integer currentFieldNumber);

    protected abstract boolean onBackwardBorder(Integer currentFieldNumber);


    @Override
    public Sequence scan(Integer fieldNumber) {
        diagonal.clear();

        diagonal.addAll(scanBackward(fieldNumber));
        if(movesRegistry.moveExists(fieldNumber)){
            diagonal.addLast(movesRegistry.getMove(fieldNumber).toString());
        }else{
            return new Sequence(diagonal);
        }
        diagonal.addAll(scanForward(fieldNumber));

        return new Sequence(diagonal);
    }
}
