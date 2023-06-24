package org.myproject.world;

import org.myproject.entity.Entity;
import org.myproject.entity.animate.Herbivore;
import org.myproject.entity.animate.Predator;
import org.myproject.entity.inanimate.Grass;
import org.myproject.entity.inanimate.Ground;
import org.myproject.entity.inanimate.Rock;
import org.myproject.entity.inanimate.Tree;

import java.util.HashMap;

public class WorldMap {

    private final int sizeX;
    private final int sizeY;
    private final Entity[][] map;
    HashMap<Coordinate, Herbivore> herbivorePopulation = new HashMap<>();
    HashMap<Coordinate, Predator> predatorPopulation = new HashMap<>();
    HashMap<Coordinate, Grass> grassPopulation = new HashMap<>();
    HashMap<Coordinate, Rock> rockPopulation = new HashMap<>();
    HashMap<Coordinate, Tree> treePopulation = new HashMap<>();
    HashMap<Coordinate, Ground> groundPopulation = new HashMap<>();

    public WorldMap(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.map = new Entity[sizeX][sizeY];
        createMap();
    }

    private void createMap() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                map[i][j] = new Ground(new Coordinate(i, j));
            }
        }
    }

    public HashMap<Coordinate, Herbivore> getHerbivorePopulation() {
        return herbivorePopulation;
    }

    public HashMap<Coordinate, Predator> getPredatorPopulation() {
        return predatorPopulation;
    }

    public HashMap<Coordinate, Grass> getGrassPopulation() {
        return grassPopulation;
    }

    public HashMap<Coordinate, Rock> getRockPopulation() {
        return rockPopulation;
    }

    public HashMap<Coordinate, Tree> getTreePopulation() {
        return treePopulation;
    }

    public HashMap<Coordinate, Ground> getGroundPopulation() {
        return groundPopulation;
    }

    public Entity getEntity(Coordinate coordinate) {
        return map[coordinate.getCordX()][coordinate.getCordY()];
    }

    public Entity getEntity(int x, int y) {
        return map[x][y];
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public Entity[][] getMap() {
        return map;
    }
}
