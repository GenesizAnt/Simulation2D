package org.myproject.world;

import org.myproject.entity.animate.Herbivore;
import org.myproject.entity.animate.Predator;
import org.myproject.entity.inanimate.Grass;
import org.myproject.entity.inanimate.Ground;
import org.myproject.entity.inanimate.Rock;
import org.myproject.entity.inanimate.Tree;

public class Action {

    /**
     * Action - действие, совершаемое над миром. Например - сходить всеми существами. Это действие итерировало бы существ и вызывало каждому makeMove(). Каждое действие описывается отдельным классом и совершает операции над картой. Симуляция содержит 2 массива действий:
     *
     * initActions - действия, совершаемые перед стартом симуляции. Пример - расставить объекты и существ на карте
     * turnActions - действия, совершаемые каждый ход. Примеры - передвижение существ, добавить травы или травоядных, если их осталось слишком мало
     */

    private WorldMap worldMap;

    public Action(WorldMap worldMap) {
        this.worldMap = worldMap;
        inhabitMap();
    }

    private void inhabitMap() {
        int countHerbivore = 1;
        int countPredator = 1;
        int countGrass = 1;
        int countRock = 3;
        int countTree = 0;
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

    public WorldMap getWorldMap() {
        return worldMap;
    }

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }
}
