package players;

import java.util.LinkedList;

public class PlayersQueue{

    private LinkedList<Player> queue;

    public PlayersQueue(PlayersRegister playersRegister) {
        this.queue = new LinkedList<Player>(playersRegister.getPlayersList());
    }

    public void changeQueueOrder(Player playerToSetFirst){
        if(!this.queue.getFirst().equals(playerToSetFirst)){
            Player previousFirstPlayer = this.queue.poll();
            this.queue.addLast(previousFirstPlayer);
            //in case there are more then two players in queue...
            changeQueueOrder(playerToSetFirst);
        }
    }

    public Player getNextPlayer(){
        Player nextPlayer = this.queue.poll();
        this.queue.addLast(nextPlayer);
        return nextPlayer;
    }


}
