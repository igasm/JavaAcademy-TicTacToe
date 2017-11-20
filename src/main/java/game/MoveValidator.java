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
            throw new IndexOutOfBoundsException("Numer pola "
                    + fieldNumber
                    + " jest większe niż numer ostatniego pola ("
                    + settings.getBoardElementsCount() + ")");
        }

        if(movesRegistry.moveExists(fieldNumber)){
            throw new RuntimeException("Pole o numerze " + fieldNumber + " jest już zaznaczone");
        }
        return moveIsValid;
    }
}
