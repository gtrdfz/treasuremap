package org.carbonit.box;

public enum EOrientation {

    N("North", 0, -1), E("East", 1, 0), S("South", 0, 1), O("West", -1, 0);

    public final String orientation;
    public final int horizontal;
    public final int vertical;

    EOrientation(String orientation, int horizontal, int vertical) {
        this.orientation = orientation;
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

}
