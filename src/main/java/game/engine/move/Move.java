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
    private final Writer writer;
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
        this.writer = consoleWriter;
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
            writer.println(writer.newline + writer.getTranslator().messageByCode("move_for") +
                    player.getName() + " (" + player.getMark().toString() +"), " + writer.getTranslator().messageByCode("enter_field_number") );
            fieldNumber = consoleReader.getInt();
        }while (!isMoveCorrect(player, fieldNumber));
    }

    void checkWin(Player player){
        for(MoveScanner moveScanner : scanners){
            sequences.add(moveScanner.scan(fieldNumber));
        }

        for(Sequence sequence : sequences ){
            if (arbiter.isWin(player.getMark(), sequence.toString())) {
                message = writer.newline + player.getName() + " " + writer.getTranslator().messageByCode("match_wins");
                scoresManager.addWin(player);
                matchState = MatchState.MATCH_END;
                break;
            }
        }
    }

    void checkDraw(){
        if(!moveSupervisor.isFreeMoveExists()){
            message = writer.newline + writer.getTranslator().messageByCode("checkDraw_message");
            scoresManager.addDraw();
            matchState = MatchState.MATCH_END;
        }
    }

    void announceMatchEnd(){
        writer.println(message);
        consoleReader.getString();
    }

    public void announceMatchResults(){
        writer.addNewLine();
        writer.printlnViaTranslator("announceMatchResults_header");
        writer.println(scoresManager.getSubmit());
    }

    MatchState getMatchState() {
        return matchState;
    }
}
