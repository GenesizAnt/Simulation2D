package org.myproject.entity.inanimate;

import org.myproject.entity.Entity;
import org.myproject.world.Coordinate;

public class Grass extends Entity {

    private int upHP;

    public Grass(Coordinate coordinate) {
        super(coordinate);
        this.upHP = (int) (Math.random() * 10) + 1;
    }

    public int getUpHP() {
        return upHP;
    }

    @Override
    public String toString() {
        return "  \u2234  ";
    }
}
