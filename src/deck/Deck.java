package deck;

import deck.Suit;
import exceptions.EmptyDeckException;

import java.util.*;

public class Deck {
    // Collection attribute
    private final Stack<Card> deck;
    // Constructor
    public Deck() {
        this.deck = generateBlackJackDeck();
    }
    //Methods
    public Boolean isEmpty() {
        return this.deck.isEmpty();
    }
    public void mix() {
        Collections.shuffle(this.deck);
    }
    // Return a card or a custom exception
    public Card getCard() throws EmptyDeckException {
        Card c;
        try {
            c = this.deck.pop();
        } catch (EmptyStackException e) {
            throw new EmptyDeckException("no hay más cartas");
        }
        return c;
    }

    /**
     * Static method to build the deck according to blackjack
     * @return a 52 cards deck
     */
    private static Stack<Card> generateBlackJackDeck(){
        Stack<Card> deck = new Stack<>();
        ArrayList<Suit> suits = new ArrayList<>();
        suits.add(Suit.SPADES);
        suits.add(Suit.CLUBS);
        suits.add(Suit.DIAMONDS);
        suits.add(Suit.HEARTS);

        for( int j = 0 ; j<4 ; j++) {
            for (Suit s : suits) {
                for (int i = 0; i < 12; i++) {
                    deck.push(new Card(i + 1, s));
                }
            }
        }
        return deck;
    }
}
