package game.players;

import game.engine.MarkType;

import java.util.ArrayList;

public class PlayersRegister {
    private ArrayList<Player> playersList;
    private final int playersCount;

    public PlayersRegister(int count){
        this.playersCount = count;
        playersList = new ArrayList<>(count);
    }

    public boolean registerPlayer(Player player){
        boolean registered = false;
        if(playersList.size() >= playersCount){
            throw new RuntimeException("only " + playersCount + " game.players are allowed");
        }else{
            playersList.add(player);
            registered = true;
        }
        return registered;
    }

    public ArrayList<Player> getPlayersList(){
        return playersList;
    }

    @Override
    public String toString(){
        String newline = System.getProperty("line.separator");
        StringBuilder stringBuilder = new StringBuilder();
        for(Player player : playersList){
            stringBuilder.append(player.toString()).append(newline);
        }
        return stringBuilder.toString();
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

    public Player getPlayerByMark(MarkType markType){
        Player player = null;
        for(Player player1 : playersList){
            if(player1.getMark().equals(markType)){
                player = player1;
            }
        }

        return player;
    }

}
