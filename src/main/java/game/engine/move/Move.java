package game.engine.move;

import game.engine.ScoresManager;
import game.engine.move.scanner.MoveScanner;
import game.engine.move.scanner.Sequence;
import game.io.ConsoleReader;
import game.io.Writer;
import game.players.Player;

import java.util.ArrayList;
import java.util.List;

public class Move {

    private final List<MoveScanner> scanners;
    private final MoveSupervisor moveSupervisor;
    private final Writer consoleWriter;
    private final ConsoleReader consoleReader;
    private Integer fieldNumber;
    private List<Sequence> sequences;
    private final Arbiter arbiter;
    private final ScoresManager scoresManager;
    private MatchState matchState;
    private String message;


    public Move(List<MoveScanner> scanners, MoveSupervisor moveSupervisor, Writer consoleWriter, ConsoleReader consoleReader, Arbiter arbiter, ScoresManager scoresManager) {
        this.scanners = scanners;
        this.moveSupervisor = moveSupervisor;
        this.consoleWriter = consoleWriter;
        this.consoleReader = consoleReader;
        this.arbiter = arbiter;
        this.scoresManager = scoresManager;
        this.sequences = new ArrayList<>(4);
        this.matchState = MatchState.MATCH_ON;
        this.message = "";
        this.fieldNumber = -1;
    }

    public MatchState run(Player player){
        matchState = MatchState.MATCH_ON;
        offerPlayerMove(player);
        checkWin(player);
        if(matchState == MatchState.MATCH_ON){
            checkDraw();
        }
        if(matchState == MatchState.MATCH_END){
            announceMatchEnd();
        }
        return matchState;
    }

    boolean isMoveCorrect(Player player, Integer fieldNumber){
        return moveSupervisor.move(player.getMark(), fieldNumber);
    }

    void offerPlayerMove(Player player){
        do{
            consoleWriter.accept(consoleWriter.newline + "Ruch dla " + player.getName() + " (" + player.getMark().toString() +"), podaj numer pola" );
            fieldNumber = consoleReader.getInt();
        }while (!isMoveCorrect(player, fieldNumber));
    }

    void checkWin(Player player){
        for(MoveScanner moveScanner : scanners){
            sequences.add(moveScanner.scan(fieldNumber));
        }

        for(Sequence sequence : sequences ){
            if (arbiter.isWin(player.getMark(), sequence.toString())) {
                message = consoleWriter.newline + player.getName() + " wygrywa rundę";
                scoresManager.addWin(player);
                matchState = MatchState.MATCH_END;
                break;
            }
        }
    }

    void checkDraw(){
        if(!moveSupervisor.isFreeMoveExists()){
            message = consoleWriter.newline + "Koniec rundy - remis";
            scoresManager.addDraw();
            matchState = MatchState.MATCH_END;
        }
    }

    void announceMatchEnd(){
        consoleWriter.accept(message);
        consoleReader.getString();
    }

    public void announceMatchResults(){
        consoleWriter.accept(consoleWriter.newline + "Wyniki");
        consoleWriter.accept(scoresManager.getSubmit());
    }

}
