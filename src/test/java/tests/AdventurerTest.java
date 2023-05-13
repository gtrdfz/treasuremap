package tests;

import org.carbonit.box.Adventurer;
import org.carbonit.box.EOrientation;
import org.carbonit.box.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AdventurerTest {


    @Test
    public void AdventurerOrientationTest() throws Exception {
        Adventurer adv = new Adventurer(new Position(1, 1), UUID.randomUUID().toString(), EOrientation.E, "d");
        adv.applyMovement();
        Assertions.assertEquals(EOrientation.S, adv.geteOrientation());

        adv = new Adventurer(new Position(1, 1), UUID.randomUUID().toString(), adv.geteOrientation(), "d");
        adv.applyMovement();
        Assertions.assertEquals(EOrientation.O, adv.geteOrientation());

        adv = new Adventurer(new Position(1, 1), UUID.randomUUID().toString(), adv.geteOrientation(), "d");
        adv.applyMovement();
        Assertions.assertEquals(EOrientation.N, adv.geteOrientation());

        adv = new Adventurer(new Position(1, 1), UUID.randomUUID().toString(), adv.geteOrientation(), "d");
        adv.applyMovement();
        Assertions.assertEquals(EOrientation.E, adv.geteOrientation());

        adv = new Adventurer(new Position(1, 1), UUID.randomUUID().toString(), adv.geteOrientation(), "g");
        adv.applyMovement();
        Assertions.assertEquals(EOrientation.N, adv.geteOrientation());

        adv = new Adventurer(new Position(1, 1), UUID.randomUUID().toString(), adv.geteOrientation(), "g");
        adv.applyMovement();
        Assertions.assertEquals(EOrientation.O, adv.geteOrientation());

        adv = new Adventurer(new Position(1, 1), UUID.randomUUID().toString(), adv.geteOrientation(), "g");
        adv.applyMovement();
        Assertions.assertEquals(EOrientation.S, adv.geteOrientation());

        adv = new Adventurer(new Position(1, 1), UUID.randomUUID().toString(), adv.geteOrientation(), "ga");
        adv.applyMovement();
        adv.applyMovement();
        Assertions.assertEquals(EOrientation.E, adv.geteOrientation());
    }

    @Test
    public void AdventureWalkGTest() throws Exception {
        // arrange
        Position position = new Position(1, 1);
        Adventurer adv = new Adventurer(position, UUID.randomUUID().toString(), EOrientation.E, "g");
        // act
        Position newPosition = adv.calculateTheoreticalNextPosition();
        // assert
        Assertions.assertEquals(position, newPosition);
    }

    @Test
    public void AdventureWalkDTest() throws Exception {
        // arrange
        Position position = new Position(1, 1);
        Adventurer adv = new Adventurer(position, UUID.randomUUID().toString(), EOrientation.E, "d");
        // act
        Position newPosition = adv.calculateTheoreticalNextPosition();
        // assert
        Assertions.assertEquals(position, newPosition);
    }

    @Test
    public void AdventureWalkTest() throws Exception {
        Adventurer adv = new Adventurer(new Position(1, 1), UUID.randomUUID().toString(), EOrientation.S, "a");
        Assertions.assertEquals(new Position(1, 2), adv.calculateTheoreticalNextPosition());

        adv = new Adventurer(new Position(1, 1), UUID.randomUUID().toString(), EOrientation.N, "a");
        Assertions.assertEquals(new Position(1, 0), adv.calculateTheoreticalNextPosition());

        adv = new Adventurer(new Position(1, 1), UUID.randomUUID().toString(), EOrientation.E, "a");
        Assertions.assertEquals(new Position(2, 1), adv.calculateTheoreticalNextPosition());

        adv = new Adventurer(new Position(1, 1), UUID.randomUUID().toString(), EOrientation.O, "a");
        Assertions.assertEquals(new Position(0, 1), adv.calculateTheoreticalNextPosition());
    }

    @Test
    public void AdventureMovementNotValidTest() {
        Assertions.assertThrows(Exception.class, () -> {
            new Adventurer(new Position(1, 1), UUID.randomUUID().toString(), EOrientation.S, "f");
        });
    }

}
