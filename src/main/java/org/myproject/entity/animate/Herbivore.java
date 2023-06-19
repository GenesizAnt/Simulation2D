package org.myproject.entity.animate;

import org.myproject.entity.inanimate.Grass;
import org.myproject.entity.inanimate.Ground;
import org.myproject.world.BreadthFirstSearch;
import org.myproject.world.Coordinate;
import org.myproject.world.WorldMap;

import java.util.ArrayList;

public class Herbivore extends Creature {

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

                if (worldMap.getSetHerbivore().containsKey(coordinates.get(0))) {
                    coordinates.clear();
                } else {
                    worldMap.getSetHerbivore().put(coordinates.get(0), (Herbivore) creature);
                    if (worldMap.getSetGrass().containsKey(coordinates.get(0))) {
                        worldMap.getSetHerbivore().get(coordinates.get(0)).eatGrass(worldMap.getSetGrass().get(coordinates.get(0)).getUpHP());
                        worldMap.getSetGrass().remove(coordinates.get(0));
                        worldMap.getSetGround().put(coordinates.get(0), new Ground(creature.getCoordinate()));
                    }
                    worldMap.getSetHerbivore().remove(creature.getCoordinate());
                    worldMap.getSetGround().put(creature.getCoordinate(), new Ground(creature.getCoordinate()));
                    creature.setCoordinate(coordinates.get(0));
                }
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

    private void eatGrass(int upHP) {
        setSizeHP(getSizeHP() + upHP);
    }

    @Override
    public String toString() {
        return "  O  ";
    }

    public int getUpHP() {
        return (int) (Math.random() * this.getSizeHP()) + 1;
    }

}
