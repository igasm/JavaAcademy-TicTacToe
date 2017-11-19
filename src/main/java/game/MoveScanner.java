package game;

import settings.Settings;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *      \ major diagonal
 *      / minor diagonal
 */

public class MoveScanner {

    private final MovesRegistry movesRegistry;
    private final Settings settings;

    public MoveScanner(MovesRegistry movesRegistry, Settings settings) {
        this.movesRegistry = movesRegistry;
        this.settings = settings;
    }

    public List<Sequence> scanAllDirections(int fieldNumber){
        List<Sequence> sequences = new ArrayList<>();
        sequences.add(scanFromLeftTopToRightBottom(fieldNumber));
        sequences.add(scanHorizontally(fieldNumber));
        sequences.add(scanVertically(fieldNumber));
        sequences.add(scanFromLeftBottomToRightTop(fieldNumber));
        return sequences;
    }

    public Sequence scanVertically(int fieldNumber){
        LinkedList<String> column = new LinkedList<>();
        int interval = settings.getBoardDimensions().getWidth();
        int fieldNumberToCheck;
        //scanBackward
        for(int i=0; i<=settings.getWinningCondition(); i++){
            fieldNumberToCheck = fieldNumber - i*interval;
            if(movesRegistry.moveExists(fieldNumberToCheck)){
                column.addFirst(movesRegistry.getMove(fieldNumberToCheck));
            }else{
                break;
            }
        }
        //scanForward
        for (int i=1; i<=settings.getWinningCondition(); i++){
            fieldNumberToCheck = fieldNumber + i*interval;
            if(movesRegistry.moveExists(fieldNumberToCheck)){
                column.addLast(movesRegistry.getMove(fieldNumberToCheck));
            }else{
                break;
            }
        }
        return new Sequence(column);
    }

    public Sequence scanHorizontally2(int fieldNumber){
        LinkedList<String> row = new LinkedList<>();
        int interval = 1;
        //scan initial field
        if(movesRegistry.moveExists(fieldNumber)){
            row.addFirst(movesRegistry.getMove(fieldNumber));
        }

        int fieldNumberToCheck;
        //scanBackward
        for(int i=0; i<=settings.getWinningCondition(); i++){
            fieldNumberToCheck = fieldNumber - i*interval;
            if(movesRegistry.moveExists(fieldNumberToCheck)){
                row.addFirst(movesRegistry.getMove(fieldNumberToCheck));
            }else{
                break;
            }
        }
        //scanForward
        for (int i=1; i<=settings.getWinningCondition(); i++){
            fieldNumberToCheck = fieldNumber + i*interval;
            if(movesRegistry.moveExists(fieldNumberToCheck)){
                row.addLast(movesRegistry.getMove(fieldNumberToCheck));
            }else{
                break;
            }
        }
        return new Sequence(row);
    }

    //scanFromLeftTopToRightBottom
    public Sequence scanDiagonal(int fieldNumber, int interval, int backwardBoundaryConditions, int forwardBoundaryConditions){
        LinkedList<String> diagonal = new LinkedList<>();
        boolean executeLoop = true;
        int fieldNumberToCheck = fieldNumber;

        //scan chosen field
        if(movesRegistry.moveExists(fieldNumberToCheck)){
            diagonal.addFirst(movesRegistry.getMove(fieldNumberToCheck));
        }else{
            return new Sequence(diagonal);
        }

        if (fieldNumberToCheck % settings.getBoardDimensions().getWidth() == backwardBoundaryConditions) {
            executeLoop = false;
        }

        //scanBackward
        if(executeLoop) {
            for (int i = 0; i <= settings.getWinningCondition(); i++) {
                fieldNumberToCheck = fieldNumberToCheck - interval;
                if (movesRegistry.moveExists(fieldNumberToCheck)) {
                    diagonal.addFirst(movesRegistry.getMove(fieldNumberToCheck));
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
                fieldNumberToCheck = fieldNumberToCheck + interval;
                if(movesRegistry.moveExists(fieldNumberToCheck)){
                    diagonal.addLast(movesRegistry.getMove(fieldNumberToCheck));
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

    public Sequence scanHorizontally(int fieldNumber){
        int interval = 1;
        int backwardBoundaryConditions = 0;
        int forwardBoundaryConditions = settings.getBoardDimensions().getWidth() - 1;
        return scanDiagonal(fieldNumber, interval, backwardBoundaryConditions, forwardBoundaryConditions);
    }

    public Sequence scanFromLeftBottomToRightTop(int fieldNumber){
        int interval = settings.getBoardDimensions().getWidth() - 1;
        int backwardBoundaryConditions = settings.getBoardDimensions().getWidth() - 1;
        int forwardBoundaryConditions = 0;
        return scanDiagonal(fieldNumber, interval, backwardBoundaryConditions, forwardBoundaryConditions);
    }

    public Sequence scanFromLeftTopToRightBottom(int fieldNumber){
        int interval = settings.getBoardDimensions().getWidth() + 1;
        int backwardBoundaryConditions = 0;
        int forwardBoundaryConditions = settings.getBoardDimensions().getWidth() - 1;
        return scanDiagonal(fieldNumber, interval, backwardBoundaryConditions, forwardBoundaryConditions);
    }




}
