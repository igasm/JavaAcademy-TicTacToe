package game;

import game.Arbiter;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ArbiterTest {

    @Test
    public void horizontalRowOfCrosses_CrossesWins(){
        //given
        String sequenceString = "xxx";
        Arbiter arbiter = new Arbiter(3);
        //when - then
        assertTrue(arbiter.isWin("x", sequenceString));
    }

    @Test
    public void horizontalRowOfNaughtsAndCrosses_NoCrossesWin(){
        //given
        String sequenceString = "xox";
        Arbiter arbiter = new Arbiter(3);
        //when - then
        assertTrue(!arbiter.isWin("x", sequenceString));
    }

    @Test
    public void givenSequenceOfNaughts_NaughtWin(){
        //given
        String sequenceString = "ooo";
        Arbiter arbiter = new Arbiter(3);
        //when - then
        assertTrue(arbiter.isWin("o", sequenceString));
    }
}
