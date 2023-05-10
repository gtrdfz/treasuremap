package tests;

import org.carbonit.box.Adventurer;
import org.carbonit.box.EOrientation;
import org.carbonit.box.Mountain;
import org.carbonit.box.Position;
import org.carbonit.game.Game;
import org.junit.jupiter.api.Test;

public class GameTest {


    @Test
    public void gameTest() throws Exception {
        Game game = new Game(5, 5);
        game.addSpecialBox(new Mountain(new Position(10, 1)));
        game.addSpecialBox(new Mountain(new Position(1, 1)));
        game.addAdventurer(new Adventurer(new Position(1, 2), "milan", EOrientation.S, "gagda"));

        game.start();

    }

}
