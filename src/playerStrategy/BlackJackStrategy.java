package playerStrategy;

import java.util.ArrayList;
import deck.Card;


public abstract class BlackJackStrategy {

    public final int GAMBLE = 10;
    abstract Boolean askAnotherCard(ArrayList<Card> hand);

    protected int countCards(ArrayList<Card> cards){
        int total = 0;

        for(Card c : cards){
            total += c.getValue();
        }

        return total;
    }

}
