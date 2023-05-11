package org.carbonit.box;

public abstract class ABox {

    private final Position position;

    private final boolean canGoOnIt;

    public ABox(Position position, boolean canGoOnIt) {
        this.position = position;
        this.canGoOnIt = canGoOnIt;
    }

    public abstract int getReward();

    public Position getPosition() {
        return position;
    }

    public boolean isCanGoOnIt() {
        return canGoOnIt;
    }

    public abstract String print();

    public abstract boolean canBeDelete();
}
