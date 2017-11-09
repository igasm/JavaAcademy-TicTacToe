import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PlayersQueue{

    private final PlayersRegister playersRegister;
    private LinkedBlockingQueue<Player> playerPriorityQueue;

    public PlayersQueue(PlayersRegister playersRegister) {
        this.playersRegister = playersRegister;
        this.playerPriorityQueue = new LinkedBlockingQueue<Player>(playersRegister.getPlayersList());
    }

    public void changeQueueOrder(Player firstPlayer){
        if(!this.playerPriorityQueue.peek().equals(firstPlayer)){
            playerPriorityQueue.clear();

            ArrayList<Player> playersWithoutFirst = new ArrayList<Player>(playersRegister.getPlayersList());
            playersWithoutFirst.remove(firstPlayer);

            playerPriorityQueue.add(firstPlayer);
            playerPriorityQueue.addAll(playersWithoutFirst);
        }
    }

    public Player getNextPlayer(){
        Player lastPlayer = this.playerPriorityQueue.poll();
        this.playerPriorityQueue.add(lastPlayer);
        return lastPlayer;
    }



}
