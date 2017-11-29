package game.engine.move;

import game.engine.ScoresManager;
import game.engine.move.scanner.*;
import game.io.ConsoleReader;
import game.io.Writer;
import game.settings.Settings;

import java.util.ArrayList;
import java.util.List;

public class MoveBuilder {

    private MovesRegistry movesRegistry = null;
    private Settings settings = null;
    private Writer writer = null;
    private ConsoleReader consoleReader = null;
    private ScoresManager scoresManager = null;

    public MoveBuilder withMovesRegistry(MovesRegistry movesRegistry){
        this.movesRegistry = movesRegistry;
        return this;
    }

    public MoveBuilder withSettings(Settings settings){
        this.settings = settings;
        return this;
    }

    public MoveBuilder withWriter(Writer writer){
        this.writer = writer;
        return this;
    }

    public MoveBuilder withConsoleReader(ConsoleReader consoleReader){
        this.consoleReader = consoleReader;
        return this;
    }

    public MoveBuilder withScoresManager(ScoresManager scoresManager){
        this.scoresManager = scoresManager;
        return this;
    }


    public Move build(){
        assert movesRegistry!=null;
        assert settings!=null;
        assert writer!=null;
        assert consoleReader!=null;
        assert scoresManager!=null;

        List<MoveScanner> scanners = new ArrayList<>();
        scanners.add(new HorizontalScanner(movesRegistry, settings));
        scanners.add(new VerticalScanner(movesRegistry, settings));
        scanners.add(new MajorDiagonalScanner(movesRegistry, settings));
        scanners.add(new MinorDiagonalScanner(movesRegistry, settings));

        MoveSupervisor moveSupervisor = new MoveSupervisor(movesRegistry, settings, writer);
        Arbiter arbiter = new Arbiter(settings.getWinningCondition());

        return new Move(scanners, moveSupervisor, writer, consoleReader, arbiter, scoresManager);
    }

}
