package org.myproject.entity.inanimate;

import org.myproject.entity.Entity;
import org.myproject.world.Coordinate;

public class Grass extends Entity {

    private int healthUP;

    public Grass(Coordinate coordinate) {
        super(coordinate);
        this.healthUP = (int) (Math.random() * 10) + 1;
    }

    public int getHealthUP() {
        return healthUP;
    }

    @Override
    public String toString() {
        return "  \u2234  ";
    }
}
