package game.engine.move;

import game.engine.MarkType;
import game.io.Writer;
import game.settings.Settings;

public class MoveSupervisor {

    private final MoveValidator moveValidator;
    private final MovesRegistry movesRegistry;
    private final Settings settings;
    private final Writer writer;

    public MoveSupervisor(MovesRegistry movesRegistry, Settings settings, Writer writer) {
        this.settings = settings;
        this.writer = writer;
        this.moveValidator = new MoveValidator(settings, movesRegistry);
        this.movesRegistry = movesRegistry;
    }

    public boolean move(MarkType mark, int fieldNumber){
        boolean moved = false;
        try {
            if (moved = moveValidator.moveIsValid(fieldNumber)) {
                movesRegistry.addMove(fieldNumber, mark);
            }
        } catch (TooBigFieldNumberException e) {
            writer.accept("TooBigFieldNumberException");
        } catch (NegativeFieldNumberException e) {
            writer.accept("NegativeFieldNumberException");
        } catch (FieldAlreadyMarkedException e) {
            writer.accept("FieldAlreadyMarkedException");
        }
        return moved;
    }

    public boolean isFreeMoveExists(){
        return movesRegistry.movesCount() < settings.getBoardElementsCount();
    }

}
