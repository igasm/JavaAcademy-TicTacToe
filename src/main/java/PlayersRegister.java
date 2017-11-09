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

    public void setPlayerToBegin(int index) throws Exception{
        if(index < playersList.size()){
            for(Player player : playersList){
                player.nextTurn = false;
            }
            playersList.get(index).nextTurn = true;
        }else{
            throw new Exception("Only indexes of : 0 - " + (playersCount-1) + " are allowed");
        }
    }

    public Player getNextPlayer() throws Exception{
        if(playersList.isEmpty()){
            throw new Exception("No players added");
        }

        Player nextPlayer = null;
        for(Player player : playersList){
            if(player.nextTurn){
                nextPlayer = player;
            }
        }

        if(nextPlayer == null){
            nextPlayer = playersList.get(0);
        }

        return nextPlayer;
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
