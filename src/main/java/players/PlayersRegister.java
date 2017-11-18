package players;

import players.Player;

import java.util.ArrayList;

public class PlayersRegister {
    private ArrayList<Player> playersList;
    private final int playersCount;

    public PlayersRegister(int count){
        this.playersCount = count;
        playersList = new ArrayList<Player>(count);
    }

    public void registerPlayer(Player player){
        if(playersList.size() >= playersCount){
            throw new RuntimeException("only " + playersCount + " players are allowed");
        }else{
            playersList.add(player);
        }
    }

    public ArrayList<Player> getPlayersList(){
        return playersList;
    }

    @Override
    public String toString(){
        String body = "";
        for(int i=0; i<playersList.size(); i++){
            body += i + ") " + playersList.get(i) + "\n";
        }
        return body;
    }


    public int getPlayerIndex(Player player){
        int playerIndex = -1;
        if(playersList.contains(player)){
            for(int i=0; i<playersList.size(); i++){
                if (playersList.get(i).equals(player)){
                    playerIndex = i;
                }
            }
        }
        return playerIndex;
    }

}
