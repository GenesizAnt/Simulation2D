package org.myproject.world;

import org.myproject.entity.animate.Herbivore;
import org.myproject.entity.animate.Predator;
import org.myproject.entity.inanimate.Grass;
import org.myproject.entity.inanimate.Ground;
import org.myproject.entity.inanimate.Rock;
import org.myproject.entity.inanimate.Tree;

public class Action {

    private WorldMap worldMap;

    public Action(WorldMap worldMap) {
        this.worldMap = worldMap;
        inhabitMap();
    }

    private void inhabitMap() {
        int countHerbivore = 1;
        int countPredator = 2;
        int countGrass = 1;
        int countRock = 9;
        int countTree = 5;
        int countAllEntity = -1;

//        int countHerbivore = 2;
//        int countPredator = 2;
//        int countGrass = 5;
//        int countRock = 5;
//        int countTree = 5;
//        int countAllEntity = -1;

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
    }
}
