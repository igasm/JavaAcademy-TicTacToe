package game;

import settings.Settings;

class MoveSupervisor {

    private final MoveValidator moveValidator;
    private final MovesRegistry movesRegistry;
    private final Settings settings;

    MoveSupervisor(MovesRegistry movesRegistry, Settings settings) {
        this.settings = settings;
        this.moveValidator = new MoveValidator(settings, movesRegistry);
        this.movesRegistry = movesRegistry;
    }

    boolean move(String mark, int fieldNumber){
        boolean moved = false;
        if (moveValidator.moveIsValid(fieldNumber)) {
            movesRegistry.addMove(fieldNumber, mark);
            if (mark.equals("o") || mark.equals("x")) {
                movesRegistry.addMove(fieldNumber, mark);
                moved = true;
            } else {
                throw new IllegalArgumentException("Mark : " + mark + " is no valid");
            }
        }
        return moved;
    }

    boolean isFreeMoveExists(){
        return movesRegistry.movesCount() < settings.getBoardElementsCount();
    }

}
