import deck.Card;
import deck.Deck;

import java.util.ArrayList;
import java.util.List;

public class Mesa  {

    public final int GAMBLE_PRICE = 10;

    private List<Player> players;
    private Dealer dealer;
    private Boolean status;
    private int moneyIn;
    private int moneyOut;

    public Mesa(String dealerName){
        this.status = false;
        this.players = new ArrayList<>();
        this.dealer = new Dealer(new Deck(),dealerName);
        this.moneyIn = 0;
        this.moneyOut = 0;
    }

    public Boolean addPlayer(Player p){ return this.players.add(p);}

    public Boolean startGame(){
        Boolean result = Boolean.TRUE;
        this.dealer.mix();
        this.status = true;
        for(Player p : this.players){
            p.start();
        }
        return result;
    }

    public boolean isOpen() {
        return status;
    }

    public synchronized void play(Player player) {
        int cardsValue = 0;
        this.moneyIn += player.pay();
        // set the first two cards - the hand
        player.setHand(this.dealer.firstDeal());
        cardsValue = this.countCards(player.getHand());
        while(player.askAnotherCard(cardsValue) && cardsValue < 21 ){
            player.addCard(dealer.dealCard());
            cardsValue = this.countCards(player.getHand());
        }
        int money = 0;
        if(cardsValue < 21 ){
            money = this.GAMBLE_PRICE/2;
            player.win(money);
        }else if (cardsValue == 21){
            money = this.GAMBLE_PRICE * 2;
            player.win(money);
        }
        this.moneyOut += money;
    }

    private int countCards(List<Card> cards){
        int total = 0;

        for(Card c : cards){
            total += c.getValue();
        }

        return total;
    }
}
