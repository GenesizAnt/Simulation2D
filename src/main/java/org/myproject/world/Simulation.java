package org.myproject.world;

import org.myproject.entity.*;
import org.myproject.entity.animate.Creature;
import org.myproject.entity.animate.Herbivore;
import org.myproject.entity.animate.Predator;
import org.myproject.entity.inanimate.Grass;
import org.myproject.entity.inanimate.Ground;

public class Simulation {

    /**
     * Главный класс приложения, включает в себя:
     *
     * Карту
     * Счётчик ходов
     * Рендерер поля
     * Actions - список действий, исполняемых перед стартом симуляции или на каждом ходу (детали ниже)
     * Методы:
     *
     * nextTurn() - просимулировать и отрендерить один ход
     * startSimulation() - запустить бесконечный цикл симуляции и рендеринга
     * pauseSimulation() - приостановить бесконечный цикл симуляции и рендеринга
     *
     * Реализовать симуляцию и подобрать различные значения так, чтобы взаимодействия внутри мира получились максимально интересными:
     *
     * Размер поля
     * Диапазоны HP и скорости существ
     * Диапазон атаки хищников
     * Опциональные идеи для усложнения проекта:
     *
     * Механика размножения существ
     * Механика голода, когда от отсутствия пищи у них начинает уменьшаться HP
     */

    WorldMap worldMap = new WorldMap(10,10);
//    Action action = new Action(worldMap);
    BreadthFirstSearch search = new BreadthFirstSearch(worldMap);
    private Entity[][] map;
    private boolean isSimulationPause;
    private int daySimulation;

    public void startSimulation() {

        while (true) {

            addNewCreatures();
            drawWordAfterTurn();
            entitySearchFastTrack();
            entityTurnPredator();
            entityTurnHerbivore();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    //ToDo создать метод топтания травы

    private void addNewCreatures() {
        if (worldMap.getSetGrass().isEmpty()) {
            int countNewGrass = 5;
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
            if (worldMap.getSetGrass().containsKey(coordinate)) {
                worldMap.getSetGrass().remove(coordinate);
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

    private void entityTurnHerbivore() {
        for (int i = 0; i < worldMap.getSizeX(); i++) {
            for (int j = 0; j < worldMap.getSizeX(); j++) {
                if (worldMap.getEntity(i, j) instanceof Herbivore) {
                    ((Herbivore) worldMap.getEntity(i, j)).makeMove(worldMap);
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
        daySimulation++;
    }



    private void showWorldMap() {
        System.out.print("\nSimulation World day " + daySimulation);
        worldMap.drawMap();
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
    }
//
//    private void turnWord() {
//
//        for (int i = 0; i < worldMap.getSizeX(); i++) {
//            for (int j = 0; j < worldMap.getSizeY(); j++) {
//                if (worldMap.getMap()[i][j] instanceof Creature) {
//
//                    ArrayList<Coordinate> newCoordinate = search.shortcutsSearch((Creature) worldMap.getMap()[i][j]);
//                    setHerbivore.get(new Coordinate(i, j)).makeMove(new Coordinate(newCoordinate.get(0).getCordX(), newCoordinate.get(0).getCordY()));
//                    setGround.put(new Coordinate(i, j), new Ground(new Coordinate(i, j)));
////
////                    ((Herbivore) worldMap.getMap()[i][j]).makeMove((Creature) worldMap.getEntity(i, j));
//                }
//                worldMap.getMap()[i][j] = new Grass(new Coordinate(i,j));
////                map[i][j] = new Ground(new Coordinate(i, j));
//            }
//        }
//
//    }
//
//    private void turnHerbivore() {
//
//    }

//        worldMap.drawMap();



//        for (int i = 0; i < worldMap.getSizeX(); i++) {
//        for (int j = 0; j < worldMap.getSizeY(); j++) {
//            if (worldMap.getMap()[i][j] instanceof Herbivore) {
//                try {
//                    coordinates = search.shortcutsSearch(((Herbivore) worldMap.getMap()[i][j]));
//                } catch (Exception e) {
//                    coordinates.add(worldMap.getMap()[i][j].getCoordinate());
//                }
//            }
//        }
//    }


}
