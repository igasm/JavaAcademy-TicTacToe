import org.testng.annotations.Test;
import players.Player;
import players.PlayersRegister;

import static org.testng.Assert.*;

public class PlayersRegisterTest {

    @Test(expectedExceptions = Exception.class)
    public void whenAddingMorePlayersThenInPlayersRegisterConstructorThrowsException() throws Exception{
        PlayersRegister playersRegister = new PlayersRegister(2);
        playersRegister.registerPlayer(new Player("A", "x"));
        playersRegister.registerPlayer(new Player("B", "o"));
        playersRegister.registerPlayer(new Player("C", "x"));
    }

    @Test
    public void whenAddingSamePlayersCountAsInPlayersRegisterConstructorThenIsOk(){
        PlayersRegister playersRegister = new PlayersRegister(2);
        try{
            playersRegister.registerPlayer(new Player("A", "x"));
            playersRegister.registerPlayer(new Player("B", "o"));
        }catch (Exception e){
            fail(); //should pass without exception
        }
    }

    @Test
    public void testGetPlayerIndex() throws Exception{
        PlayersRegister playersRegister = new PlayersRegister(2);
        Player playerA = new Player("A", "x");
        Player playerB = new Player("B", "o");
        playersRegister.registerPlayer(playerA);
        playersRegister.registerPlayer(playerB);
        assertEquals(playersRegister.getPlayerIndex(playerA), 0);
        assertEquals(playersRegister.getPlayerIndex(playerB), 1);
    }
    
}