package game;

import settings.Settings;

public class MoveValidator {
    private final Settings settings;
    private final MovesRegistry movesRegistry;

    public MoveValidator(Settings settings, MovesRegistry movesRegistry) {
        this.settings = settings;
        this.movesRegistry = movesRegistry;
    }

    public boolean moveIsValid(int fieldNumber){
        boolean moveIsValid = true;
        if(fieldNumber >= settings.getBoardElementsCount()){
            throw new IndexOutOfBoundsException("Field number "
                    + fieldNumber
                    + " is greater than number of last field"
                    + settings.getBoardElementsCount());
        }

        if(movesRegistry.moveExists(fieldNumber)){
            throw new RuntimeException("Field number " + fieldNumber + " is already marked");
        }
        return moveIsValid;
    }
}
