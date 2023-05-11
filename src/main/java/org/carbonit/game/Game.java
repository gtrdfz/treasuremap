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
            // set all next position
            for (Adventurer adventurer : adventurerList) {
                adventurer.setNextPosition(getNextPosition(adventurer));
            }
            // check conflict before moves
            for (Adventurer adventurer : adventurerList) {
                if (checkConflict(adventurer, null))
                    if (adventurer.getNextPosition().equals(adventurer.getPosition()))
                        executeNextMove(adventurer);
                    else
                        adventurer.cancelMovement();
                else
                    executeNextMove(adventurer);

                adventurer.setNextPosition(adventurer.getPosition());
            }
        }
    }

    private boolean checkConflict(Adventurer adventurer, List<Adventurer> adList) {
        for (Adventurer ad : adList) {
            if (adventurer.getNextPosition().equals(ad.getNextPosition()) || adventurer.getNextPosition().equals(ad.getPosition()))
                return checkConflict(ad, adList.subList(adList.indexOf(ad) + 1, adList.size()));
        }
        return false;
    }

    private Position getNextPosition(Adventurer adventurer) {
        Position newPosition = adventurer.getNextCaseWithoutChangePosition();
        // position it out of the map
        if (!isInMap(newPosition)) {
            return adventurer.getPosition();
        }
        // position not change
        if (newPosition.equals(adventurer.getPosition())) {
            return newPosition;
        }
        // check if position can be change, and get rewards
        if (positionABoxHashMap.containsKey(newPosition)) {
            ABox box = positionABoxHashMap.get(newPosition);
            if (!box.isCanGoOnIt())
                return adventurer.getPosition();
        }
        return newPosition;
    }

    private void executeNextMove(Adventurer adventurer) {
        // position it out of the map
        if (!isInMap(adventurer.getNextPosition())) {
            Logger.error("Impossible situation");
            return;
        }
        // check if position can be change, and get rewards
        if (positionABoxHashMap.containsKey(adventurer.getNextPosition())) {
            ABox box = positionABoxHashMap.get(adventurer.getNextPosition());
            if (box.isCanGoOnIt()) {
                adventurer.addTreseare(box.getReward());
                if (box.canBeDelete()) positionABoxHashMap.remove(adventurer.getNextPosition());
            } else {
                Logger.error("Impossible situation");
                return;
            }
        }
        adventurer.applyMovement();
    }

    private boolean isInMap(Position newPosition) {
        return newPosition.getHorizontal() > 0 && newPosition.getHorizontal() <= this.width && newPosition.getVertical() > 0 && newPosition.getVertical() <= this.height;
    }


    public String print() {
        StringBuilder output = new StringBuilder();
        output.append("\n");
        for (ABox b : this.positionABoxHashMap.values())
            output.append(b.print()).append("\n");
        for (Adventurer a : this.adventurerList)
            output.append(a.print()).append("\n");
        return output.toString();
    }

}
