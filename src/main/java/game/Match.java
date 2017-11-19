package game;

import board.Board;
import board.BoardPrinter;
import players.Player;
import players.PlayersQueue;
import settings.Settings;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Match {

    private final Consumer<String> consoleWriter;
    private final PlayersQueue playersQueue;
    private final Board board;
    private final MoveSupervisor moveSupervisor;
    private final MoveScanner boardScanner;
    private final Settings settings;
    private final ScoresManager scoresManager;
    private final MovesRegistry movesRegistry;
    private boolean matchOn;
    private final Supplier<String> consoleReader;

    public Match(Consumer<String> consoleWriter, PlayersQueue playersQueue, Board board, MoveSupervisor moveSupervisor, MoveScanner boardScanner, Settings settings, ScoresManager scoresManager, MovesRegistry movesRegistry, Supplier<String> consoleReader) {
        this.consoleWriter = consoleWriter;
        this.playersQueue = playersQueue;
        this.board = board;
        this.moveSupervisor = moveSupervisor;
        this.boardScanner = boardScanner;
        this.settings = settings;
        this.scoresManager = scoresManager;
        this.consoleReader = consoleReader;
        this.matchOn = true;
        this.movesRegistry = movesRegistry;
    }


    public void run(){
        movesRegistry.clear();
        Scanner sc = new Scanner(System.in);
        String newline = System.getProperty("line.separator");
        BoardPrinter boardPrinter = new BoardPrinter(board, settings.getBoardDimensions(), consoleWriter);
        matchOn = true;
        consoleWriter.accept(board.toString());
        while(matchOn){
            Player currentPlayer = playersQueue.getNextPlayer();
            consoleWriter.accept(newline + "Ruch dla " + currentPlayer.getName() + " (" + currentPlayer.getMark() +"), podaj numer pola" );
            int fieldNumber = sc.nextInt();
            moveSupervisor.move(currentPlayer.getMark(), fieldNumber);
            boardPrinter.printBoardWithMoves(movesRegistry);
            List<Sequence> sequences = boardScanner.scanAllDirections(fieldNumber);
            Arbiter arbiter = new Arbiter(settings.getWinningCondition());
            for(Sequence sequence : sequences ){
                if (arbiter.isWin(currentPlayer.getMark(), sequence.toString())) {
                    consoleWriter.accept(newline + currentPlayer.getName() + " wygrywa rundę");
                    scoresManager.addWin(currentPlayer);
                    consoleReader.get();
                    matchOn=false;
                }
            }
            if(!moveSupervisor.isFreeMoveExists()){
                consoleWriter.accept("Koniec rundy - remis");
                scoresManager.addDraw();
                matchOn=false;
            }
        }
        consoleWriter.accept(newline + "Wyniki rundy");
        consoleWriter.accept(scoresManager.getSubmit());
    }

}
