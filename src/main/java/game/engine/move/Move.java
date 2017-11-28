package game.engine.move;

import game.engine.ScoresManager;
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
    private Player player;
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
        this.player = player;
        matchState = MatchState.MATCH_ON;
        offerPlayerMove();
        checkWin();
        if(matchState == MatchState.MATCH_ON){
            checkDraw();
        }
        if(matchState == MatchState.MATCH_END){
            announceMatchEnd();
        }
        return matchState;
    }

    boolean isMoveIsCorrect(Player player, Integer fieldNumber){
        return moveSupervisor.move(player.getMark(), fieldNumber);
    }

    void offerPlayerMove(){
        do{
            consoleWriter.accept(consoleWriter.newline + "Ruch dla " + player.getName() + " (" + player.getMark().toString() +"), podaj numer pola" );
            fieldNumber = consoleReader.getInt();
        }while (!isMoveIsCorrect(player, fieldNumber));
    }

    void checkWin(){
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
