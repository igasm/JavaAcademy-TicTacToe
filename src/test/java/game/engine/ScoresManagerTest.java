package game.engine;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import game.players.InvalidPlayerNameException;
import game.players.Player;
import game.players.PlayersRegister;

import static org.testng.Assert.*;

public class ScoresManagerTest {

    ScoresManager scoresManager;
    Player playerA;
    Player playerB;

    @BeforeMethod
    public void beforeMethod() throws InvalidPlayerNameException {
        //given
        playerA = new Player("Adam", MarkType.CROSS);
        playerB = new Player("Beata", MarkType.NAUGHT);
        PlayersRegister playersRegister = new PlayersRegister(2);
        playersRegister.registerPlayer(playerA);
        playersRegister.registerPlayer(playerB);
        scoresManager = new ScoresManager(playersRegister);
    }

    @Test
    public void playerAWins_playerAHas3Points(){
        //when
        scoresManager.addWin(playerA);
        //then
        assertEquals(playerA.getScore(), 3);
    }

    @Test
    public void gameEndWithDraw_playerAHas1Point_and_playerBHas1Point(){
        //when
        scoresManager.addDraw();
        //then
        assertEquals(playerA.getScore(), 1);
        assertEquals(playerB.getScore(), 1);
    }

    @Test
    public void whenNoMatchPlayed_playersHave0Points(){
        //when-then
        assertEquals(playerA.getScore(), 0);
        assertEquals(playerB.getScore(), 0);
    }
}