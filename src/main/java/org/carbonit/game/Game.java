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
                setRealNextPosition(adventurer);
            }
            // check conflict before moves
            manageConflicts();

            for (Adventurer adventurer : adventurerList) {
                executeNextMove(adventurer);
            }

        }
    }

    private void manageConflicts() {
        for (Adventurer a : adventurerList) {
            for (Adventurer b : adventurerList) {
                if (!a.equals(b) && a.getNextPosition().equals(b.getNextPosition())) {
                    b.setNextPosition(b.getPosition());
                    manageConflicts();
                }
            }
        }
    }

    private void setRealNextPosition(Adventurer adventurer) {
        adventurer.calculateTheoreticalNextPosition();
        // position it out of the map
        if (!isInMap(adventurer.getNextPosition())) {
            adventurer.setNextPosition(adventurer.getPosition());
            return;
        }
        // check if position can be change, and get rewards
        if (positionABoxHashMap.containsKey(adventurer.getNextPosition())) {
            ABox box = positionABoxHashMap.get(adventurer.getNextPosition());
            if (!box.isCanGoOnIt()) {
                adventurer.setNextPosition(adventurer.getPosition());
            }
        }
    }

    private void executeNextMove(Adventurer adventurer) {
        // check if position can be change, and get rewards
        if (positionABoxHashMap.containsKey(adventurer.getNextPosition())) {
            ABox box = positionABoxHashMap.get(adventurer.getNextPosition());
            if (box.isCanGoOnIt()) {
                adventurer.addTreseare(box.getReward());
                if (box.canBeDelete())
                    positionABoxHashMap.remove(adventurer.getNextPosition());
            }
        }
        adventurer.applyMovement();
    }

    private boolean isInMap(Position newPosition) {
        return newPosition.getHorizontal() >= 0 && newPosition.getHorizontal() <= this.width && newPosition.getVertical() >= 0 && newPosition.getVertical() <= this.height;
    }

    public String print() {
        StringBuilder output = new StringBuilder();
        output.append(String.format("C - %d - %d\n", width, height));
        for (ABox b : this.positionABoxHashMap.values())
            output.append(b.print()).append("\n");
        for (Adventurer a : this.adventurerList)
            output.append(a.print()).append("\n");
        return output.toString();
    }

}
