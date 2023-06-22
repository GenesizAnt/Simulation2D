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

    private int sizeX;
    private int sizeY;
    private Entity[][] map;
    HashMap<Coordinate, Herbivore> setHerbivore = new HashMap<>();
    HashMap<Coordinate, Predator> setPredator = new HashMap<>();
    HashMap<Coordinate, Grass> setGrass = new HashMap<>();
    HashMap<Coordinate, Rock> setRock = new HashMap<>();
    HashMap<Coordinate, Tree> setTree = new HashMap<>();
    HashMap<Coordinate, Ground> setGround = new HashMap<>();

    public WorldMap(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.map = new Entity[sizeX][sizeY];
        createMap();
        inhabitMap();
        getEntityWorld();
    }

    public void drawMap() {
        for (int i = 0; i < sizeX; i++) {
            System.out.println();
            for (int j = 0; j < sizeY; j++) {
                System.out.print(map[i][j]);
            }
        }
    }

    private void createMap() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                map[i][j] = new Ground(new Coordinate(i, j));
            }
        }
    }

    private void inhabitMap() {
        int countHerbivore = 7;
        int countPredator = 2;
        int countGrass = 3;
        int countRock = 3;
        int countTree = 5;
        int countAllEntity = -1;

//        int countHerbivore = 2;
//        int countPredator = 2;
//        int countGrass = 5;
//        int countRock = 5;
//        int countTree = 5;
//        int countAllEntity = -1;

        while (!(countAllEntity == 0)) {

            int sizeXRandom = (int) (Math.random() * sizeX);
            int sizeYRandom = (int) (Math.random() * sizeY);

            if (map[sizeXRandom][sizeYRandom] instanceof Ground && countHerbivore > 0) {
                map[sizeXRandom][sizeYRandom] = new Herbivore(new Coordinate(sizeXRandom, sizeYRandom));
                countHerbivore--;
            } else if (map[sizeXRandom][sizeYRandom] instanceof Ground && countPredator > 0) {
                map[sizeXRandom][sizeYRandom] = new Predator(new Coordinate(sizeXRandom, sizeYRandom));
                countPredator--;
            } else if (map[sizeXRandom][sizeYRandom] instanceof Ground && countGrass > 0) {
                map[sizeXRandom][sizeYRandom] = new Grass(new Coordinate(sizeXRandom, sizeYRandom));
                countGrass--;
            } else if (map[sizeXRandom][sizeYRandom] instanceof Ground && countRock > 0) {
                map[sizeXRandom][sizeYRandom] = new Rock(new Coordinate(sizeXRandom, sizeYRandom));
                countRock--;
            } else if (map[sizeXRandom][sizeYRandom] instanceof Ground && countTree > 0) {
                map[sizeXRandom][sizeYRandom] = new Tree(new Coordinate(sizeXRandom, sizeYRandom));
                countTree--;
            }
            countAllEntity = countHerbivore + countPredator + countGrass + countGrass + countRock + countTree;
        }
    }

    public void getEntityWorld() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (map[i][j] instanceof Herbivore) {
                    setHerbivore.put(new Coordinate(i, j), (Herbivore) map[i][j]);
                } else if (map[i][j] instanceof Predator) {
                    setPredator.put(new Coordinate(i, j), (Predator) map[i][j]);
                } else if (map[i][j] instanceof Grass) {
                    setGrass.put(new Coordinate(i, j), (Grass) map[i][j]);
                } else if (map[i][j] instanceof Rock) {
                    setRock.put(new Coordinate(i, j), (Rock) map[i][j]);
                } else if (map[i][j] instanceof Tree) {
                    setTree.put(new Coordinate(i, j), (Tree) map[i][j]);
                } else if (map[i][j] instanceof Ground) {
                    setGround.put(new Coordinate(i, j), (Ground) map[i][j]);
                }
            }
        }
    }

    public HashMap<Coordinate, Herbivore> getSetHerbivore() {
        return setHerbivore;
    }

    public void setSetHerbivore(HashMap<Coordinate, Herbivore> setHerbivore) {
        this.setHerbivore = setHerbivore;
    }

    public HashMap<Coordinate, Predator> getSetPredator() {
        return setPredator;
    }

    public void setSetPredator(HashMap<Coordinate, Predator> setPredator) {
        this.setPredator = setPredator;
    }

    public HashMap<Coordinate, Grass> getSetGrass() {
        return setGrass;
    }

    public void setSetGrass(HashMap<Coordinate, Grass> setGrass) {
        this.setGrass = setGrass;
    }

    public HashMap<Coordinate, Rock> getSetRock() {
        return setRock;
    }

    public void setSetRock(HashMap<Coordinate, Rock> setRock) {
        this.setRock = setRock;
    }

    public HashMap<Coordinate, Tree> getSetTree() {
        return setTree;
    }

    public void setSetTree(HashMap<Coordinate, Tree> setTree) {
        this.setTree = setTree;
    }

    public HashMap<Coordinate, Ground> getSetGround() {
        return setGround;
    }

    public void setSetGround(HashMap<Coordinate, Ground> setGround) {
        this.setGround = setGround;
    }

    public void setCoordinateEntityCoordinate(Entity entity, int sizeX, int sizeY) {
        map[sizeX][sizeY] = entity;
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
