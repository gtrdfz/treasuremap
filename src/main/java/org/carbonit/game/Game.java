package org.carbonit.game;

import org.carbonit.box.ABox;
import org.carbonit.box.Adventurer;
import org.carbonit.box.Position;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Game {

    private final int width;
    private final int height;
    private final HashMap<Position, ABox> positionABoxHashMap = new HashMap<>();
    private final List<Adventurer> adventurerList = new ArrayList<>();

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addSpecialBox(ABox box) {
        if (box.getPosition().getHorizontal() > height || box.getPosition().getVertical() > width)
            Logger.error("One element is out of the map: " + box.print());
        if (positionABoxHashMap.containsKey(box.getPosition()))
            Logger.error("2 elements can't be on same box: " + box.print());
        else
            positionABoxHashMap.put(box.getPosition(), box);
    }

    public void addAdventurer(Adventurer adventurer) {
        if (adventurer.getPosition().getHorizontal() > height || adventurer.getPosition().getVertical() > width)
            Logger.error("One element is out of the map: " + adventurer.getPosition().toString());
        adventurerList.add(adventurer);
    }

    public void start() {

        int i = 0;
        while (adventurerList.stream().anyMatch(x -> x.geteMovementList().size() > 0)) {
            Logger.info("Round n°" + i);
            for (Adventurer adventurer : adventurerList) {
                executeMove(adventurer);
            }
        }

    }

    private void executeMove(Adventurer adventurer) {
        Position newPosition = adventurer.getNextCaseWithoutChangePosition();
        if(newPosition.equals(adventurer.getPosition()))
            adventurer.applyMovement();
    }


}
