package game;

import deck.Card;
import deck.Deck;
import exceptions.EmptyDeckException;
import persistance.MesaMySql;

import java.util.*;

public class Mesa implements Observer {
    // CONSTANTS
    public final int GAMBLE_PRICE = 10;
    // Attributes
    private List<Player> players;
    private Dealer dealer;
    private Boolean active;
    private Boolean available;
    private int moneyIn;
    private int moneyOut;
    private Player winner;
    private MesaMySql persistance;

    // Constructor
    public Mesa(String dealerName){
        this.active = false;
        this.available = false;
        this.players = new ArrayList<>();
        this.dealer = new Dealer(new Deck(),dealerName);
        this.moneyIn = 0;
        this.moneyOut = 0;
        // Add this as observer of the dealer
        this.dealer.addObserver(this);
        this.persistance = MesaMySql.getInstance();
    }
    // Method to add a player
    public Boolean addPlayer(Player p){ return this.players.add(p);}
    // Method which starts the simulation
    public void startGame(){
        // the dealer mix the cards
        this.dealer.mix();
        // set the active in true
        this.active = true;
        // set the available in true
        this.available = true;
        // init the game.Player's Threads
        for(Player p : this.players){
            p.start();
        }
    }
    // return true if the MESA is open
    public boolean isActive() {
        return active;
    }

    public synchronized Boolean isAvailable(){
        Boolean response = false;
        while(this.active && response == false){
            if(!this.available){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                response = true;
                available = false;
            }
        }
        return response;
    }

    /**
     * The method that contains the business's logic
     * @param player receives the player
     */
    public synchronized void play(Player player) {
        this.print("JUEGA " + player.getAlias() + "!!!!");
        // Variable to hold the cards value, to make more readable the code
        int cardsValue = 0;
        // Make the gamble and save it the money that gets into the MESA
        this.moneyIn += player.pay();
        /**
         * Try / Catch Block to handle exceptions
         */
        try {
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
        } catch (EmptyDeckException e) {
            if(this.active) {
                this.close();
            }
        }finally {
            int money = 0;
            // If the player retires
            if(cardsValue < 21 ){
                // Player talks in console, to see better the simulation
                print(player.getAlias() + ": me retiro");
                money = this.GAMBLE_PRICE/2;
                // gets the half of the gable
                player.win(money);
                // if the player win
            }else if (cardsValue == 21){
                // Player talks in console, to see better the simulation
                print(player.getAlias() + ": BLACKJACK PAPU");
                // gets the double of the gamble
                money = this.GAMBLE_PRICE * 2;
                player.win(money);
            }else{
                // Player talks in console, to see better the simulation
                print(player.getAlias() + ": uh me pasé");
            }
            // save the money that left the MESA
            this.moneyOut += money;
            // notify the other players
            notifyAll();
        }
        // If the MESA still opens
        if (this.active) {
            // Turn it available
            this.available = true;
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        /**
         * If the argument is a card, shows the card.
         * else, if the arg is null, close the MESA
         */
        if(arg instanceof Card){
          Card c = (Card)arg;
          print("Carta: " + c);
        }else if(arg == null){
            this.print("Se han acabado las cartas");
            this.close();
        }
    }

    /**
     * Method to manage the console's output
     * @param s
     */
    private synchronized void print(String s){
        if(this.active){
            System.out.println(s);
        }
    }

    /**
     * Final method to be executed, ends the current match at the table, and shows relevant data
     */
    private void close() {
        this.active = false;
        this.available = false;
        this.calculateWinner();
        persistance.saveMatch(this);
        this.printLastMatchData();
    }

    private void printLastMatchData() {
        Map dict = this.persistance.getLastMatch();
        if (dict != null && !dict.isEmpty()){
            System.out.println("ID de Partida" + dict.get("id"));
            System.out.println("Ganador: " + dict.get("winner_name"));
            System.out.println("Dinero total del ganador: " + dict.get("winner_money"));
            System.out.println("Ganancias de la mesa: " + dict.get("earn_by_the_table"));
            System.out.println("perdidas de la mesa: " + dict.get("lost_by_the_table"));
        }
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

    private void calculateWinner(){
        Player winner = null;
        // Iterate over the players
        for(Player p : players){
            // if the player didn't lose
            if(p.getMoney()>0){
                // if not exists a reference value
                if(winner == null){
                    // Take it as a reference
                    winner = p;
                // else, compare with the reference
                }else if(winner.getMoney() < p.getMoney()){
                    winner = p;
                }
            }
        }
        // set the object's member attribute
        this.winner = winner;
    }

    public String getWinnerName() {
        return this.winner != null ? this.winner.getAlias() : "no hay ganador";
    }

    public int getWinnerMoney(){
        return this.winner != null ? this.winner.getMoney() : 0;
    }

    public int getMoneyIn() {
        return this.moneyIn;
    }

    public int getMoneyOut() {
        return this.moneyOut;
    }
}
