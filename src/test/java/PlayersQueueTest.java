import org.testng.annotations.Test;
import players.Player;
import players.PlayersQueue;
import players.PlayersRegister;

import static org.testng.Assert.*;

public class PlayersQueueTest {

    @Test
    public void testChangeQueueOrder() throws Exception{
        PlayersRegister playersRegister = new PlayersRegister(2);
        Player playerA = new Player("A", "x");
        Player playerB = new Player("B", "o");
        playersRegister.registerPlayer(playerA);
        playersRegister.registerPlayer(playerB);
        PlayersQueue playersQueue = new PlayersQueue(playersRegister);
        playersQueue.changeQueueOrder(playerB);
        assertEquals(playersQueue.getNextPlayer(), playerB);
        assertEquals(playersQueue.getNextPlayer(), playerA);
        assertEquals(playersQueue.getNextPlayer(), playerB);
        assertEquals(playersQueue.getNextPlayer(), playerA);
    }

}