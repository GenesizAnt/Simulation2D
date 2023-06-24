package org.myproject.entity.animate;

import org.myproject.entity.inanimate.Ground;
import org.myproject.world.BreadthFirstSearch;
import org.myproject.world.Coordinate;
import org.myproject.world.WorldMap;
import java.util.ArrayList;

public class Predator extends Creature {
    private final int attackPower;
    private ArrayList<Coordinate> motionCoordinates = new ArrayList<>();

    public Predator(Coordinate coordinate) {
        super(coordinate);
        this.attackPower = (int) (Math.random() * 10) + 1;
    }

    @Override
    public void searchFastTrack(WorldMap worldMap) {
        BreadthFirstSearch breadthSearch = new BreadthFirstSearch(worldMap);
        motionCoordinates = breadthSearch.findShortestWay(this);
    }

    @Override
    public void makeMove(WorldMap worldMap) {
        if (!motionCoordinates.isEmpty()) {

            if (worldMap.getPredatorPopulation().containsKey(motionCoordinates.get(0))) {
                motionCoordinates.clear();
            } else if (worldMap.getHerbivorePopulation().containsKey(motionCoordinates.get(0))) {
                worldMap.getHerbivorePopulation().get(motionCoordinates.get(0)).setHealth(worldMap.getHerbivorePopulation().get(motionCoordinates.get(0)).getHealth() - this.getAttackPower());
                if (worldMap.getHerbivorePopulation().get(motionCoordinates.get(0)).getHealth() <= 0) {
                    worldMap.getPredatorPopulation().put(motionCoordinates.get(0), this);
                    worldMap.getPredatorPopulation().get(motionCoordinates.get(0)).eatHerbivore(worldMap.getHerbivorePopulation().get(motionCoordinates.get(0)).getHealth());
                    worldMap.getHerbivorePopulation().remove(motionCoordinates.get(0));
                    worldMap.getPredatorPopulation().remove(this.getCoordinate());
                    worldMap.getGroundPopulation().put(this.getCoordinate(), new Ground(this.getCoordinate()));
                    this.setCoordinate(motionCoordinates.get(0));
                }
            } else if (!(checkHerbivoreAround(worldMap) == null)) {
                Coordinate coordinate = checkHerbivoreAround(worldMap);
                worldMap.getHerbivorePopulation().get(coordinate).setHealth(worldMap.getHerbivorePopulation().get(coordinate).getHealth() - this.getAttackPower());
                if (worldMap.getHerbivorePopulation().get(coordinate).getHealth() <= 0) {
                    worldMap.getPredatorPopulation().put(coordinate, this);
                    worldMap.getPredatorPopulation().get(coordinate).eatHerbivore(worldMap.getHerbivorePopulation().get(coordinate).getHealth()); //coordinates.get(0)
                    worldMap.getHerbivorePopulation().remove(coordinate);
                    worldMap.getPredatorPopulation().remove(this.getCoordinate());
                    worldMap.getGroundPopulation().put(this.getCoordinate(), new Ground(this.getCoordinate()));
                    this.setCoordinate(motionCoordinates.get(0));
                }
            } else {
                worldMap.getPredatorPopulation().put(motionCoordinates.get(0), this);
                worldMap.getPredatorPopulation().remove(this.getCoordinate());
                worldMap.getGroundPopulation().put(this.getCoordinate(), new Ground(this.getCoordinate()));
                this.setCoordinate(motionCoordinates.get(0));
            }
        }
    }

    private void eatHerbivore(int upHealth) {
        setHealth(getHealth() + upHealth);
    }

    private Coordinate checkHerbivoreAround(WorldMap worldMap) {

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
                if (worldMap.getHerbivorePopulation().containsKey(new Coordinate(i, j))) {
                    return worldMap.getHerbivorePopulation().get(new Coordinate(i, j)).getCoordinate();
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
