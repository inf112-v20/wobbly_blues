package run;

import classes.Card;
import interfaces.ICard;
import org.junit.Test;
import org.junit.runner.RunWith;
import testrunner.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class TestCard {

    @Test
    public void testCard(){
        ICard card = new Card();
    }
}
