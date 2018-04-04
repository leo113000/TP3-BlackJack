package deck;

import deck.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Deck {

    private final Stack<Card> deck;

    public Deck() {
        this.deck = generateBlackJackDeck();
    }

    public Boolean isEmpty() {
        return this.deck.isEmpty();
    }

    public void mix() {
        Collections.shuffle(this.deck);
    }

    public Card getCard() {
        return this.deck.pop();
    }

    private static Stack<Card> generateBlackJackDeck(){
        Stack<Card> deck = new Stack<>();
        ArrayList<Suit> suits = new ArrayList<>();
        suits.add(Suit.SPADES);
        suits.add(Suit.CLUBS);
        suits.add(Suit.DIAMONDS);
        suits.add(Suit.HEARTS);

        for( int j = 0 ; j<4 ; j++) {
            for (Suit s : suits) {
                for (int i = 0; i < 13; i++) {
                    deck.push(new Card(i + 1, s));
                }
            }
        }
        return deck;
    }
}
