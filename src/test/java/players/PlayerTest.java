package players;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PlayerTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenAddingPlayerWithOnlyWhitespacesInName_errorExpected(){
        Player player = new Player("     ", "x");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenAddingPlayerWithEmptyName_errorExpected(){
        Player player = new Player("", "x");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenAddingPlayerWithSpecialCharacters_errorExpected(){
        Player player = new Player("iga?*_", "x");
    }

    @Test
    public void whenCreatingNewPlayer_getScoreReturns0(){
        Player player = new Player("iga77", "x");
        assertEquals(player.getScore(), 0);
    }

    @Test
    public void whenCreatingPlayerWithNameJohn_getNameReturnsJohn(){
        Player player = new Player("John", "x");
        assertEquals(player.getName(), "John");
    }

    @Test
    public void givenNewPlayer_whenAdding3Points_getScoreReturns3(){
        Player player = new Player("John", "x");
        player.addPoints(3);
        assertEquals(player.getScore(), 3);
    }


}