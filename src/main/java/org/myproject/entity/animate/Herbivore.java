package org.myproject.entity.animate;

import org.myproject.entity.Entity;
import org.myproject.entity.inanimate.Grass;
import org.myproject.entity.inanimate.Ground;
import org.myproject.world.BreadthFirstSearch;
import org.myproject.world.Coordinate;
import org.myproject.world.WorldMap;

import java.util.ArrayList;

public class Herbivore extends Creature {

    private int upHP;
    private ArrayList<Coordinate> coordinates = new ArrayList<>();

    public Herbivore(Coordinate coordinate) {
        super(coordinate);
        this.upHP = (int) (Math.random() * 40) + 1;
    }

    @Override
    public void searchFastTrack(WorldMap worldMap) {
        BreadthFirstSearch fastTrack = new BreadthFirstSearch(worldMap);
        coordinates = fastTrack.shortcutsSearch(this);
    }

    @Override
    public void makeMove(WorldMap worldMap) {
        if (!coordinates.isEmpty()) {
            if (worldMap.getSetHerbivore().containsKey(coordinates.get(0)) || worldMap.getSetPredator().containsKey(coordinates.get(0))) {
                coordinates.clear();
            } else {
                worldMap.getSetHerbivore().put(coordinates.get(0), this);
                worldMap.getSetHerbivore().get(coordinates.get(0)).setSizeHP(this.getSizeHP());
                if (worldMap.getSetGrass().containsKey(coordinates.get(0))) {
                    worldMap.getSetHerbivore().get(coordinates.get(0)).eatGrass(worldMap.getSetGrass().get(coordinates.get(0)).getUpHP());
                    worldMap.getSetGrass().remove(coordinates.get(0));
                    worldMap.getSetGround().put(coordinates.get(0), new Ground(this.getCoordinate()));
                }
                worldMap.getSetHerbivore().remove(this.getCoordinate());
                worldMap.getSetGround().put(this.getCoordinate(), new Ground(this.getCoordinate()));
                this.setCoordinate(coordinates.get(0));
                if (worldMap.getSetGrass().containsKey(coordinates.get(0))) {
                    worldMap.getSetGrass().remove(coordinates.get(0));
                }
            }
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
        return upHP;
    }
}
