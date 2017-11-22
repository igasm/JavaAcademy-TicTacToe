package players;

import game.MarkType;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PlayerTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenAddingPlayerWithOnlyWhitespacesInName_errorExpected(){
        Player player = new Player("     ", MarkType.CROSS);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenAddingPlayerWithEmptyName_errorExpected(){
        Player player = new Player("", MarkType.CROSS);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenAddingPlayerWithSpecialCharacters_errorExpected(){
        Player player = new Player("iga?*_", MarkType.CROSS);
    }

    @Test
    public void whenCreatingNewPlayer_getScoreReturns0(){
        Player player = new Player("iga77", MarkType.CROSS);
        assertEquals(player.getScore(), 0);
    }

    @Test
    public void whenCreatingPlayerWithNameJohn_getNameReturnsJohn(){
        Player player = new Player("John", MarkType.CROSS);
        assertEquals(player.getName(), "John");
    }

    @Test
    public void givenNewPlayer_whenAdding3Points_getScoreReturns3(){
        Player player = new Player("John", MarkType.CROSS);
        player.addPoints(3);
        assertEquals(player.getScore(), 3);
    }


}