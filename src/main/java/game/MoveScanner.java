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

    Sequence scanDiagonal(SequenceType sequenceType, int fieldNumber){
        LinkedList<String> diagonal = new LinkedList<>();
        boolean executeLoop = true;
        int fieldNumberToCheck = fieldNumber;
        Integer step = calculateStep(sequenceType);
        Integer backwardBoundaryConditions = calculateBackwardBoundaryCondition(sequenceType);
        Integer forwardBoundaryConditions = calculateForwardBoundaryConditions(sequenceType);

        //scan chosen field
        if(movesRegistry.moveExists(fieldNumberToCheck)){
            diagonal.addFirst(movesRegistry.getMove(fieldNumberToCheck).toString());
        }else{
            return new Sequence(diagonal);
        }

        if (fieldNumberToCheck % settings.getBoardDimensions().getWidth() == backwardBoundaryConditions) {
            executeLoop = false;
        }

        //scanBackward
        if(executeLoop) {
            for (int i = 0; i <= settings.getWinningCondition(); i++) {
                fieldNumberToCheck = fieldNumberToCheck - step;
                if (movesRegistry.moveExists(fieldNumberToCheck)) {
                    diagonal.addFirst(movesRegistry.getMove(fieldNumberToCheck).toString());
                    if (fieldNumberToCheck % settings.getBoardDimensions().getWidth() == backwardBoundaryConditions) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        fieldNumberToCheck = fieldNumber;

        if(fieldNumberToCheck % settings.getBoardDimensions().getWidth() == forwardBoundaryConditions){
            executeLoop = false;
        }else{
            executeLoop = true;
        }

        //scanforward
        if(executeLoop) {
            for(int i=0; i<=settings.getWinningCondition(); i++){
                fieldNumberToCheck = fieldNumberToCheck + step;
                if(movesRegistry.moveExists(fieldNumberToCheck)){
                    diagonal.addLast(movesRegistry.getMove(fieldNumberToCheck).toString());
                    if (fieldNumberToCheck % settings.getBoardDimensions().getWidth() == forwardBoundaryConditions){
                        break;
                    }
                }else{
                    break;
                }
            }
        }
        return new Sequence(diagonal);
    }

    Sequence scanHorizontally(int fieldNumber){
        int interval = 1;
        int backwardBoundaryConditions = 0;
        int forwardBoundaryConditions = settings.getBoardDimensions().getWidth() - 1;
        return scanDiagonal(SequenceType.horizontal, fieldNumber);
    }

    Sequence scanMinorDiagonal(int fieldNumber){
        int interval = settings.getBoardDimensions().getWidth() - 1;
        int backwardBoundaryConditions = settings.getBoardDimensions().getWidth() - 1;
        int forwardBoundaryConditions = 0;
        return scanDiagonal(SequenceType.minorDiagonal, fieldNumber);
    }

    Sequence scanMajorDiagonal(int fieldNumber){
        int interval = settings.getBoardDimensions().getWidth() + 1;
        int backwardBoundaryConditions = 0;
        int forwardBoundaryConditions = settings.getBoardDimensions().getWidth() - 1;
        return scanDiagonal(SequenceType.majorDiagonal, fieldNumber);
    }




}
