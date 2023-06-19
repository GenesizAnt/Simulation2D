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
        BreadthFirstSearch fastTrack = new BreadthFirstSearch(worldMap);

        try { //ToDo сделать путь на один короче и после каждого шага сделать проверку окружения и делать удар если есть животное + добавить проверку на смерть животного

            Creature creature = this;

            ArrayList<Coordinate> coordinates = fastTrack.shortcutsSearch(creature);

            if (coordinates.size() > 0) {

                if (worldMap.getSetPredator().containsKey(coordinates.get(0))) {
                    coordinates.clear();
                } else {
                    worldMap.getSetPredator().put(coordinates.get(0), (Predator) creature);
//                    if (worldMap.getSetGrass().containsKey(coordinates.get(0))) {
//                        worldMap.getSetHerbivore().get(coordinates.get(0)).eatGrass(worldMap.getSetGrass().get(coordinates.get(0)).getUpHP());
//                        worldMap.getSetGrass().remove(coordinates.get(0));
//                        worldMap.getSetGround().put(coordinates.get(0), new Ground(creature.getCoordinate()));
//                    }
                    worldMap.getSetPredator().remove(creature.getCoordinate());
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

    @Override
    public String toString() {
        return "  \u03A8  ";
    }
}
