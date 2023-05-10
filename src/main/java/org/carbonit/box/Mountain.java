package org.carbonit.box;

public class Mountain extends ABox {

    public Mountain(Position position) {
        super(position, false);
    }

    @Override
    protected int getReward() {
        return 0;
    }

    @Override
    public String print() {
        return String.format("M - %d - %d", getPosition().getHorizontal(), getPosition().getVertical());
    }

}
