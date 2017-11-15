import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;

import static org.testng.Assert.*;

public class ScoresManagerTest {

    ScoresManager scoresManager;
    Player playerA;
    Player playerB;

    @BeforeMethod
    public void beforeMethod(){
        //given
        playerA = new Player("Adam","x");
        playerB = new Player("Beata", "o");
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
}