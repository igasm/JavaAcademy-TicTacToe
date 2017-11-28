package game.engine.move;

import game.settings.Settings;

public class MajorDiagonalScanner extends DiagonalScanner{

    MajorDiagonalScanner(MovesRegistry movesRegistry, Settings settings){
        super(movesRegistry, settings, settings.getBoardDimensions().getWidth() + 1);
    }

    @Override
    protected boolean onForwardBorder(Integer fieldNumber) {
        return fieldNumber % settings.getBoardDimensions().getWidth() == settings.getBoardDimensions().getWidth() - 1;
    }

    @Override
    protected boolean onBackwardBorder(Integer fieldNumber) {
        return fieldNumber % settings.getBoardDimensions().getWidth() == 0;
    }
}
