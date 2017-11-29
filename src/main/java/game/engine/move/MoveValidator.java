package game.engine.move;

import game.engine.move.MovesRegistry;
import game.settings.Settings;

class MoveValidator {
    private final Settings settings;
    private final MovesRegistry movesRegistry;

    MoveValidator(Settings settings, MovesRegistry movesRegistry) {
        this.settings = settings;
        this.movesRegistry = movesRegistry;
    }

    boolean moveIsValid(int fieldNumber) throws TooBigFieldNumberException, NegativeFieldNumberException, FieldAlreadyMarkedException {
        boolean moveIsValid = true;
        if(fieldNumber >= settings.getBoardElementsCount()){
            throw new TooBigFieldNumberException("Numer pola "
                    + fieldNumber
                    + " jest większe niż numer ostatniego pola ("
                    + settings.getBoardElementsCount() + ")");
        }else if(fieldNumber < 0){
            throw new NegativeFieldNumberException("Numer pola nie może byc ujemny ");
        }

        if(movesRegistry.moveExists(fieldNumber)){
            throw new FieldAlreadyMarkedException("Pole o numerze " + fieldNumber + " jest już zaznaczone");
        }
        return moveIsValid;
    }
}
