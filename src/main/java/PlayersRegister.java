import java.util.ArrayList;
import java.util.List;

public class PlayersRegister {
    private ArrayList<Player> playersList;
    private final int playersCount;

    PlayersRegister(int count){
        this.playersCount = count;
        playersList = new ArrayList<Player>(count);
    }

    public void registerPlayer(Player player) throws Exception{
        if(playersList.size() >= playersCount){
            throw new Exception("only " + playersCount + " players are allowed");
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
