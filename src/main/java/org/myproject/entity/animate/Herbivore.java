package org.myproject.entity.animate;

import org.myproject.world.BreadthFirstSearch;
import org.myproject.world.Coordinate;
import org.myproject.world.WorldMap;

public class Herbivore extends Creature {

    public Herbivore(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public void makeMove() {

    }

    @Override
    public String toString() {
        return "  O  ";
    }
}
