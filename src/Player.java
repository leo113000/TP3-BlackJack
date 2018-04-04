import deck.Card;
import playerStrategy.*;

import java.util.ArrayList;

public class Player extends  Thread{

    private String name;
    private int money;
    private ArrayList<Card> hand;
    private BlackJackStrategy bjs;
    private Mesa mesa;

    public Player(String name,int money, BlackJackStrategy bjs, Mesa m) {
        this.name = name;
        this.money = money;
        this.bjs = bjs;
        this.mesa = m;
    }

    @Override
    public void run() {

        while(this.gamble()!= 0 && this.mesa.isOpen()){
            this.mesa.play(this);
        }


    }

    public int gamble() {
        // Variable to be returned
        int gamble = 0;
        // Save the subtraction between the player's money and a gamble according the strategy
        int subtraction = this.money - bjs.GAMBLE;
        // If the subtraction's result is positive or zero
        if( subtraction >= 0)
        {
            // Save the current money
            this.money = subtraction;
            // Assign the gamble value to be returned
            gamble = bjs.GAMBLE;
        }
        return gamble;
    }
}
