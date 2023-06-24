package org.myproject.world;

import org.myproject.entity.animate.Creature;
import org.myproject.entity.animate.Herbivore;
import org.myproject.entity.animate.Predator;
import org.myproject.entity.inanimate.Grass;
import org.myproject.entity.inanimate.Rock;
import org.myproject.entity.inanimate.Tree;

import java.util.*;

public class BreadthFirstSearch {

    Queue<Coordinate> queueCoordinate = new ArrayDeque<>();
    ArrayList<Coordinate> visitCoordinate = new ArrayList<>();
    private final WorldMap worldMap;
    private boolean isFind = false;

    public BreadthFirstSearch(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public ArrayList<Coordinate> findShortestWay(Creature creatureThis) {

        cleanCoordinateParent(worldMap);

        ArrayList<Coordinate> pathToEntity = new ArrayList<>();

        queueCoordinate.add(creatureThis.getCoordinate()); // добавляем в очередь первые координаты

        while (!isFind) {
            Coordinate coordinateSoughtEntity = checkSoughtEntity(queueCoordinate, creatureThis);// проверяем - искомый ли здесь объект?

            if (coordinateSoughtEntity == null) { // если искомый объект не нашелся
                Coordinate visitedCoordinate = queueCoordinate.poll(); // посещенная координата из очереди
                visitCoordinate.add(visitedCoordinate); // добавляем в список Посещенные координаты

                try {
                    queueCoordinate = replenishQueue(Objects.requireNonNull(visitedCoordinate), queueCoordinate);// ищем соседей для объекта, который посетили и добавляем в очередь
                } catch (Exception e) {
                    return pathToEntity; //если закончились координаты в очереди возвращаем пустой путь
                }

                queueCoordinate.removeAll(visitCoordinate);// удаляем из очереди, все координаты которые посетили
            } else {
                pathToEntity = unfoldPathToEntity(coordinateSoughtEntity, creatureThis); //если нашли нужный объект через его родителей "разворачиваем" кратчайший путь
                isFind = true;
            }
        }
        return pathToEntity;
    }

    private ArrayList<Coordinate> unfoldPathToEntity(Coordinate coordinateSoughtEntity, Creature creatureThis) {
        ArrayList<Coordinate> coordinateList = new ArrayList<>();
        while (!(worldMap.getEntity(coordinateSoughtEntity).getCoordinate().equals(creatureThis.getCoordinate()))) {
            coordinateList.add(coordinateSoughtEntity);
            coordinateSoughtEntity = coordinateSoughtEntity.getParent();
        }
        Collections.reverse(coordinateList);
        return coordinateList;
    }

    private Queue<Coordinate> replenishQueue(Coordinate parentCoordinate, Queue<Coordinate> queue) {

        int startX = parentCoordinate.getCordX() - 1;
        if (startX < 0) {
            startX = 0;
        }

        int startY = parentCoordinate.getCordY() - 1;
        if (startY < 0) {
            startY = 0;
        }

        int endX = parentCoordinate.getCordX() + 1;
        if (endX >= worldMap.getMap().length) {
            endX = worldMap.getMap().length - 1;
        }

        int endY = parentCoordinate.getCordY() + 1;
        if (endY >= worldMap.getMap()[0].length) {
            endY = worldMap.getMap()[0].length - 1;
        }


        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                if (!(worldMap.getEntity(new Coordinate(i, j)) instanceof Predator) &&
                        !(worldMap.getEntity(new Coordinate(i, j)) instanceof Tree) &&
                        !(worldMap.getEntity(new Coordinate(i, j)) instanceof Rock)) {

                    if (worldMap.getEntity(i, j).getCoordinate().getParent() == null) {
                        worldMap.getEntity(i, j).getCoordinate().setParent(parentCoordinate);
                    }

                    queue.add(worldMap.getEntity(i, j).getCoordinate());
                }
            }
        }
        return queue;
    }

    private Coordinate checkSoughtEntity(Queue<Coordinate> queue, Creature creatureThis) {
        ArrayList<Coordinate> checkQueue = new ArrayList<>(queue);
        for (Coordinate coordinate : checkQueue) {
            if ((worldMap.getEntity(coordinate) instanceof Grass && creatureThis instanceof Herbivore) ||
                    (worldMap.getEntity(coordinate) instanceof Herbivore && creatureThis instanceof Predator)) {
                return coordinate;
            }
        }
        return null;
    }

    private void cleanCoordinateParent(WorldMap worldMap) {
        for (int i = 0; i < worldMap.getSizeX(); i++) {
            for (int j = 0; j < worldMap.getSizeY(); j++) {
                worldMap.getMap()[i][j].getCoordinate().setParent(null);
            }
        }
    }
}
