package game;

import board.Board;
import settings.Settings;

public class MoveSupervisor {

    private final Board board;
    private int movesCount;
    private final MoveValidator moveValidator;
    private final int winningCondition;
    private final MovesRegistry movesRegistry;
    private final Settings settings;

    public MoveSupervisor(Board board, int winningCondition, MovesRegistry movesRegistry, Settings settings) {
        this.board = board;
        this.settings = settings;
        this.moveValidator = new MoveValidator(settings, movesRegistry);
        this.winningCondition = winningCondition;
        this.movesRegistry = movesRegistry;
    }

    public boolean move(String mark, int fieldNumber){
        boolean moved = false;
        if (moveValidator.moveIsValid(fieldNumber)) {
            movesRegistry.addMove(fieldNumber, mark);
            if (mark.equals("o") || mark.equals("x")) {
                movesRegistry.addMove(fieldNumber, mark);
                movesCount++;
                moved = true;
            } else {
                throw new RuntimeException("Mark : " + mark + " is no valid");
            }
        }
        return moved;
    }

    public boolean isFreeMoveExists(){
        return movesRegistry.movesCount() < board.getElementsCount();
    }

}
