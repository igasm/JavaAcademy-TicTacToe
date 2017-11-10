import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ArbiterTest {

    @Test
    public void horizontalRowOfNaughts_NaughtWins(){
        //given
        String plansza = "XXX";
        Arbiter arbiter = new Arbiter();
        //when - then
        assertTrue(arbiter.isXWins(plansza));
    }
}
