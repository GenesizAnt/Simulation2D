package org.myproject.entity.animate;

import org.myproject.world.Coordinate;

public class Predator extends Creature {

    private int attackPower;

    public Predator(Coordinate coordinate) {
        super(coordinate);
        this.attackPower = (int) (Math.random() * 10) + 1;
    }

    @Override
    public void makeMove() {

    }

    @Override
    public String toString() {
        return "  \u03A8  ";
    }
}
