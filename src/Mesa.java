import deck.Card;
import deck.Deck;

import java.util.ArrayList;
import java.util.List;

public class Mesa  {
    // CONSTANTS
    public final int GAMBLE_PRICE = 10;
    // Attributes
    private List<Player> players;
    private Dealer dealer;
    private Boolean status;
    private int moneyIn;
    private int moneyOut;
    // Constructor
    public Mesa(String dealerName){
        this.status = false;
        this.players = new ArrayList<>();
        this.dealer = new Dealer(new Deck(),dealerName);
        this.moneyIn = 0;
        this.moneyOut = 0;
    }
    // Method to add a player
    public Boolean addPlayer(Player p){ return this.players.add(p);}
    // Method which starts the simulation
    public void startGame(){
        // the dealer mix the cards
        this.dealer.mix();
        // set the status in true, the MESA is open
        this.status = true;
        // init the Player's Threads
        for(Player p : this.players){
            p.start();
        }
    }
    // return true if the MESA is available
    public boolean isOpen() {
        return status;
    }

    /**
     * The method that contains the business's logic
     * @param player receives the player
     */
    public synchronized void play(Player player) {
        // Variable to hold the cards value, to make more readable the code
        int cardsValue = 0;
        // Make the gamble and save it the money that gets into the MESA
        this.moneyIn += player.pay();
        // Set the first two cards - the hand
        player.setHand(this.dealer.firstDeal());
        // Count the value of the cards
        cardsValue = this.countCards(player.getHand());
        // While the cards total is less than 21 and the player wants to keep playing
        while( cardsValue < 21 && player.askAnotherCard(cardsValue) ){
            // Deal a card to the hand of the player
            player.addCard(dealer.dealCard());
            // Count again the value of the cards
            cardsValue = this.countCards(player.getHand());
        }
        int money = 0;
        // If the player retires
        if(cardsValue < 21 ){
            money = this.GAMBLE_PRICE/2;
            // gets the half of the gable
            player.win(money);
        // if the player win
        }else if (cardsValue == 21){
            // gets the double of the gamble
            money = this.GAMBLE_PRICE * 2;
            player.win(money);
        }
        // save the money that left the MESA
        this.moneyOut += money;
    }
    // Method to count the value of the hand
    private int countCards(List<Card> cards){
        // Variable to be returned
        int total = 0;
        // get the value for each card
        for(Card c : cards){
            total += c.getValue();
        }
        // return total
        return total;
    }
}
