package playerStrategy;

import java.util.ArrayList;
import deck.Card;


public abstract class BlackJackStrategy {


    public abstract Boolean askAnotherCard(int cardsValue);

    /*
    protected int countCards(ArrayList<Card> cards){
        int total = 0;

        for(Card c : cards){
            total += c.getValue();
        }

        return total;
    }
    */
}
