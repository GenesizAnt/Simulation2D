package org.myproject.world;

import org.myproject.entity.animate.Creature;
import org.myproject.entity.animate.Herbivore;
import org.myproject.entity.animate.Predator;
import org.myproject.entity.inanimate.Grass;
import org.myproject.entity.inanimate.Rock;
import org.myproject.entity.inanimate.Tree;

import java.util.*;

public class BreadthFirstSearch {

    Queue<Coordinate> queue = new ArrayDeque<>();
    ArrayList<Coordinate> listVisit = new ArrayList<>();
    private final WorldMap worldMap;
    private boolean isFind = false;

    public BreadthFirstSearch(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public ArrayList<Coordinate> shortcutsSearch(Creature creature) {
//        if (creature instanceof Predator) {
//            drawWordAfterTurn(); //ToDo перемещает животных на карте, которые походили раньше текущего животного
//        }

        cleanMapParent(worldMap);

        ArrayList<Coordinate> pathFindToEntity = new ArrayList<>();

        queue.add(creature.getCoordinate()); // добавляем в очередь первые координаты

        while (!isFind) {
            Coordinate coordinateFindObj = checkFindEntity(queue, creature);// проверяем - искомый ли здесь объект?

            if (coordinateFindObj == null) { // если искомый объект не нашелся
                Coordinate visitedCoordinate = queue.poll(); // посещенная координата из очереди

                listVisit.add(visitedCoordinate); // добавляем в список Посещенные координаты

                queue = replenishQueue(Objects.requireNonNull(visitedCoordinate), queue);// ищем соседей для объекта, который посетили и добавляем в очередь

                queue.removeAll(listVisit);// удаляем из очереди, все координаты которые посетили

            } else {

                pathFindToEntity = getPathFindToEntity(coordinateFindObj, creature); //если нашли нужный объект через его родителей "разворачиваем" кратчайший путь
                isFind = true;

            }
        }

        return pathFindToEntity;
    }

    private void cleanMapParent(WorldMap worldMap) {
        for (int i = 0; i < worldMap.getSizeX(); i++) {
            for (int j = 0; j < worldMap.getSizeY(); j++) {
                worldMap.getMap()[i][j].getCoordinate().setParent(null);
            }
        }
    }

    //ToDo Поиск короткого пути - сделать поиск объекта, а от него по диагонали
    // сделать возврат к точке, если препятствие, то обойти. Тогда путь будет прямой
    private ArrayList<Coordinate> getPathFindToEntity(Coordinate coordinateFindObj, Creature creature) {
        ArrayList<Coordinate> coordinateList = new ArrayList<>();
        while (!(worldMap.getEntity(coordinateFindObj).getCoordinate().equals(creature.getCoordinate()))) {
            coordinateList.add(coordinateFindObj);
            coordinateFindObj = coordinateFindObj.getParent();
        }


        Collections.reverse(coordinateList);
        return coordinateList;
    }

// рабочий код
//    private ArrayList<Coordinate> getPathFindToEntity(Coordinate coordinateFindObj, Creature creature) {
//        ArrayList<Coordinate> coordinateList = new ArrayList<>();
//
//        if (creature instanceof Herbivore) {
//            while (!(worldMap.getEntity(coordinateFindObj).getCoordinate().equals(creature.getCoordinate()))) {
//                coordinateList.add(coordinateFindObj);
//                coordinateFindObj = coordinateFindObj.getParent();
//            }
//        } else if (creature instanceof Predator) {
//            while (!(worldMap.getEntity(coordinateFindObj) instanceof Predator)) {
////            while (!(worldMap.getEntity(coordinateFindObj) instanceof Herbivore) это правильная строчка
//                coordinateList.add(coordinateFindObj);
//                coordinateFindObj = coordinateFindObj.getParent();
//            }
//        }
//
//        Collections.reverse(coordinateList);
//        return coordinateList;
//    }


    private Queue<Coordinate> replenishQueue(Coordinate parent, Queue<Coordinate> queue) {
        Queue<Coordinate> childEntity = queue;

        int startX = parent.getCordX() - 1;
        if (startX < 0) {
            startX = 0;
        }

        int startY = parent.getCordY() - 1;
        if (startY < 0) {
            startY = 0;
        }

        int endX = parent.getCordX() + 1;
        if (endX >= worldMap.getMap().length) {
            endX = worldMap.getMap().length - 1;
        }

        int endY = parent.getCordY() + 1;
        if (endY >= worldMap.getMap()[0].length) {
            endY = worldMap.getMap()[0].length - 1;
        }


        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                if (
//                        !(worldMap.getEntity(new Coordinate(i, j)) instanceof Herbivore) &&
                        !(worldMap.getEntity(new Coordinate(i, j)) instanceof Predator) &&
                                !(worldMap.getEntity(new Coordinate(i, j)) instanceof Tree) &&
                                !(worldMap.getEntity(new Coordinate(i, j)) instanceof Rock)) {

                    if (worldMap.getEntity(i, j).getCoordinate().getParent() == null) {
                        worldMap.getEntity(i, j).getCoordinate().setParent(parent);
                    }
                    childEntity.add(worldMap.getEntity(i, j).getCoordinate());

                }
            }
        }
        return childEntity;
    }

    private Coordinate checkFindEntity(Queue<Coordinate> queue, Creature creature) {
        ArrayList<Coordinate> queueCheck = new ArrayList<>(queue);

        for (Coordinate coordinate : queueCheck) {
            if ((worldMap.getEntity(coordinate) instanceof Grass && creature instanceof Herbivore) ||
                    (worldMap.getEntity(coordinate) instanceof Herbivore && creature instanceof Predator)) {
                return coordinate;
            }
        }
        return null;
    }
//
//
//    private void drawWordAfterTurn() {
//        for (int i = 0; i < worldMap.getSizeX(); i++) {
//            for (int j = 0; j < worldMap.getSizeY(); j++) {
//                if (worldMap.getSetHerbivore().containsKey(new Coordinate(i, j))) {
//                    worldMap.getMap()[i][j] = worldMap.getSetHerbivore().get(new Coordinate(i, j));
//                } else if (worldMap.getSetPredator().containsKey(new Coordinate(i, j))) {
//                    worldMap.getMap()[i][j] = worldMap.getSetPredator().get(new Coordinate(i, j));
//                } else if (worldMap.getSetGrass().containsKey(new Coordinate(i, j))) {
//                    worldMap.getMap()[i][j] = worldMap.getSetGrass().get(new Coordinate(i, j));
//                } else if (worldMap.getSetRock().containsKey(new Coordinate(i, j))) {
//                    worldMap.getMap()[i][j] = worldMap.getSetRock().get(new Coordinate(i, j));
//                } else if (worldMap.getSetTree().containsKey(new Coordinate(i, j))) {
//                    worldMap.getMap()[i][j] = worldMap.getSetTree().get(new Coordinate(i, j));
//                } else if (worldMap.getSetGround().containsKey(new Coordinate(i, j))) {
//                    worldMap.getMap()[i][j] = worldMap.getSetGround().get(new Coordinate(i, j));
//                }
//            }
//        }
//    }
}
