package tests;

import org.carbonit.box.*;
import org.carbonit.game.Game;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    public void gameTest() throws Exception {
        // arrange
        Game game = new Game(5, 5);
        game.addSpecialBox(new Mountain(new Position(10, 1)));
        game.addSpecialBox(new Mountain(new Position(1, 1)));
        game.addSpecialBox(new Mountain(new Position(2, 1)));
        game.addSpecialBox(new Treasure(new Position(1, 2), 1));
        game.addSpecialBox(new Treasure(new Position(2, 2), 2));
        game.addAdventurer(new Adventurer(new Position(3, 1), "milan", EOrientation.O, "agadaadda"));

        // act
        System.out.println(game.print());
        game.start();
        System.out.println(game.print());

        // assert

        int a = 5;

    }

}
