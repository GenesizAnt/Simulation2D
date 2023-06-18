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
    private WorldMap worldMap;
    private boolean isFind = false;

    public BreadthFirstSearch(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public ArrayList<Coordinate> shortcutsSearch(Creature creature) {

        ArrayList<Coordinate> pathFindToEntity = new ArrayList<>();

//        creature.getCoordinate().setParent(creature.getCoordinate());
        queue.add(creature.getCoordinate()); // добавляем в очередь первые координаты

        while (!isFind) {

            Coordinate coordinateFindObj = checkFindEntity(queue, creature);// проверяем - искомый ли здесь объект?

            if (coordinateFindObj == null) { // если искомый объект не нашелся

                Coordinate x = queue.poll();

                if (x == null) {
                    System.out.println();
                }

                listVisit.add(x); // достаем из очереди объект и добавляем в Посещенные координаты

                queue = replenishQueue(x, queue);
                // ищем соседей для объекта, который посетили и добавляем в очередь


                queue.removeAll(listVisit);// удаляем из очереди, всех кого посетили

                if (queue.isEmpty()) {
                    System.out.println();
                }

            } else {
//                removeAllBut(queue, coordinateFindObj);
//                pathFindToEntity.add(coordinateFindObj);
                pathFindToEntity = pathFindToEntity(checkFindEntity(queue, creature), creature);
                isFind = true;
            }

        }

        return pathFindToEntity;
    }

    private boolean removeAllBut(Queue<Coordinate> queue, Coordinate coordinateFindObj) {
        return Collections.singleton(coordinateFindObj).removeIf(x -> !queue.contains(x));
    }

    private ArrayList<Coordinate> pathFindToEntity(Coordinate coordinate, Creature creature) {
        ArrayList<Coordinate> path = new ArrayList<>();

        path = findPathRecursion(coordinate, creature, path);

        Collections.reverse(path);

        return path;
    }

    private ArrayList<Coordinate> findPathRecursion(Coordinate coordinate, Creature creature, ArrayList<Coordinate> path) {

        ArrayList<Coordinate> path1 = path;

        path1.add(coordinate);

        if (coordinate.equals(creature.getCoordinate())) {
            findPathRecursion(coordinate.getParent(), creature, path1);
        } else {
            return path1;
        }

        return path1;
    }

    private Queue<Coordinate> replenishQueue(Coordinate parent, Queue<Coordinate> queue) {
        Queue<Coordinate> childEntity = queue;
//        childEntity.add(parent);

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
                if (!(worldMap.getEntity(new Coordinate(i, j)) instanceof Herbivore) &&
                        !(worldMap.getEntity(new Coordinate(i, j)) instanceof Predator) &&
                        !(worldMap.getEntity(new Coordinate(i, j)) instanceof Tree) &&
                        !(worldMap.getEntity(new Coordinate(i, j)) instanceof Rock)) {

                    if (worldMap.getEntity(i, j).getCoordinate().getParent() == null) {
                        worldMap.getEntity(i, j).getCoordinate().setParent(parent);
                    }
                    childEntity.add(worldMap.getEntity(i, j).getCoordinate());

//                    childEntity.add(new Coordinate(i, j, parent));

                }
            }
        }

        return childEntity;
    }

    private Coordinate checkFindEntity(Queue<Coordinate> queue, Creature creature) {
        ArrayList<Coordinate> queueCheck = new ArrayList<>();
        for (Coordinate coordinate : queue) {
            queueCheck.add(coordinate);
        }
//        Coordinate coordinateFindObj = queueCheck.get(0);
//        if ((worldMap.getEntity(coordinateFindObj) instanceof Grass && creature instanceof Herbivore) ||
//                (worldMap.getEntity(coordinateFindObj) instanceof Herbivore && creature instanceof Predator)) {
//            return queue.peek();
//        }


        for (int i = 0; i < queueCheck.size(); i++) {

//            if ((worldMap.getEntity(queueCheck.get(i)) instanceof Grass && creature instanceof Herbivore)) {
//                return queueCheck.get(i);
//            }

            // работает
//        for (int i = 0; i < queueCheck.size(); i++) {
//
            if ((worldMap.getEntity(queueCheck.get(i)) instanceof Grass && creature instanceof Herbivore) ||
                    (worldMap.getEntity(queueCheck.get(i)) instanceof Herbivore && creature instanceof Predator)) {
                return queueCheck.get(i);
            }

        }

//        while (!queueCheck.isEmpty()) {
//            if (worldMap.getEntity(Objects.requireNonNull(queueCheck.peek())) instanceof Grass && creature instanceof Herbivore ||
//                    worldMap.getEntity(Objects.requireNonNull(queueCheck.peek())) instanceof Herbivore && creature instanceof Predator) {
//
//            }
//        }
        return null;
    }
}
