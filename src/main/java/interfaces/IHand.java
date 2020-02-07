package interfaces;

import java.util.List;

public interface IHand {
    /**
     * HUSKER IKKE
     * @param cards A list of cards in hand.
     */
    void holdCards(List<ICard> cards);

    /**
     * Removes cards from hand.
     */
    void removeCards();

}
