package org.carbonit.box;

public abstract class ABox {

    private final int horizontal;
    private final int vertical;

    public ABox(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    protected abstract int getReward();

    protected abstract boolean canGoOnIt();

    public int getHorizontal() {
        return horizontal;
    }

    public int getVertical() {
        return vertical;
    }
}
