package game;

import board.BoardBuilder;
import board.BoardPrinter;
import io.ConsoleReader;
import players.Player;
import players.PlayersQueue;
import settings.Settings;

import java.util.List;
import java.util.function.Consumer;

class Match {

    private final Consumer<String> consoleWriter;
    private final PlayersQueue playersQueue;
    private final MoveSupervisor moveSupervisor;
    private final MoveScanner moveScanner;
    private final Settings settings;
    private final ScoresManager scoresManager;
    private final MovesRegistry movesRegistry;
    private boolean matchOn;
    private final ConsoleReader consoleReader;
    private final BoardBuilder boardBuilder;

    Match(Consumer<String> consoleWriter, PlayersQueue playersQueue, Settings settings, ScoresManager scoresManager, ConsoleReader consoleReader) {
        this.consoleWriter = consoleWriter;
        this.playersQueue = playersQueue;
        this.settings = settings;
        this.scoresManager = scoresManager;
        this.consoleReader = consoleReader;
        this.matchOn = true;
        this.boardBuilder = new BoardBuilder(settings.getBoardDimensions());
        this.movesRegistry = new MovesRegistry();
        this.moveScanner = new MoveScanner(movesRegistry, settings);
        this.moveSupervisor = new MoveSupervisor(movesRegistry, settings);
    }


    void run(){
        movesRegistry.clear();
        String newline = System.getProperty("line.separator");
        BoardPrinter boardPrinter = new BoardPrinter(boardBuilder.viaBoard(), settings.getBoardDimensions(), consoleWriter);
        matchOn = true;
        boolean moveIsCorrect;
        int fieldNumber = 0;
        boardPrinter.printBord();
        while(matchOn){
            moveIsCorrect = false;
            Player currentPlayer = playersQueue.getNextPlayer();
            while (!moveIsCorrect) {
                try {
                    consoleWriter.accept(newline + "Ruch dla " + currentPlayer.getName() + " (" + currentPlayer.getMark().toString() +"), podaj numer pola" );
                    fieldNumber = consoleReader.getInt();
                    moveIsCorrect = moveSupervisor.move(currentPlayer.getMark(), fieldNumber);
                }catch (Exception e){
                    consoleWriter.accept(e.getMessage());
                }
            }
            boardPrinter.printBoardWithMoves(movesRegistry);
            List<Sequence> sequences = moveScanner.scanAllDirections(fieldNumber);
            Arbiter arbiter = new Arbiter(settings.getWinningCondition());
            for(Sequence sequence : sequences ){
                if (arbiter.isWin(currentPlayer.getMark(), sequence.toString())) {
                    consoleWriter.accept(newline + currentPlayer.getName() + " wygrywa rundę");
                    scoresManager.addWin(currentPlayer);
                    consoleReader.getString();
                    matchOn=false;
                }
            }
            if(matchOn && !moveSupervisor.isFreeMoveExists()){
                consoleWriter.accept(newline + "Koniec rundy - remis");
                scoresManager.addDraw();
                consoleReader.getString();
                matchOn=false;
            }
        }
        consoleWriter.accept(newline + "Wyniki po rundzie");
        consoleWriter.accept(scoresManager.getSubmit());
    }

}
