package tests;

import org.carbonit.box.*;
import org.carbonit.game.Game;
import org.junit.jupiter.api.Assertions;
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
        int a = 5;

    }

    @Test
    public void gameNotConflictTest() throws Exception {
        // arrange
        Game game = new Game(3, 3);
        game.addAdventurer(new Adventurer(new Position(0, 0), "1n", EOrientation.E, "a"));
        game.addAdventurer(new Adventurer(new Position(1, 0), "2n", EOrientation.S, "a"));
        game.addAdventurer(new Adventurer(new Position(1, 1), "3n", EOrientation.O, "a"));
        game.addAdventurer(new Adventurer(new Position(0, 1), "4n", EOrientation.N, "a"));

        // act
        System.out.println(game.print());
        game.start();
        System.out.println(game.print());

        // assert
        String res = "C - 3 - 3\n" +
                "A - 1n - 1 - 0 - E - 0\n" +
                "A - 2n - 1 - 1 - S - 0\n" +
                "A - 3n - 0 - 1 - O - 0\n" +
                "A - 4n - 0 - 0 - N - 0\n";
        Assertions.assertEquals(res, game.print());
    }

    @Test
    public void gameConflictTest() throws Exception {
        // arrange
        Game game = new Game(3, 3);
        game.addAdventurer(new Adventurer(new Position(0, 0), "1n", EOrientation.E, "a"));
        game.addAdventurer(new Adventurer(new Position(1, 0), "2n", EOrientation.S, "a"));
        game.addAdventurer(new Adventurer(new Position(1, 1), "3n", EOrientation.O, "a"));
        game.addAdventurer(new Adventurer(new Position(0, 2), "4n", EOrientation.N, "a"));
        game.addAdventurer(new Adventurer(new Position(0, 1), "5n", EOrientation.N, "a"));
        game.addAdventurer(new Adventurer(new Position(2, 2), "6n", EOrientation.E, "a"));

        // act
        System.out.println(game.print());
        game.start();
        System.out.println(game.print());

        String res = "C - 3 - 3\n" +
                "A - 1n - 1 - 0 - E - 0\n" +
                "A - 2n - 1 - 1 - S - 0\n" +
                "A - 3n - 0 - 1 - O - 0\n" +
                "A - 4n - 0 - 2 - N - 0\n" +
                "A - 5n - 0 - 0 - N - 0\n" +
                "A - 6n - 2 - 2 - E - 0\n";
        Assertions.assertEquals(res, game.print());
    }


    @Test
    public void gameTreasureTest() throws Exception {
        Game game = new Game(4, 1);
        game.addSpecialBox(new Treasure(new Position(2, 0), 2));
        game.addSpecialBox(new Treasure(new Position(2, 0), 2));
        game.addAdventurer(new Adventurer(new Position(1, 0), "1n", EOrientation.E, "addgga"));
        game.addAdventurer(new Adventurer(new Position(0, 0), "2n", EOrientation.E, "aggdda"));

        // act
        System.out.println(game.print());
        game.start();
        System.out.println(game.print());

        String res = "C - 4 - 1\n" +
                "A - 1n - 3 - 0 - E - 1\n" +
                "A - 2n - 2 - 0 - E - 1\n";
        Assertions.assertEquals(res, game.print());
    }

    @Test
    public void gameAdventurerOutOfMapTest() {
        Game game = new Game(1, 1);
        Assertions.assertThrows(Exception.class, () -> game.addAdventurer(new Adventurer(new Position(-1, -1), "1n", EOrientation.E, "addgga")));
        Assertions.assertThrows(Exception.class, () -> game.addAdventurer(new Adventurer(new Position(3, 3), "1n", EOrientation.E, "addgga")));
    }


}
