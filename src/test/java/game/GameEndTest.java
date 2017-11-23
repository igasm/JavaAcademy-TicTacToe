package game;

import org.testng.annotations.Test;
import players.Player;
import players.PlayersRegister;

import static org.testng.Assert.*;

public class GameEndTest {

    @Test
    public void whenPlayerAHas3PointsAndPlayerBHas3Points_thenGetWinnerReturnsNull(){
        PlayersRegister playersRegister = new PlayersRegister(2);
        Player playerA = new Player("John", MarkType.NAUGHT);
        playerA.addPoints(3);
        Player playerB = new Player("Mark", MarkType.CROSS);
        playerB.addPoints(3);
        playersRegister.registerPlayer(playerA);
        playersRegister.registerPlayer(playerB);
        ScoresManager scoresManager = new ScoresManager(playersRegister);
        GameEnd gameEnd = new GameEnd(scoresManager);
        assertEquals(gameEnd.getWinner(), null);
    }

    @Test
    public void whenPlayerAHas3PointsAndPlayerBHas0Points_thenGetWinnerReturnsPlayerA(){
        PlayersRegister playersRegister = new PlayersRegister(2);
        Player playerA = new Player("John", MarkType.NAUGHT);
        playerA.addPoints(3);
        Player playerB = new Player("Mark", MarkType.CROSS);
        playerB.addPoints(0);
        playersRegister.registerPlayer(playerA);
        playersRegister.registerPlayer(playerB);
        ScoresManager scoresManager = new ScoresManager(playersRegister);
        GameEnd gameEnd = new GameEnd(scoresManager);
        assertEquals(gameEnd.getWinner(), playerA);
    }

}