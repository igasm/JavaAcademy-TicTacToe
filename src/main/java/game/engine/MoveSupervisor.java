package game.engine;

import game.settings.Settings;

class MoveSupervisor {

    private final MoveValidator moveValidator;
    private final MovesRegistry movesRegistry;
    private final Settings settings;

    MoveSupervisor(MovesRegistry movesRegistry, Settings settings) {
        this.settings = settings;
        this.moveValidator = new MoveValidator(settings, movesRegistry);
        this.movesRegistry = movesRegistry;
    }

    boolean move(MarkType mark, int fieldNumber){
        boolean moved;
        if (moved = moveValidator.moveIsValid(fieldNumber)) {
            movesRegistry.addMove(fieldNumber, mark);
        }
        return moved;
    }

    boolean isFreeMoveExists(){
        return movesRegistry.movesCount() < settings.getBoardElementsCount();
    }

}
