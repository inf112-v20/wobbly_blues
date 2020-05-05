package classes;

import com.badlogic.gdx.math.*;
import enums.*;

public class AIPlayer extends Robot {

    public AIPlayer(Vector2 pos, RobotNames name) {
        super(pos, name);
    }

    /**
     * Selects random cards from the hand
     */

    public void doTurn() {
        while (cardsChosen.size() < numbRegister) {
            int rand = (int) (Math.random() * numbCards);
            while(cardsChosen.contains(hand.get(rand))) {
                rand = (int) (Math.random() * numbCards);
            }
            selectCard(rand);
        }
        ready = true;
    }


}
