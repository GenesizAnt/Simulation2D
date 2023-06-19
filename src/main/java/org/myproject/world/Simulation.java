package org.myproject.world;

import org.myproject.entity.*;
import org.myproject.entity.animate.Creature;
import org.myproject.entity.animate.Herbivore;
import org.myproject.entity.animate.Predator;
import org.myproject.entity.inanimate.Grass;
import org.myproject.entity.inanimate.Ground;
import org.myproject.entity.inanimate.Rock;
import org.myproject.entity.inanimate.Tree;

import java.util.ArrayList;
import java.util.HashMap;

public class Simulation {

    WorldMap worldMap = new WorldMap(10,10);
//    Action action = new Action(worldMap);
    BreadthFirstSearch search = new BreadthFirstSearch(worldMap);
    private Entity[][] map;
    private boolean isSimulationPause;
    private int daySimulation;

    public void startSimulation() {

//        actions.initAction(this.fieldWorld, quantityHerbivore, quantityPredator, quantityRock, quantityTree, quantityGrass);

        while (!isSimulationPause) {
//            showWorldMap();
            drawWordAfterTurn();
            entityTurn();
            checkIsSimulationPause(daySimulation);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void entityTurn() {
//        WorldMap map1 = action.getWorldMap();

        for (int i = 0; i < worldMap.getSizeX(); i++) {
            for (int j = 0; j < worldMap.getSizeX(); j++) {
                if (worldMap.getEntity(i, j) instanceof Herbivore) {
                    ((Herbivore) worldMap.getEntity(i, j)).makeMove(worldMap);
                }
            }
        }

//        action.setWorldMap(map1);
        daySimulation++;
    }

    private void showWorldMap() {
        System.out.print("\nSimulation World day " + daySimulation);
        worldMap.drawMap();
    }

    private boolean checkIsSimulationPause(int turnCount) {
        //дать на выбор несколько вариантов как продолжать дальнейшую симуляцию
        //после 10 ходов пауза
        //если сьели всех животных
        //вопрос - хотите перейти на пошаговую симуляцию
        return false;
    }

//    public void getEntityWorld() {
//        for (int i = 0; i < action.getWorldMap().getSizeX(); i++) {
//            for (int j = 0; j < action.getWorldMap().getSizeX(); j++) {
//                if (action.getWorldMap().getMap()[i][j] instanceof Herbivore) {
//                    setHerbivore.put(new Coordinate(i, j), (Herbivore) action.getWorldMap().getMap()[i][j]);
//                } else if (action.getWorldMap().getMap()[i][j] instanceof Predator) {
//                    setPredator.put(new Coordinate(i, j), (Predator) action.getWorldMap().getMap()[i][j]);
//                } else if (action.getWorldMap().getMap()[i][j] instanceof Grass) {
//                    setGrass.put(new Coordinate(i, j), (Grass) action.getWorldMap().getMap()[i][j]);
//                } else if (action.getWorldMap().getMap()[i][j] instanceof Rock) {
//                    setRock.put(new Coordinate(i, j), (Rock) action.getWorldMap().getMap()[i][j]);
//                } else if (action.getWorldMap().getMap()[i][j] instanceof Tree) {
//                    setTree.put(new Coordinate(i, j), (Tree) action.getWorldMap().getMap()[i][j]);
//                } else if (action.getWorldMap().getMap()[i][j] instanceof Ground) {
//                    setGround.put(new Coordinate(i, j), (Ground) action.getWorldMap().getMap()[i][j]);
//                }
//            }
//        }
//    }


//    public void generateTurn() {
//
//        while (true) {
//            worldMap.drawMap();
//
//            turnWord();
//            drawWordAfterTurn();
//
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
    private void drawWordAfterTurn() {
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
        System.out.println("____________");
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
