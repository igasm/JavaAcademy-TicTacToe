package game.engine.move;

import game.engine.ScoresManager;
import game.io.ConsoleReader;
import game.io.Writer;
import game.settings.Settings;

import java.util.ArrayList;
import java.util.List;

public class MoveBuilder {

    private final MovesRegistry movesRegistry;
    private final Settings settings;
    private final Writer writer;
    private final ConsoleReader consoleReader;
    private final ScoresManager scoresManager;

    public MoveBuilder(MovesRegistry movesRegistry, Settings settings, Writer writer, ConsoleReader consoleReader, ScoresManager scoresManager) {
        this.movesRegistry = movesRegistry;
        this.settings = settings;
        this.writer = writer;
        this.consoleReader = consoleReader;
        this.scoresManager = scoresManager;
    }

    public Move build(){
        List<MoveScanner> scanners = new ArrayList<>();
        scanners.add(new HorizontalScanner(movesRegistry, settings));
        scanners.add(new VerticalScanner(movesRegistry, settings));
        scanners.add(new MajorDiagonalScanner(movesRegistry, settings));
        scanners.add(new MinorDiagonalScanner(movesRegistry, settings));

        MoveSupervisor moveSupervisor = new MoveSupervisor(movesRegistry, settings);
        Arbiter arbiter = new Arbiter(settings.getWinningCondition());

        return new Move(scanners, moveSupervisor, writer, consoleReader, arbiter, scoresManager);


    }

}
