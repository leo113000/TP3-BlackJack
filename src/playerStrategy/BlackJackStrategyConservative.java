package playerStrategy;

import java.util.ArrayList;
import deck.Card;

public class BlackJackStrategyConservative extends BlackJackStrategy {
    @Override
    public Boolean askAnotherCard(ArrayList<Card> hand) { return this.countCards(hand) < 11; }
}
