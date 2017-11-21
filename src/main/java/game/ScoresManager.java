package game;

import players.Player;
import players.PlayersRegister;

class ScoresManager {
    private final PlayersRegister playersRegister;

    ScoresManager(PlayersRegister playersRegister) {
        this.playersRegister = playersRegister;
    }

    void addWin(Player player){
        player.addPoints(3);
    }

    void addDraw(){
        for(Player player : playersRegister.getPlayersList()){
            player.addPoints(1);
        }
    }

    String getSubmit(){
        String newline = System.getProperty("line.separator");
        StringBuilder stringBuilder = new StringBuilder();
        for(Player player : playersRegister.getPlayersList()){
            stringBuilder.append(player.getName() + ": " + player.getScore() + newline);
        }
        return stringBuilder.toString();
    }
}
