package tests;

import org.carbonit.box.Mountain;
import org.carbonit.map.Map;
import org.junit.jupiter.api.Test;

public class GameTest {


    @Test
    public void gameTest() {
        Map map = new Map(5,5);
        map.addSpecialBox(new Mountain(10,1));
    }

}
