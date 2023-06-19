package org.myproject.entity.animate;

import org.myproject.entity.inanimate.Ground;
import org.myproject.world.BreadthFirstSearch;
import org.myproject.world.Coordinate;
import org.myproject.world.WorldMap;

import java.util.ArrayList;

public class Herbivore extends Creature {

//    BreadthFirstSearch fastTrack = new BreadthFirstSearch();

    public Herbivore(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public void makeMove(WorldMap worldMap) {
        BreadthFirstSearch fastTrack = new BreadthFirstSearch(worldMap);

        try {

            Creature creature = this;

            ArrayList<Coordinate> coordinates = fastTrack.shortcutsSearch(creature);

            if (coordinates.size() > 0) {

                worldMap.getSetHerbivore().put(coordinates.get(0), (Herbivore) creature);
                worldMap.getSetHerbivore().remove(creature.getCoordinate());
                worldMap.getSetGround().put(creature.getCoordinate(), new Ground(creature.getCoordinate()));
                creature.setCoordinate(coordinates.get(0));
//                setGround.put(new Coordinate(i, j), new Ground(new Coordinate(i, j)));
//
//                    ((Herbivore) worldMap.getMap()[i][j]).makeMove((Creature) worldMap.getEntity(i, j));


//                worldMap.setCoordinateEntityCoordinate(this, coordinates.get(0).getCordX(), coordinates.get(0).getCordY());
//                worldMap.setCoordinateEntityCoordinate(new Ground(this.getCoordinate()), this.getCoordinate().getCordX(), this.getCoordinate().getCordY());
//                this.setCoordinate(new Coordinate(coordinates.get(0).getCordX(), coordinates.get(0).getCordY()));
            }

        } catch (Exception e) {

        }
    }

    @Override
    public String toString() {
        return "  O  ";
    }

}
