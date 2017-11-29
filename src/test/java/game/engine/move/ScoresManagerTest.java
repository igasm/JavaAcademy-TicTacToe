package game.engine.move;

import game.engine.MarkType;
import game.engine.ScoresManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import game.players.InvalidPlayerNameException;
import game.players.Player;
import game.players.PlayersRegister;

import static org.testng.Assert.*;

public class ScoresManagerTest {

    private ScoresManager scoresManager;
    private Player playerA;
    private Player playerB;
    private String newline = System.getProperty("line.separator");

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

    @Test
    public void playerAHas3Points_playerBHas0Points_submitTest(){
        //when
        scoresManager.addWin(playerA);
        //then
        assertEquals(scoresManager.getSubmit().trim(), "Adam: 3" + newline + "Beata: 0" );
    }
}