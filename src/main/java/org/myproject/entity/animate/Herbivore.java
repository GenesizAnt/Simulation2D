package org.myproject.entity.animate;

import org.myproject.entity.inanimate.Ground;
import org.myproject.world.BreadthFirstSearch;
import org.myproject.world.Coordinate;
import org.myproject.world.WorldMap;

import java.util.ArrayList;

public class Herbivore extends Creature {
    private int health;
    private ArrayList<Coordinate> motionCoordinates = new ArrayList<>();

    public Herbivore(Coordinate coordinate) {
        super(coordinate);
        this.health = (int) (Math.random() * 30) + 1;
    }

    @Override
    public void searchFastTrack(WorldMap worldMap) {
        BreadthFirstSearch breadthSearch = new BreadthFirstSearch(worldMap);
        motionCoordinates = breadthSearch.findShortestWay(this);
    }

    @Override
    public void makeMove(WorldMap worldMap) {
        if (!motionCoordinates.isEmpty()) {
            if (worldMap.getHerbivorePopulation().containsKey(motionCoordinates.get(0)) || worldMap.getPredatorPopulation().containsKey(motionCoordinates.get(0))) {
                motionCoordinates.clear();
            } else {
                worldMap.getHerbivorePopulation().put(motionCoordinates.get(0), this);
                worldMap.getHerbivorePopulation().get(motionCoordinates.get(0)).setHealth(this.getHealth());
                if (worldMap.getGrassPopulation().containsKey(motionCoordinates.get(0))) {
                    worldMap.getHerbivorePopulation().get(motionCoordinates.get(0)).eatGrass(worldMap.getGrassPopulation().get(motionCoordinates.get(0)).getHealthUP());
                    worldMap.getGrassPopulation().remove(motionCoordinates.get(0));
                    worldMap.getGroundPopulation().put(motionCoordinates.get(0), new Ground(this.getCoordinate()));
                }
                worldMap.getHerbivorePopulation().remove(this.getCoordinate());
                worldMap.getGroundPopulation().put(this.getCoordinate(), new Ground(this.getCoordinate()));
                this.setCoordinate(motionCoordinates.get(0));
            }
        }
    }

    private void eatGrass(int upHealth) {
        setHealth(this.getHealth() + upHealth);
    }

    @Override
    public String toString() {
        return "  O  ";
    }
}
