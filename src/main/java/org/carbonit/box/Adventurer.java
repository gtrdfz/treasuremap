package org.carbonit.box;

import org.tinylog.Logger;

public class Adventurer {

    private final String name;
    private final int horizontal;
    private final int vertical;
    private EOrientation eOrientation;

    public Adventurer(int horizontal, int vertical, String name, EOrientation eOrientation) {
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.name = name;
        this.eOrientation = eOrientation;
    }

    public void applyMovement(EMovement movement) {
        EOrientation[] orientations = EOrientation.values();
        int orientationLength = orientations.length;
        int currentOrientationOrdinal = this.eOrientation.ordinal();
        if (movement == EMovement.D)
            eOrientation = orientations[(currentOrientationOrdinal + 1) % orientationLength];
        else if (movement == EMovement.G)
            eOrientation = orientations[Math.abs((orientationLength + currentOrientationOrdinal - 1) % orientationLength)];
        else if (movement == EMovement.A)
            Logger.error("todo"); //TODO
        else
            Logger.error("Movement not implemented:" + movement.name());
    }

    public EOrientation geteOrientation() {
        return eOrientation;
    }

}
