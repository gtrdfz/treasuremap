package tests;

import org.carbonit.box.Adventurer;
import org.carbonit.box.EMovement;
import org.carbonit.box.EOrientation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AdventurerTest {


    @Test
    public void AdventurerOrientationTest() {
        Adventurer east = new Adventurer(1, 1, UUID.randomUUID().toString(), EOrientation.E);
        east.applyMovement(EMovement.D);
        Assertions.assertEquals(EOrientation.S, east.geteOrientation());
        east.applyMovement(EMovement.D);
        Assertions.assertEquals(EOrientation.O, east.geteOrientation());
        east.applyMovement(EMovement.D);
        Assertions.assertEquals(EOrientation.N, east.geteOrientation());
        east.applyMovement(EMovement.D);
        Assertions.assertEquals(EOrientation.E, east.geteOrientation());
        east.applyMovement(EMovement.G);
        Assertions.assertEquals(EOrientation.N, east.geteOrientation());
        east.applyMovement(EMovement.G);
        Assertions.assertEquals(EOrientation.O, east.geteOrientation());
        east.applyMovement(EMovement.G);
        Assertions.assertEquals(EOrientation.S, east.geteOrientation());
        east.applyMovement(EMovement.G);
        east.applyMovement(EMovement.A);
        Assertions.assertEquals(EOrientation.E, east.geteOrientation());
    }


}
