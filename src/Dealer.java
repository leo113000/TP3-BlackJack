import deck.*;

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
}
