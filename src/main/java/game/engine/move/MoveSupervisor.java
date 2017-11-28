package game.engine.move;

import game.engine.MarkType;
import game.settings.Settings;

public class MoveSupervisor {

    private final MoveValidator moveValidator;
    private final MovesRegistry movesRegistry;
    private final Settings settings;

    public MoveSupervisor(MovesRegistry movesRegistry, Settings settings) {
        this.settings = settings;
        this.moveValidator = new MoveValidator(settings, movesRegistry);
        this.movesRegistry = movesRegistry;
    }

    public boolean move(MarkType mark, int fieldNumber){
        boolean moved;
        if (moved = moveValidator.moveIsValid(fieldNumber)) {
            movesRegistry.addMove(fieldNumber, mark);
        }
        return moved;
    }

    public boolean isFreeMoveExists(){
        return movesRegistry.movesCount() < settings.getBoardElementsCount();
    }

}
