package org.carbonit.box;

public class Treasure extends ABox {

    private int nb;

    public Treasure(int horizontal, int vertical, int nb) {
        super(horizontal, vertical);
        this.nb = nb;
    }

    private int GetOneTreasure() {
        if (nb > 0) {
            nb--;
            return 1;
        }
        return 0;
    }

    @Override
    protected int getReward() {
        return GetOneTreasure();
    }

    @Override
    protected boolean canGoOnIt() {
        return true;
    }
}
