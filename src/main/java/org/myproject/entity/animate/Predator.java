package org.myproject.entity.animate;

import org.myproject.entity.inanimate.Ground;
import org.myproject.world.BreadthFirstSearch;
import org.myproject.world.Coordinate;
import org.myproject.world.WorldMap;

import java.util.ArrayList;

public class Predator extends Creature {

    private int attackPower;
    private ArrayList<Coordinate> coordinates = new ArrayList<>();

    public Predator(Coordinate coordinate) {
        super(coordinate);
        this.attackPower = (int) (Math.random() * 10) + 1;
    }

    @Override
    public void searchFastTrack(WorldMap worldMap) {
        try {
            Creature creature = this;
            BreadthFirstSearch fastTrack = new BreadthFirstSearch(worldMap);
            coordinates = fastTrack.shortcutsSearch(creature);
        } catch (Exception e) {

        }
    }

    @Override
    public void makeMove(WorldMap worldMap) {

//        try {

//        Creature creature = this;

        if (!coordinates.isEmpty()) {

            if (worldMap.getSetPredator().containsKey(coordinates.get(0))) {
                coordinates.clear();
            } else if (worldMap.getSetHerbivore().containsKey(coordinates.get(0))) {
                worldMap.getSetHerbivore().get(coordinates.get(0)).setSizeHP(worldMap.getSetHerbivore().get(coordinates.get(0)).getSizeHP() - this.getAttackPower());
                if (worldMap.getSetHerbivore().get(coordinates.get(0)).getSizeHP() <= 0) {
                    worldMap.getSetPredator().put(coordinates.get(0), this);
                    worldMap.getSetPredator().get(coordinates.get(0)).eatHerbivore(worldMap.getSetHerbivore().get(coordinates.get(0)).getUpHP());
                    worldMap.getSetHerbivore().remove(coordinates.get(0));
                    worldMap.getSetGround().put(coordinates.get(0), new Ground(this.getCoordinate()));
                    worldMap.getSetPredator().remove(this.getCoordinate());
                    worldMap.getSetGround().put(this.getCoordinate(), new Ground(this.getCoordinate()));
                    this.setCoordinate(coordinates.get(0));
                }
            } else if (!(checkHerbivoreAround(worldMap, this) == null)) {
                Coordinate coordinate = checkHerbivoreAround(worldMap, this);
                worldMap.getSetHerbivore().get(coordinate).setSizeHP(worldMap.getSetHerbivore().get(coordinate).getSizeHP() - this.getAttackPower());
                if (worldMap.getSetHerbivore().get(coordinate).getSizeHP() <= 0) {
                    worldMap.getSetPredator().put(coordinate, this);
                    worldMap.getSetPredator().get(coordinate).eatHerbivore(worldMap.getSetHerbivore().get(coordinates.get(0)).getUpHP());
                    worldMap.getSetHerbivore().remove(coordinate);
                    worldMap.getSetGround().put(coordinate, new Ground(this.getCoordinate()));
                    worldMap.getSetPredator().remove(coordinate);
                    worldMap.getSetGround().put(this.getCoordinate(), new Ground(this.getCoordinate()));
                    this.setCoordinate(coordinates.get(0));
                }
            } else {
                worldMap.getSetPredator().put(coordinates.get(0), this);
                worldMap.getSetPredator().remove(this.getCoordinate());
                worldMap.getSetGround().put(this.getCoordinate(), new Ground(this.getCoordinate()));
                this.setCoordinate(coordinates.get(0));
            }
//                setGround.put(new Coordinate(i, j), new Ground(new Coordinate(i, j)));
//
//                    ((Herbivore) worldMap.getMap()[i][j]).makeMove((Creature) worldMap.getEntity(i, j));


//                worldMap.setCoordinateEntityCoordinate(this, coordinates.get(0).getCordX(), coordinates.get(0).getCordY());
//                worldMap.setCoordinateEntityCoordinate(new Ground(this.getCoordinate()), this.getCoordinate().getCordX(), this.getCoordinate().getCordY());
//                this.setCoordinate(new Coordinate(coordinates.get(0).getCordX(), coordinates.get(0).getCordY()));
        }

//        } catch (Exception e) {
//
//        }


//        BreadthFirstSearch fastTrack = new BreadthFirstSearch(worldMap);
//
//        try {
//            //ToDo сделать путь на один короче и после каждого шага сделать проверку окружения и делать удар если есть животное + добавить проверку на смерть животного
//
//            Creature creature = this;
//
//            ArrayList<Coordinate> coordinates = fastTrack.shortcutsSearch(creature);
//
//            if (coordinates.size() > 0) {
//
//                if (worldMap.getSetPredator().containsKey(coordinates.get(0))) {
//                    coordinates.clear();
//                }
////                else if (worldMap.getSetHerbivore().containsKey(coordinates.get(1))) {
//
////                    worldMap.getSetHerbivore().get(coordinates.get(1)).setSizeHP(getSizeHP() - this.getAttackPower());
////                    if (worldMap.getSetPredator().get(coordinates.get(1)).getSizeHP() <= 0) {
////                        worldMap.getSetPredator().get(coordinates.get(0)).eatHerbivore(worldMap.getSetHerbivore().get(coordinates.get(1)).getUpHP());
////                        worldMap.getSetHerbivore().remove(coordinates.get(1));
////                        worldMap.getSetGround().put(coordinates.get(1), new Ground(creature.getCoordinate()));
////                    }
//
////                }
////                else if (checkHerbivoreAround()) {
////                    //attac
////                }
//
//                else {
//                    worldMap.getSetPredator().put(coordinates.get(0), (Predator) creature);
//
//                    worldMap.getSetPredator().remove(creature.getCoordinate());
//                    worldMap.getSetGround().put(creature.getCoordinate(), new Ground(creature.getCoordinate()));
//                    creature.setCoordinate(coordinates.get(0));
//                }
//
////                if (worldMap.getSetGrass().containsKey(coordinates.get(0))) {
////                    worldMap.getSetHerbivore().get(coordinates.get(0)).eatGrass(worldMap.getSetGrass().get(coordinates.get(0)).getUpHP());
////                    worldMap.getSetGrass().remove(coordinates.get(0));
////                    worldMap.getSetGround().put(coordinates.get(0), new Ground(creature.getCoordinate()));
////                }
////
//
////                setGround.put(new Coordinate(i, j), new Ground(new Coordinate(i, j)));
////
////                    ((Herbivore) worldMap.getMap()[i][j]).makeMove((Creature) worldMap.getEntity(i, j));
//
//
////                worldMap.setCoordinateEntityCoordinate(this, coordinates.get(0).getCordX(), coordinates.get(0).getCordY());
////                worldMap.setCoordinateEntityCoordinate(new Ground(this.getCoordinate()), this.getCoordinate().getCordX(), this.getCoordinate().getCordY());
////                this.setCoordinate(new Coordinate(coordinates.get(0).getCordX(), coordinates.get(0).getCordY()));
//            }
//
//        } catch (Exception e) {
//
//        }

    }

    private void eatHerbivore(int upHP) {
        setSizeHP(getSizeHP() + upHP);
    }

    private Coordinate checkHerbivoreAround(WorldMap worldMap, Predator predator) {

        int startX = this.getCoordinate().getCordX() - 1;
        if (startX < 0) {
            startX = 0;
        }

        int startY = this.getCoordinate().getCordY() - 1;
        if (startY < 0) {
            startY = 0;
        }

        int endX = this.getCoordinate().getCordX() + 1;
        if (endX >= worldMap.getMap().length) {
            endX = worldMap.getMap().length - 1;
        }

        int endY = this.getCoordinate().getCordY() + 1;
        if (endY >= worldMap.getMap()[0].length) {
            endY = worldMap.getMap()[0].length - 1;
        }


        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                if (worldMap.getSetHerbivore().containsKey(new Coordinate(i, j))) {
                    return new Coordinate(i, j);
                }
            }
        }
        return null;
    }


    public int getAttackPower() {
        return attackPower;
    }

    @Override
    public String toString() {
        return "  \u03A8  ";
    }
}
