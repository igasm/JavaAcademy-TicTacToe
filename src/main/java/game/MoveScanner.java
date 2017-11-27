package game;

import settings.Settings;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *      \ major diagonal
 *      / minor diagonal
 */

class MoveScanner {

    private final MovesRegistry movesRegistry;
    private final Settings settings;
    private enum SequenceType { majorDiagonal, minorDiagonal, vertical, horizontal };

    MoveScanner(MovesRegistry movesRegistry, Settings settings) {
        this.movesRegistry = movesRegistry;
        this.settings = settings;
    }

    List<Sequence> scanAllDirections(int fieldNumber){
        List<Sequence> sequences = new ArrayList<>();
        sequences.add(scanMajorDiagonal(fieldNumber));
        sequences.add(scanHorizontally(fieldNumber));
        sequences.add(scanVertically(fieldNumber));
        sequences.add(scanMinorDiagonal(fieldNumber));
        return sequences;
    }

    Sequence scanVertically(int fieldNumber){
        LinkedList<String> column = new LinkedList<>();
        int interval = settings.getBoardDimensions().getWidth();
        int fieldNumberToCheck;
        //scanBackward
        for(int i=0; i<=settings.getWinningCondition(); i++){
            fieldNumberToCheck = fieldNumber - i*interval;
            if(movesRegistry.moveExists(fieldNumberToCheck)){
                column.addFirst(movesRegistry.getMove(fieldNumberToCheck).toString());
            }else{
                break;
            }
        }
        //scanForward
        for (int i=1; i<=settings.getWinningCondition(); i++){
            fieldNumberToCheck = fieldNumber + i*interval;
            if(movesRegistry.moveExists(fieldNumberToCheck)){
                column.addLast(movesRegistry.getMove(fieldNumberToCheck).toString());
            }else{
                break;
            }
        }
        return new Sequence(column);
    }

    private Integer calculateStep(SequenceType sequenceType){
        Integer step;
        switch (sequenceType){
            case majorDiagonal : step = settings.getBoardDimensions().getWidth() + 1; break;
            case minorDiagonal : step = settings.getBoardDimensions().getWidth() - 1; break;
            case horizontal : step = 1; break;
            default: step = 0;
        }
        return step;
    }

    private Integer calculateBackwardBoundaryCondition(SequenceType sequenceType){
        Integer condition;
        switch (sequenceType){
            case majorDiagonal : condition = 0; break;
            case minorDiagonal : condition = settings.getBoardDimensions().getWidth() - 1; break;
            case horizontal : condition = 0; break;
            default: condition = 0;
        }
        return condition;
    }

    private Integer calculateForwardBoundaryConditions(SequenceType sequenceType){
        Integer condition;
        switch (sequenceType){
            case majorDiagonal : condition = settings.getBoardDimensions().getWidth() - 1; break;
            case minorDiagonal : condition = 0; break;
            case horizontal : condition = settings.getBoardDimensions().getWidth() - 1; break;
            default: condition = 0;
        }
        return condition;
    }

    private Integer previousField(Integer step, Integer fieldNumber){
        return fieldNumber - step;
    }

    private Integer nextField(Integer step, Integer fieldNumber){
        return fieldNumber + step;
    }

    private Boolean onBackwardBorder(SequenceType sequenceType, Integer fieldNumber){
        return fieldNumber % settings.getBoardDimensions().getWidth() == calculateBackwardBoundaryCondition(sequenceType);
    }

    private Boolean onForwardBorder(SequenceType sequenceType, Integer fieldNumber){
        return fieldNumber % settings.getBoardDimensions().getWidth() == calculateForwardBoundaryConditions(sequenceType);
    }

    private List<String> scanBackward(SequenceType sequenceType, Integer fieldNumber){
        LinkedList<String> sequence = new LinkedList<>();
        Integer step = calculateStep(sequenceType);
        Integer currentFieldNumber = fieldNumber;

        while(sequence.size() < settings.getWinningCondition()
                && movesRegistry.moveExists(previousField(step, currentFieldNumber))
                && !onBackwardBorder(sequenceType, currentFieldNumber)){
            sequence.addFirst(movesRegistry.getMove(previousField(step, currentFieldNumber)).toString());
            currentFieldNumber = previousField(step, currentFieldNumber);
        }

        return sequence;
    }

    private List<String> scanForward(SequenceType sequenceType, Integer fieldNumber){
        LinkedList<String> sequence = new LinkedList<>();
        Integer step = calculateStep(sequenceType);
        Integer currentFieldNumber = fieldNumber;

        while(sequence.size() < settings.getWinningCondition()
                && movesRegistry.moveExists(nextField(step, currentFieldNumber))
                && !onForwardBorder(sequenceType, currentFieldNumber)){
            sequence.addFirst(movesRegistry.getMove(nextField(step, currentFieldNumber)).toString());
            currentFieldNumber = nextField(step, currentFieldNumber);
        }

        return sequence;
    }


    Sequence scanDiagonal(SequenceType sequenceType, int fieldNumber){
        LinkedList<String> diagonal = new LinkedList<>();

        diagonal.addAll(scanBackward(sequenceType, fieldNumber));
        if(movesRegistry.moveExists(fieldNumber)){
            diagonal.addLast(movesRegistry.getMove(fieldNumber).toString());
        }else{
            return new Sequence(diagonal);
        }
        diagonal.addAll(scanForward(sequenceType, fieldNumber));

        return new Sequence(diagonal);
    }

    Sequence scanHorizontally(int fieldNumber){
        return scanDiagonal(SequenceType.horizontal, fieldNumber);
    }

    Sequence scanMinorDiagonal(int fieldNumber){
        return scanDiagonal(SequenceType.minorDiagonal, fieldNumber);
    }

    Sequence scanMajorDiagonal(int fieldNumber){
        return scanDiagonal(SequenceType.majorDiagonal, fieldNumber);
    }




}
