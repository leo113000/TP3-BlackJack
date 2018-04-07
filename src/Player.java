import deck.Card;
import playerStrategy.*;

import java.util.ArrayList;
import java.util.List;

public class Player extends  Thread{

    private String name;
    private int money;
    private List<Card> hand;
    private BlackJackStrategy bjs;
    private Mesa mesa;

    public Player(String name,int money, BlackJackStrategy bjs, Mesa m) {
        this.name = name;
        this.money = money;
        this.bjs = bjs;
        this.mesa = m;
        this.hand = new ArrayList<>();
    }

    @Override
    public void run() {

        // wait random time to start playing

        while(this.money >= this.mesa.GAMBLE_PRICE && this.mesa.isOpen()){
            this.mesa.play(this);
        }

    }

    public int pay() {
        // Variable to be returned
        int payment = 0;

        this.money -= this.mesa.GAMBLE_PRICE;
        this.money = this.money<0 ? 0 : this.money;
        return payment;
    }

    public void win(int money){
        this.money += money;
    }


    public void setHand(List<Card> cards){ this.hand = cards; }

    public void addCard(Card c){ this.hand.add(c); }

    public Boolean askAnotherCard(int valueOfCards) {
        return this.bjs.askAnotherCard(valueOfCards);
    }

    public List<Card> getHand() {
        return  this.hand;
    }
}
