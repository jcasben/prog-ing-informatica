package cat.uib.eps.aas.labs.estructurals.flyweight;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CardFactory cardFactory = new CardFactory();

        CardGame game = new CardGame(
                List.of(),
                null
        );

        while(!game.hasEnded()) {
            game.playTurn();
        }

    }
}
