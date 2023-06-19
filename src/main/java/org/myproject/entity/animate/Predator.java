package org.myproject.entity.animate;

import org.myproject.entity.inanimate.Ground;
import org.myproject.world.BreadthFirstSearch;
import org.myproject.world.Coordinate;
import org.myproject.world.WorldMap;

import java.util.ArrayList;

public class Predator extends Creature {

    private int attackPower;

    public Predator(Coordinate coordinate) {
        super(coordinate);
        this.attackPower = (int) (Math.random() * 10) + 1;
    }

    @Override
    public void makeMove(WorldMap worldMap) {

//        BreadthFirstSearch fastTrack = new BreadthFirstSearch(worldMap);
//
//
//        try {
//
//            ArrayList<Coordinate> coordinates = fastTrack.shortcutsSearch(this);
//
//            if (coordinates.size() > 0) {
//                worldMap.setCoordinateEntityCoordinate(this, coordinates.get(0).getCordX(), coordinates.get(0).getCordY());
//                worldMap.setCoordinateEntityCoordinate(new Ground(this.getCoordinate()), this.getCoordinate().getCordX(), this.getCoordinate().getCordY());
//                this.setCoordinate(new Coordinate(coordinates.get(0).getCordX(), coordinates.get(0).getCordY()));
//            }
//
//        } catch (Exception e) {
//
//        }

    }

    @Override
    public String toString() {
        return "  \u03A8  ";
    }
}
