package game;

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
        // Sleep threads at the beginning to make it more random who starts
        this.waitSeconds(2);
        // If the player has money enough and the MESA is available
        while(this.money >= this.mesa.GAMBLE_PRICE && this.mesa.isAvailable()){
            // Play!!
            this.mesa.play(this);
            // Waiting between games
            this.waitSeconds(2);
        }
        if(this.money<this.mesa.GAMBLE_PRICE) {
            System.out.println(this.alias + ": Me he quedado seco " + this.money);
        }
    }

    public void waitSeconds(int seconds){
        try {
            sleep(ThreadLocalRandom.current().nextInt(0,seconds*1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
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
        // return the payment
        return this.mesa.GAMBLE_PRICE;
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
