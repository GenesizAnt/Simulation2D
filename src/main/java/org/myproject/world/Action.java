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

    public void initActions(int countHerbivore, int countPredator, int countGrass, int countRock, int countTree) {
        int countAllEntity = -1;

        while (!(countAllEntity == 0)) {

            int sizeXRandom = (int) (Math.random() * worldMap.getSizeX());
            int sizeYRandom = (int) (Math.random() * worldMap.getSizeY());

            if (worldMap.getMap()[sizeXRandom][sizeYRandom] instanceof Ground && countHerbivore > 0) {
                worldMap.getMap()[sizeXRandom][sizeYRandom] = new Herbivore(new Coordinate(sizeXRandom, sizeYRandom));
                countHerbivore--;
            } else if (worldMap.getMap()[sizeXRandom][sizeYRandom] instanceof Ground && countPredator > 0) {
                worldMap.getMap()[sizeXRandom][sizeYRandom] = new Predator(new Coordinate(sizeXRandom, sizeYRandom));
                countPredator--;
            } else if (worldMap.getMap()[sizeXRandom][sizeYRandom] instanceof Ground && countGrass > 0) {
                worldMap.getMap()[sizeXRandom][sizeYRandom] = new Grass(new Coordinate(sizeXRandom, sizeYRandom));
                countGrass--;
            } else if (worldMap.getMap()[sizeXRandom][sizeYRandom] instanceof Ground && countRock > 0) {
                worldMap.getMap()[sizeXRandom][sizeYRandom] = new Rock(new Coordinate(sizeXRandom, sizeYRandom));
                countRock--;
            } else if (worldMap.getMap()[sizeXRandom][sizeYRandom] instanceof Ground && countTree > 0) {
                worldMap.getMap()[sizeXRandom][sizeYRandom] = new Tree(new Coordinate(sizeXRandom, sizeYRandom));
                countTree--;
            }
            countAllEntity = countHerbivore + countPredator + countGrass + countGrass + countRock + countTree;
        }
        getEntityWorld();
    }

    private void getEntityWorld() {
        for (int i = 0; i < worldMap.getSizeX(); i++) {
            for (int j = 0; j < worldMap.getSizeY(); j++) {
                if (worldMap.getMap()[i][j] instanceof Herbivore) {
                    worldMap.getSetHerbivore().put(new Coordinate(i, j), (Herbivore) worldMap.getMap()[i][j]);
                } else if (worldMap.getMap()[i][j] instanceof Predator) {
                    worldMap.getSetPredator().put(new Coordinate(i, j), (Predator) worldMap.getMap()[i][j]);
                } else if (worldMap.getMap()[i][j] instanceof Grass) {
                    worldMap.getSetGrass().put(new Coordinate(i, j), (Grass) worldMap.getMap()[i][j]);
                } else if (worldMap.getMap()[i][j] instanceof Rock) {
                    worldMap.getSetRock().put(new Coordinate(i, j), (Rock) worldMap.getMap()[i][j]);
                } else if (worldMap.getMap()[i][j] instanceof Tree) {
                    worldMap.getSetTree().put(new Coordinate(i, j), (Tree) worldMap.getMap()[i][j]);
                } else if (worldMap.getMap()[i][j] instanceof Ground) {
                    worldMap.getSetGround().put(new Coordinate(i, j), (Ground) worldMap.getMap()[i][j]);
                }
            }
        }
    }

    public void turnActions() {
        addNewEntityIfNeeded();
        drawWordAfterTurn();
        entitySearchFastTrack();
        entityTurnPredator();
        entityTurnHerbivore();
        checkGrassDelete();
    }

    private void addNewEntityIfNeeded() {
        if (worldMap.getSetGrass().isEmpty()) {
            int countNewGrass = 3;
            for (int i = 0; i <= countNewGrass; i++) {
                int randomCoordinate = getRandomCoordinateGround();
                Coordinate coordinate = (Coordinate) worldMap.getSetGround().keySet().toArray()[randomCoordinate];
                worldMap.getSetGrass().put(coordinate, new Grass(new Coordinate(coordinate.getCordX(), coordinate.getCordY())));
                worldMap.getSetGround().remove(coordinate);
            }
            int randomCoordinate = (int) ((Math.random() * worldMap.getSetGround().size()));
            Coordinate coordinate = (Coordinate) worldMap.getSetGround().keySet().toArray()[randomCoordinate];
            worldMap.getSetHerbivore().put(coordinate, new Herbivore(new Coordinate(coordinate.getCordX(), coordinate.getCordY()))); //если скушали всю травку, добавляем живтоное
            worldMap.getSetGround().remove(coordinate);
            if (worldMap.getSetHerbivore().size() > 10) {
                int randomCoordinate1 = (int) ((Math.random() * worldMap.getSetGround().size()));
                Coordinate coordinate1 = (Coordinate) worldMap.getSetGround().keySet().toArray()[randomCoordinate1];
                worldMap.getSetPredator().put(coordinate1, new Predator(new Coordinate(coordinate1.getCordX(), coordinate1.getCordY()))); //если скушали всю травку, добавляем живтоное
                worldMap.getSetGround().remove(coordinate1);
            }
        }
    }

    private int getRandomCoordinateGround() {
        return (int) ((Math.random() * worldMap.getSetGround().size()));
    }

    private void entitySearchFastTrack() {
        for (int i = 0; i < worldMap.getSizeX(); i++) {
            for (int j = 0; j < worldMap.getSizeX(); j++) {
                if (worldMap.getEntity(i, j) instanceof Creature) {
                    ((Creature) worldMap.getEntity(i, j)).searchFastTrack(worldMap);
                }
            }
        }
    }

    private void entityTurnPredator() {
        for (int i = 0; i < worldMap.getSizeX(); i++) {
            for (int j = 0; j < worldMap.getSizeX(); j++) {
                if (worldMap.getEntity(i, j) instanceof Predator) {
                    ((Predator) worldMap.getEntity(i, j)).makeMove(worldMap);
                }
            }
        }
//        daySimulation++;
    }

    private void entityTurnHerbivore() {
        for (int i = 0; i < worldMap.getSizeX(); i++) {
            for (int j = 0; j < worldMap.getSizeX(); j++) {
                if (worldMap.getEntity(i, j) instanceof Herbivore) {
                    ((Herbivore) worldMap.getEntity(i, j)).makeMove(worldMap);
                }
            }
        }
    }

    private void drawWordAfterTurn() {
        System.out.print("\nSimulation World day " + daySimulation);
        for (int i = 0; i < worldMap.getSizeX(); i++) {
            System.out.println();
            for (int j = 0; j < worldMap.getSizeY(); j++) {
                if (worldMap.getSetHerbivore().containsKey(new Coordinate(i, j))) {
                    worldMap.getMap()[i][j] = worldMap.getSetHerbivore().get(new Coordinate(i, j));
                } else if (worldMap.getSetPredator().containsKey(new Coordinate(i, j))) {
                    worldMap.getMap()[i][j] = worldMap.getSetPredator().get(new Coordinate(i, j));
                } else if (worldMap.getSetGrass().containsKey(new Coordinate(i, j))) {
                    worldMap.getMap()[i][j] = worldMap.getSetGrass().get(new Coordinate(i, j));
                } else if (worldMap.getSetRock().containsKey(new Coordinate(i, j))) {
                    worldMap.getMap()[i][j] = worldMap.getSetRock().get(new Coordinate(i, j));
                } else if (worldMap.getSetTree().containsKey(new Coordinate(i, j))) {
                    worldMap.getMap()[i][j] = worldMap.getSetTree().get(new Coordinate(i, j));
                } else if (worldMap.getSetGround().containsKey(new Coordinate(i, j))) {
                    worldMap.getMap()[i][j] = worldMap.getSetGround().get(new Coordinate(i, j));
                }
                System.out.print(worldMap.getMap()[i][j]);
            }
        }
        System.out.println();
        daySimulation++;
    }

    private void checkGrassDelete() {
        ArrayList<Coordinate> grass = new ArrayList<>(worldMap.getSetGrass().keySet());
        Set<Coordinate> herbivore = worldMap.getSetHerbivore().keySet();
        Set<Coordinate> predator = worldMap.getSetPredator().keySet();

        for (int i = 0; i < grass.size(); i++) {
            if (herbivore.contains(grass.get(i))) {
                worldMap.getSetGrass().remove(grass.get(i));
            }
            if (predator.contains(grass.get(i))) {
                worldMap.getSetGrass().remove(grass.get(i));
            }
        }
    }

//    public WorldMap getWorldMap() {
//        return worldMap;
//    }
//
//    public void setWorldMap(WorldMap worldMap) {
//        this.worldMap = worldMap;
//    }
}
