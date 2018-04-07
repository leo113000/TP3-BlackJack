package playerStrategy;

import java.util.ArrayList;
import deck.Card;

public class BlackJackStrategyModerate extends BlackJackStrategy{
    @Override
    public Boolean askAnotherCard(int cardsValue) { return cardsValue<15; }
}
