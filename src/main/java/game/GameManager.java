package game;

import game.engine.GameEnd;
import game.engine.Match;
import game.engine.ScoresManager;
import game.engine.move.Move;
import game.engine.move.MoveBuilder;
import game.engine.move.MovesRegistry;
import game.io.ConsoleReader;
import game.io.Writer;
import game.players.PlayersQueue;
import game.settings.Settings;

class GameManager {
    private final PlayersQueue playersQueue;
    private final Writer consoleWriter;
    private final Settings settings;
    private final ScoresManager scoresManager;
    private final ConsoleReader consoleReader;
    private final GameEnd gameEnd;
    private boolean gameExit = false;
    private Integer matchNo = 0;

    GameManager(PlayersQueue playersQueue, Writer consoleWriter, Settings settings, ScoresManager scoresManager, ConsoleReader consoleReader) {
        this.playersQueue = playersQueue;
        this.consoleWriter = consoleWriter;
        this.settings = settings;
        this.scoresManager = scoresManager;
        this.consoleReader = consoleReader;
        gameEnd = new GameEnd(scoresManager, consoleWriter);
    }

    void runGame(){
        MovesRegistry movesRegistry = new MovesRegistry();
        Move move = new MoveBuilder()
                .withConsoleReader(consoleReader)
                .withMovesRegistry(movesRegistry)
                .withScoresManager(scoresManager)
                .withSettings(settings)
                .withWriter(consoleWriter).build();
        Match match = new Match(consoleWriter, playersQueue, settings, movesRegistry, move);
        while (matchNo++ < 3 && !gameExit){
            consoleWriter.print(consoleWriter.newline + "======");
            consoleWriter.printViaTranslator("match_no_header");
            consoleWriter.print(" " + matchNo);
            consoleWriter.println("======");
            match.run();
        }

        if(!gameExit) {
            consoleWriter.println("===========================");
            consoleWriter.print(gameEnd.announce());
        }
    }

}
