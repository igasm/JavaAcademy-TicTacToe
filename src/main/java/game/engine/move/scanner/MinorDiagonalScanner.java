package game.engine.move.scanner;

import game.engine.move.MovesRegistry;
import game.engine.move.scanner.DiagonalScanner;
import game.settings.Settings;

public class MinorDiagonalScanner extends DiagonalScanner {

    public MinorDiagonalScanner(MovesRegistry movesRegistry, Settings settings){
        super(movesRegistry, settings, settings.getBoardDimensions().getWidth() - 1);
    }

    @Override
    protected boolean onForwardBorder(Integer fieldNumber) {
        return fieldNumber % settings.getBoardDimensions().getWidth() == 0;
    }

    @Override
    protected boolean onBackwardBorder(Integer fieldNumber) {
        return fieldNumber % settings.getBoardDimensions().getWidth() == settings.getBoardDimensions().getWidth() - 1;
    }
}
