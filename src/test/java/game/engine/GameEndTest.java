package game.engine;

import org.testng.annotations.Test;
import game.players.InvalidPlayerNameException;
import game.players.Player;
import game.players.PlayersRegister;

import static org.testng.Assert.*;

public class GameEndTest {

    @Test
    public void whenPlayerAHas3PointsAndPlayerBHas3Points_thenGetWinnerReturnsNull() throws InvalidPlayerNameException {
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
    public void whenPlayerAHas3PointsAndPlayerBHas0Points_thenGetWinnerReturnsPlayerA() throws InvalidPlayerNameException {
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