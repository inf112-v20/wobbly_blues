package interfaces;

import java.util.List;

public interface ITurnHandler {

    /**
     * Adds a card to the robots hand from the "card pile".
     */
    void addCard();

    /**
     * End of programing phase and marks the player ready.
     */
    void isReady();

    /**
     * @param cards A list of played cards.
     * Sorts the cards on the board in order of their priority.
     * Returns the sorted list.
     */
    List<IRobot> sortByPriority(List<IRobot>cards);

    /**
     * Does the turn.
     */
    void doTurn();
}
