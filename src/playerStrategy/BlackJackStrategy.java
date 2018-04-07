package playerStrategy;

import java.util.ArrayList;
import deck.Card;


public abstract class BlackJackStrategy {
    /**
     *  This method is the abstract strategy to be implemented
     * @param cardsValue the value of the cards
     * @return
     */
    public abstract Boolean askAnotherCard(int cardsValue);
}
