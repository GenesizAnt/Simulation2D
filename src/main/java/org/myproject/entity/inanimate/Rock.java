package org.myproject.entity.inanimate;

import org.myproject.entity.Entity;
import org.myproject.world.Coordinate;

public class Rock extends Entity {
    public Rock(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public String toString() {
        return "  \u25A0  ";
    }
}
