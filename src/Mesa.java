import com.sun.org.apache.xpath.internal.operations.Bool;
import deck.Deck;

import java.util.ArrayList;
import java.util.List;

public class Mesa  {

    private List<Player> players;
    private Dealer dealer;
    private Boolean status;
    private int earnings;

    public Mesa(String dealerName){
        this.status = false;
        this.players = new ArrayList<>();
        this.dealer = new Dealer(new Deck(),dealerName);
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

    }
}
