package game.engine;

import game.io.Writer;
import game.players.Player;

public class GameEnd {
    private final ScoresManager scoresManager;
    private final Writer writer;

    public GameEnd(ScoresManager scoresManager, Writer writer) {
        this.scoresManager = scoresManager;
        this.writer = writer;
    }

    Player getWinner(){
        Player winner = scoresManager.getPlayersRegister().getPlayersList().get(0);
        for(Player player : scoresManager.getPlayersRegister().getPlayersList()){
            if(player.getScore() > winner.getScore()){
                winner = player;
            }else if(winner.getScore() == player.getScore()
                    && !winner.getName().equals(player.getName())){
                winner = null;
            }
        }
        return winner;
    }

    public String announce(){
        String message = writer.getTranslator().messageByCode("game_submit_header") + writer.newline;

        Player winner = getWinner();
        if(winner!=null){
            message += writer.getTranslator().messageByCode("the_winner_is") + " " + winner.getName() + " (" + winner.getMark() + ")";
        }else{
            message += writer.getTranslator().messageByCode("draw_info");
        }

        message += writer.newline + scoresManager.getSubmit();

        return message;
    }


}
