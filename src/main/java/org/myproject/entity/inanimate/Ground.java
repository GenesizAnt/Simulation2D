package org.myproject.entity.inanimate;

import org.myproject.entity.Entity;
import org.myproject.world.Coordinate;

public class Ground extends Entity {
    public Ground(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public String toString() {
        return "  .  ";
    }
}
