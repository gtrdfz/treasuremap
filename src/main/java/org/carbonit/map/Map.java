package org.carbonit.map;

import org.carbonit.box.ABox;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;

public final class Map {

    private final int width;
    private final int height;

    private final List<ABox> boxList = new ArrayList<>();

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addSpecialBox(ABox box) {
        if (box.getHorizontal() > height || box.getVertical() > width)
            Logger.error(String.format("One element is out of the map: vertical %d and horizontal %d", box.getVertical(), box.getHorizontal()));
        boxList.add(box);
    }

    public List<ABox> getSpecialBox(int width, int height) {
        List<ABox> result = new ArrayList<>();
        for (ABox b : boxList) {
            if (b.getVertical() == width && b.getVertical() == height)
                result.add(b);
        }
        return result;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


}
