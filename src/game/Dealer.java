package game;

import deck.*;
import exceptions.EmptyDeckException;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


public class Dealer extends Observable {
    // Attributes
    private final Deck deck;
    private final String name;
    // Constructor
    public Dealer(Deck deck, String name) {
        this.name = name;
        this.deck = deck;
    }
    // Methods
    public String getName(){ return this.name;}
    public void mix(){ this.deck.mix(); }

    /**
     * This method get a card from the deck and notify the observers
     * @return the Card
     * @throws EmptyDeckException
     */
    public Card dealCard() throws EmptyDeckException {
        // get the card
        Card c = this.deck.getCard();
        // Set the the change in the obj and notify
        setChanged();
        notifyObservers(c);
        if(this.deck.isEmpty()){
            setChanged();
            notifyObservers();
        }
        return c;
    }
    // Method to deal two cards, for the initial hand
    public List<Card> firstDeal() throws EmptyDeckException {
        List<Card> cards = new ArrayList<>();
        for( int i = 0 ; i < 2 ; i++ ){
            cards.add(this.dealCard());
        }
        return cards;
    }
}
