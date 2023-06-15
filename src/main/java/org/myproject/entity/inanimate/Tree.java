package org.myproject.entity.inanimate;

import org.myproject.entity.Entity;
import org.myproject.world.Coordinate;

public class Tree extends Entity {
    public Tree(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public String toString() {
        return " `|` ";
    }
}
