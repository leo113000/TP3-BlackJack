import deck.*;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    // Attributes
    private final Deck deck;
    private final String name;
    // Constructor
    public Dealer(Deck deck, String name) {
        this.deck = deck;
        this.name = name;
    }
    // Methods
    public String getName(){ return this.name;}
    public Boolean emptyDeck(){ return this.deck.isEmpty(); }
    public void mix(){ this.deck.mix(); }
    public Card dealCard(){ return this.deck.getCard(); }
    // Method to deal two cards, for the initial hand
    public List<Card> firstDeal(){
        List<Card> cards = new ArrayList<>();
        for( int i = 0 ; i < 2 ; i++ ){
            cards.add(this.dealCard());
        }
        return cards;
    }
}
