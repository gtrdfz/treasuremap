package org.carbonit.box;

public class Treasure extends ABox {

    private int nb;

    public Treasure(Position position, int nb) {
        super(position, true);
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
    public int getReward() {
        return GetOneTreasure();
    }

    @Override
    public String print() {
        return String.format("T - %d - %d - %d", getPosition().getHorizontal(), getPosition().getVertical(), nb);
    }

    @Override
    public boolean canBeDelete() {
        return nb <= 0;
    }
}
