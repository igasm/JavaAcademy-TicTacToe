package game.engine;

import game.players.Player;
import game.players.PlayersRegister;

public class ScoresManager {
    private final PlayersRegister playersRegister;

    public ScoresManager(PlayersRegister playersRegister) {
        this.playersRegister = playersRegister;
    }

    public void addWin(Player player){
        player.addPoints(3);
    }

    public void addDraw(){
        for(Player player : playersRegister.getPlayersList()){
            player.addPoints(1);
        }
    }

    public String getSubmit(){
        String newline = System.getProperty("line.separator");
        StringBuilder stringBuilder = new StringBuilder();
        for(Player player : playersRegister.getPlayersList()){
            stringBuilder.append(player.getName()).append(": ").append(player.getScore()).append(newline);
        }
        return stringBuilder.toString();
    }

    PlayersRegister getPlayersRegister(){
        return playersRegister;
    }

}
