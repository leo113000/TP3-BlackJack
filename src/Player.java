import deck.Card;
import playerStrategy.*;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.ThreadLocalRandom;

public class Player extends  Thread{
    // Attributes
    private String alias; // this attribute is called alias and not name, to avoid conflicts with Thread.
    private int money;
    private List<Card> hand;
    private BlackJackStrategy bjs;
    private Mesa mesa;
    // Constructor
    public Player(String alias, int money, BlackJackStrategy bjs, Mesa m) {
        this.alias = alias;
        this.money = money;
        this.bjs = bjs;
        this.mesa = m;
        this.hand = new ArrayList<>();
    }

    @Override
    public void run() {
        // Sleep threads at the beginning to make it more random
        try {
            this.sleep(ThreadLocalRandom.current().nextInt(1000,3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // If the player has money and the MESA is open
        while(this.money >= this.mesa.GAMBLE_PRICE && this.mesa.isOpen()){
            // Play!!
            this.mesa.play(this);
        }
    }

    /**
     *  This method will be called by the MESA at the moment of playing
     * @return
     */
    public int pay() {
        // Variable to be returned
        int payment = 0;
        // Discount the money from the player
        this.money -= this.mesa.GAMBLE_PRICE;
        // Checking negative
        this.money = this.money<0 ? 0 : this.money;
        return payment;
    }

    /**
     * This method will be called by the MESA at the moment of win some money
     * @param money
     */
    public void win(int money){
        this.money += money;
    }

    // Set the hand, specially useful at the first dealing of the game
    public void setHand(List<Card> cards){ this.hand = cards; }
    // Add a card to the player's hand
    public void addCard(Card c){ this.hand.add(c); }

    /**
     * This method will be called by the MESA. This calls the strategy object
     * @param valueOfCards The total value of the hand, calculated by the MESA
     * @return True if the player keeps playing or false if stop asking cards
     */
    public Boolean askAnotherCard(int valueOfCards) {
        return this.bjs.askAnotherCard(valueOfCards);
    }
    // GETTERS
    public String getAlias() { return this.alias; }
    public int getMoney() { return this.money; }
    public List<Card> getHand() { return  this.hand; }
}
