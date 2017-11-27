package game.engine;

import game.players.Player;

class GameEnd {
    private final ScoresManager scoresManager;

    GameEnd(ScoresManager scoresManager) {
        this.scoresManager = scoresManager;
    }

    Player getWinner(){
        Player winner = scoresManager.getPlayersRegister().getPlayersList().get(0);
        for(Player player : scoresManager.getPlayersRegister().getPlayersList()){
            if(player.getScore() > winner.getScore()){
                winner = player;
            }else if(winner.getScore() == player.getScore()
                    && winner.getName() != player.getName()){
                winner = null;
            }
        }
        return winner;
    }

    String announce(){
        String newline = System.getProperty("line.separator");
        Player winner = getWinner();
        String message;
        if(winner!=null){
            message = "Wygrywa " + winner.getName() + " (" + winner.getMark() + ") ";
        }else{
            message = "Remis";
        }

        message += newline + scoresManager.getSubmit();
        return message;
    }


}
