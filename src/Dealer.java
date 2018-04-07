import deck.*;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private final Deck deck;
    private final String name;

    public Dealer(Deck deck, String name) {
        this.deck = deck;
        this.name = name;
    }

    public String getName(){ return this.name;}

    public Boolean emptyDeck(){ return this.deck.isEmpty(); }

    public void mix(){ this.deck.mix(); }

    public Card dealCard(){ return this.deck.getCard(); }

    public List<Card> firstDeal(){
        List<Card> cards = new ArrayList<>();
        for( int i = 0 ; i < 2 ; i++ ){
            cards.add(this.dealCard());
        }
        return cards;
    }

}
