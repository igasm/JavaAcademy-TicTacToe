package players;

import game.MarkType;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PlayersQueueTest {

    @Test
    public void testChangeQueueOrder() throws Exception{
        PlayersRegister playersRegister = new PlayersRegister(2);
        Player playerA = new Player("A", MarkType.CROSS);
        Player playerB = new Player("B", MarkType.CROSS);
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