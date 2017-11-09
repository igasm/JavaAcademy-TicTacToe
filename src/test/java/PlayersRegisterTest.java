import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PlayersRegisterTest {

    @Test(expectedExceptions = Exception.class)
    public void whenAddingMorePlayersThenInPlayersRegisterConstructorThrowsException() throws Exception{
        PlayersRegister playersRegister = new PlayersRegister(2);
        playersRegister.registerPlayer(new Player("A"));
        playersRegister.registerPlayer(new Player("B"));
        playersRegister.registerPlayer(new Player("C"));
    }

    @Test
    public void whenAddingSamePlayersCountAsInPlayersRegisterConstructorThenIsOk(){
        PlayersRegister playersRegister = new PlayersRegister(2);
        try{
            playersRegister.registerPlayer(new Player("A"));
            playersRegister.registerPlayer(new Player("B"));
        }catch (Exception e){
            fail(); //should pass without exception
        }
    }

    @Test
    public void testGetPlayerIndex() throws Exception{
        PlayersRegister playersRegister = new PlayersRegister(2);
        Player playerA = new Player("A");
        Player playerB = new Player("B");
        playersRegister.registerPlayer(playerA);
        playersRegister.registerPlayer(playerB);
        assertEquals(playersRegister.getPlayerIndex(playerA), 0);
        assertEquals(playersRegister.getPlayerIndex(playerB), 1);
    }

    @Test
    public void whenNoNextPlayerWasSetThenNextPlayerShouldBeFirstAdded() throws Exception{
        PlayersRegister playersRegister = new PlayersRegister(2);
        Player playerA = new Player("A");
        Player playerB = new Player("B");
        playersRegister.registerPlayer(playerA);
        playersRegister.registerPlayer(playerB);
        assertEquals(playerA, playersRegister.getNextPlayer());
    }

    @Test
    public void whenNextPlayerWasSetPlayerBThenNextPlayerShouldBePlayerB() throws Exception{
        PlayersRegister playersRegister = new PlayersRegister(2);
        Player playerA = new Player("A");
        Player playerB = new Player("B");
        playersRegister.registerPlayer(playerA);
        playersRegister.registerPlayer(playerB);
        int playerBIndex = playersRegister.getPlayerIndex(playerB);
        playersRegister.setPlayerToBegin(playerBIndex);
        assertEquals(playerB, playersRegister.getNextPlayer());
    }
}