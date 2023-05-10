package org.carbonit.box;

import java.util.Objects;

public class Position {

    private int horizontal;
    private int vertical;

    public Position(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }


    public void move(int horizontal, int vertical) {
        this.horizontal += horizontal;
        this.vertical += vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return horizontal == position.horizontal && vertical == position.vertical;
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontal, vertical);
    }

}
