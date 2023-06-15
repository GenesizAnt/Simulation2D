package org.myproject.entity.inanimate;

import org.myproject.entity.Entity;
import org.myproject.world.Coordinate;

public class Grass extends Entity {
    public Grass(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public String toString() {
        return "  \u2234  ";
    }
}
