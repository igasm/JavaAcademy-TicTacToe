package game.engine;

import game.board.BoardBuilder;
import game.board.BoardPrinter;
import game.engine.move.MatchState;
import game.engine.move.Move;
import game.engine.move.MovesRegistry;
import game.io.Writer;
import game.players.Player;
import game.players.PlayersQueue;
import game.settings.Settings;

public class Match {

    private final Writer consoleWriter;
    private final PlayersQueue playersQueue;
    private final Settings settings;
    private final MovesRegistry movesRegistry;
    private MatchState matchState;
    private final Move move;

    public Match(Writer consoleWriter, PlayersQueue playersQueue, Settings settings, MovesRegistry movesRegistry, Move move) {
        this.consoleWriter = consoleWriter;
        this.playersQueue = playersQueue;
        this.settings = settings;
        this.movesRegistry = movesRegistry;
        this.move = move;
    }


    public void run(){
        movesRegistry.clear();
        BoardPrinter boardPrinter = new BoardPrinter(new BoardBuilder(settings.getBoardDimensions()).viaBoard(), settings.getBoardDimensions(), consoleWriter);
        matchState = MatchState.MATCH_ON;
        boardPrinter.printBord();
        while(matchState == MatchState.MATCH_ON){
            Player currentPlayer = playersQueue.getNextPlayer();
            matchState = move.run(currentPlayer);
            consoleWriter.addNewLine();
            boardPrinter.printBoardWithMoves(movesRegistry);
        }
        move.announceMatchResults();
    }

}
