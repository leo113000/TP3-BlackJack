package game;

import persistance.MesaMySql;
import playerStrategy.BlackJackStrategy;
import playerStrategy.BlackJackStrategyAggresive;
import playerStrategy.BlackJackStrategyConservative;
import playerStrategy.BlackJackStrategyModerate;

public class Main {

    public static void main(String[] args) {

        MesaMySql a = MesaMySql.getInstance();
        Mesa table = new game.Mesa("Jorge UTN");
        table.addPlayer(new game.Player("Leo", 1000, new BlackJackStrategyModerate(),table));
        table.addPlayer(new game.Player("Sauco", 1000, new BlackJackStrategyAggresive(),table));
        table.addPlayer(new game.Player("Mozo", 1000, new BlackJackStrategyConservative(),table));
        table.startGame();
    }
}
