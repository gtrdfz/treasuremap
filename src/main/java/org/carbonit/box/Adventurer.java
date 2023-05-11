package org.carbonit.box;

import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;

public class Adventurer {

    private final String name;
    private final List<EMovement> eMovementList = new ArrayList<>();
    private int nbTreasure;
    private Position position;
    private Position nextPosition;
    private EOrientation eOrientation;

    public Adventurer(Position position, String name, EOrientation eOrientation, String movements) throws Exception {
        this.position = position;
        this.name = name;
        this.eOrientation = eOrientation;
        this.nbTreasure = 0;
        checkRegexMovements(movements);
    }

    public Position getNextPosition() {
        return nextPosition;
    }

    public void setNextPosition(Position nextPosition) {
        this.nextPosition = nextPosition;
    }

    private void checkRegexMovements(String movements) throws Exception {
        try {
            for (String move : movements.split("")) {
                eMovementList.add(EMovement.valueOf(move.toUpperCase()));
            }
        } catch (Exception e) {
            throw new Exception("Movements are not valid: " + movements);
        }

    }

    public Position getPosition() {
        return position;
    }

    public void addTreseare(int nb) {
        this.nbTreasure += nb;
    }

    public List<EMovement> geteMovementList() {
        return eMovementList;
    }

    public void applyMovement() {
        if (eMovementList.size() == 0)
            return;
        EMovement movement = eMovementList.get(0);
        eMovementList.remove(0);
        EOrientation[] orientations = EOrientation.values();
        int orientationLength = orientations.length;
        int currentOrientationOrdinal = this.eOrientation.ordinal();
        if (movement == EMovement.D)
            eOrientation = orientations[(currentOrientationOrdinal + 1) % orientationLength];
        else if (movement == EMovement.G)
            eOrientation = orientations[Math.abs((orientationLength + currentOrientationOrdinal - 1) % orientationLength)];
        else if (movement == EMovement.A)
            this.position = getNewPositionIfWalk();
        else
            Logger.error("Movement not implemented:" + movement.name());
    }

    public Position getNextCaseWithoutChangePosition() {
        if (eMovementList.size() > 0) {
            EMovement eMovement = eMovementList.get(0);
            if (eMovement == EMovement.A) {
                return getNewPositionIfWalk();
            }
        }
        return position;
    }

    public void cancelMovement() {
        if (eMovementList.size() > 0)
            this.eMovementList.remove(0);
    }

    private Position getNewPositionIfWalk() {
        return new Position(position.getHorizontal() + eOrientation.horizontal, position.getVertical() + eOrientation.vertical);
    }

    public EOrientation geteOrientation() {
        return eOrientation;
    }

    public String print() {
        return String.format("A - %s - %d - %d - %s - %d", this.name, getPosition().getHorizontal(), getPosition().getVertical(), this.eOrientation.toString(), this.nbTreasure);
    }

}
