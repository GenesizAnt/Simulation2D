package org.myproject.world;

import org.myproject.entity.animate.Creature;
import org.myproject.entity.animate.Herbivore;
import org.myproject.entity.animate.Predator;
import org.myproject.entity.inanimate.Grass;
import org.myproject.entity.inanimate.Ground;
import org.myproject.entity.inanimate.Rock;
import org.myproject.entity.inanimate.Tree;

import java.util.ArrayList;
import java.util.Set;

public class Action {
    private WorldMap worldMap;
    private int daySimulation = 0;

    public Action(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void initActions(int amountHerbivore, int amountPredator, int amountGrass, int amountRock, int amountTree) {
        int amountAllEntity = -1;

        while (!(amountAllEntity == 0)) {

            int sizeXRandom = (int) (Math.random() * worldMap.getSizeX());
            int sizeYRandom = (int) (Math.random() * worldMap.getSizeY());

            if (worldMap.getMap()[sizeXRandom][sizeYRandom] instanceof Ground && amountHerbivore > 0) {
                worldMap.getMap()[sizeXRandom][sizeYRandom] = new Herbivore(new Coordinate(sizeXRandom, sizeYRandom));
                amountHerbivore--;
            } else if (worldMap.getMap()[sizeXRandom][sizeYRandom] instanceof Ground && amountPredator > 0) {
                worldMap.getMap()[sizeXRandom][sizeYRandom] = new Predator(new Coordinate(sizeXRandom, sizeYRandom));
                amountPredator--;
            } else if (worldMap.getMap()[sizeXRandom][sizeYRandom] instanceof Ground && amountGrass > 0) {
                worldMap.getMap()[sizeXRandom][sizeYRandom] = new Grass(new Coordinate(sizeXRandom, sizeYRandom));
                amountGrass--;
            } else if (worldMap.getMap()[sizeXRandom][sizeYRandom] instanceof Ground && amountRock > 0) {
                worldMap.getMap()[sizeXRandom][sizeYRandom] = new Rock(new Coordinate(sizeXRandom, sizeYRandom));
                amountRock--;
            } else if (worldMap.getMap()[sizeXRandom][sizeYRandom] instanceof Ground && amountTree > 0) {
                worldMap.getMap()[sizeXRandom][sizeYRandom] = new Tree(new Coordinate(sizeXRandom, sizeYRandom));
                amountTree--;
            }
            amountAllEntity = amountHerbivore + amountPredator + amountGrass + amountGrass + amountRock + amountTree;
        }
        getEntityLists();
    }

    private void getEntityLists() {
        for (int i = 0; i < worldMap.getSizeX(); i++) {
            for (int j = 0; j < worldMap.getSizeY(); j++) {
                if (worldMap.getMap()[i][j] instanceof Herbivore) {
                    worldMap.getHerbivorePopulation().put(new Coordinate(i, j), (Herbivore) worldMap.getMap()[i][j]);
                } else if (worldMap.getMap()[i][j] instanceof Predator) {
                    worldMap.getPredatorPopulation().put(new Coordinate(i, j), (Predator) worldMap.getMap()[i][j]);
                } else if (worldMap.getMap()[i][j] instanceof Grass) {
                    worldMap.getGrassPopulation().put(new Coordinate(i, j), (Grass) worldMap.getMap()[i][j]);
                } else if (worldMap.getMap()[i][j] instanceof Rock) {
                    worldMap.getRockPopulation().put(new Coordinate(i, j), (Rock) worldMap.getMap()[i][j]);
                } else if (worldMap.getMap()[i][j] instanceof Tree) {
                    worldMap.getTreePopulation().put(new Coordinate(i, j), (Tree) worldMap.getMap()[i][j]);
                } else if (worldMap.getMap()[i][j] instanceof Ground) {
                    worldMap.getGroundPopulation().put(new Coordinate(i, j), (Ground) worldMap.getMap()[i][j]);
                }
            }
        }
    }

    public void turnActions() {
        addNewEntityIfNeeded();
        drawWorldMap();
        creatureLookingFastTrack();
        turnPredator();
        turnHerbivore();
        trampleGrass();
    }

    private void addNewEntityIfNeeded() {
        if (worldMap.getGrassPopulation().isEmpty()) {
            int amountNewGrass = 3;
            for (int i = 0; i <= amountNewGrass; i++) {
                int randomIndexCoordinate = getRandomCoordinateGround();
                Coordinate coordinate = (Coordinate) worldMap.getGroundPopulation().keySet().toArray()[randomIndexCoordinate];
                worldMap.getGrassPopulation().put(coordinate, new Grass(new Coordinate(coordinate.getCordX(), coordinate.getCordY())));
                worldMap.getGroundPopulation().remove(coordinate);
            }
            int randomIndexCoordinate = (int) ((Math.random() * worldMap.getGroundPopulation().size()));
            Coordinate coordinate = (Coordinate) worldMap.getGroundPopulation().keySet().toArray()[randomIndexCoordinate];
            worldMap.getHerbivorePopulation().put(coordinate, new Herbivore(new Coordinate(coordinate.getCordX(), coordinate.getCordY()))); //если скушали всю травку, добавляем живтоное
            worldMap.getGroundPopulation().remove(coordinate);
            if (worldMap.getHerbivorePopulation().size() > 10) {
                int randomCoordinate1 = (int) ((Math.random() * worldMap.getGroundPopulation().size()));
                Coordinate coordinate1 = (Coordinate) worldMap.getGroundPopulation().keySet().toArray()[randomCoordinate1];
                worldMap.getPredatorPopulation().put(coordinate1, new Predator(new Coordinate(coordinate1.getCordX(), coordinate1.getCordY()))); //если скушали всю травку, добавляем живтоное
                worldMap.getGroundPopulation().remove(coordinate1);
            }
        }
    }

    private int getRandomCoordinateGround() {
        return (int) ((Math.random() * worldMap.getGroundPopulation().size()));
    }

    private void creatureLookingFastTrack() {
        for (int i = 0; i < worldMap.getSizeX(); i++) {
            for (int j = 0; j < worldMap.getSizeX(); j++) {
                if (worldMap.getEntity(i, j) instanceof Creature) {
                    ((Creature) worldMap.getEntity(i, j)).searchFastTrack(worldMap);
                }
            }
        }
    }

    private void turnPredator() {
        for (int i = 0; i < worldMap.getSizeX(); i++) {
            for (int j = 0; j < worldMap.getSizeX(); j++) {
                if (worldMap.getEntity(i, j) instanceof Predator) {
                    ((Predator) worldMap.getEntity(i, j)).makeMove(worldMap);
                }
            }
        }
    }

    private void turnHerbivore() {
        for (int i = 0; i < worldMap.getSizeX(); i++) {
            for (int j = 0; j < worldMap.getSizeX(); j++) {
                if (worldMap.getEntity(i, j) instanceof Herbivore) {
                    ((Herbivore) worldMap.getEntity(i, j)).makeMove(worldMap);
                }
            }
        }
    }

    private void drawWorldMap() {
        System.out.print("\nSimulation World day " + daySimulation);
        for (int i = 0; i < worldMap.getSizeX(); i++) {
            System.out.println();
            for (int j = 0; j < worldMap.getSizeY(); j++) {
                if (worldMap.getHerbivorePopulation().containsKey(new Coordinate(i, j))) {
                    worldMap.getMap()[i][j] = worldMap.getHerbivorePopulation().get(new Coordinate(i, j));
                } else if (worldMap.getPredatorPopulation().containsKey(new Coordinate(i, j))) {
                    worldMap.getMap()[i][j] = worldMap.getPredatorPopulation().get(new Coordinate(i, j));
                } else if (worldMap.getGrassPopulation().containsKey(new Coordinate(i, j))) {
                    worldMap.getMap()[i][j] = worldMap.getGrassPopulation().get(new Coordinate(i, j));
                } else if (worldMap.getRockPopulation().containsKey(new Coordinate(i, j))) {
                    worldMap.getMap()[i][j] = worldMap.getRockPopulation().get(new Coordinate(i, j));
                } else if (worldMap.getTreePopulation().containsKey(new Coordinate(i, j))) {
                    worldMap.getMap()[i][j] = worldMap.getTreePopulation().get(new Coordinate(i, j));
                } else if (worldMap.getGroundPopulation().containsKey(new Coordinate(i, j))) {
                    worldMap.getMap()[i][j] = worldMap.getGroundPopulation().get(new Coordinate(i, j));
                }
                System.out.print(worldMap.getMap()[i][j]);
            }
        }
        System.out.println();
        daySimulation++;
    }

    private void trampleGrass() {
        ArrayList<Coordinate> grass = new ArrayList<>(worldMap.getGrassPopulation().keySet());
        Set<Coordinate> herbivore = worldMap.getHerbivorePopulation().keySet();
        Set<Coordinate> predator = worldMap.getPredatorPopulation().keySet();

        for (Coordinate coordinate : grass) {
            if (herbivore.contains(coordinate)) {
                worldMap.getGrassPopulation().remove(coordinate);
            }
            if (predator.contains(coordinate)) {
                worldMap.getGrassPopulation().remove(coordinate);
            }
        }
    }
}
