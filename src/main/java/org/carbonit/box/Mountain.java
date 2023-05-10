package org.carbonit.box;

public class Mountain extends ABox {

    public Mountain(int horizontal, int vertical) {
        super(horizontal, vertical);
    }

    @Override
    protected int getReward() {
        return 0;
    }

    @Override
    protected boolean canGoOnIt() {
        return false;
    }

}
