package org.myproject.start;

import org.myproject.entity.animate.Creature;
import org.myproject.entity.animate.Herbivore;
import org.myproject.entity.inanimate.Grass;
import org.myproject.world.Action;
import org.myproject.world.BreadthFirstSearch;
import org.myproject.world.Coordinate;
import org.myproject.world.WorldMap;

import java.util.*;

public class Main {


    public static void main(String[] args) {


        WorldMap worldMap = new WorldMap(5,5);
        Action action = new Action(worldMap);

        worldMap.drawMap();

        BreadthFirstSearch search = new BreadthFirstSearch(worldMap);
        ArrayList<Coordinate> coordinates = new ArrayList<>();

        for (int i = 0; i < worldMap.getSizeX(); i++) {
            for (int j = 0; j < worldMap.getSizeY(); j++) {
                if (worldMap.getMap()[i][j] instanceof Herbivore) {

                    try {
                        coordinates = search.shortcutsSearch(((Herbivore) worldMap.getMap()[i][j]));
                    } catch (Exception e) {
                        coordinates.add(worldMap.getMap()[i][j].getCoordinate());
                    }


                    Coordinate coordinate = coordinates.get(0);
//                    coordinate.setParent(null);
                    coordinates.remove(0);


                        coordinates = createArrayListFromParent(coordinate, worldMap);

//                    coordinates = pathFindToEntity(coordinate, (Creature) worldMap.getMap()[i][j]);

                }
            }
        }



//        ArrayList<Coordinate> coor = new ArrayList<>();
//        coor.add(coordinate);
//
//        while (worldMap.getEntity(coordinate) instanceof Herbivore) {
//            Coordinate coordinate1 = coor.get(0);
//            coor.add(coordinate1.getParent());
//        }
//
        for (int i = 0; i < coordinates.size(); i++) {
            System.out.println(coordinates.get(i));
        }


//        search.shortcutsSearch();

//
//        worldMap.drawMap();


    }

    public static ArrayList<Coordinate> createArrayListFromParent(Coordinate coordinate, WorldMap worldMap) {
        ArrayList<Coordinate> arrayList = new ArrayList<>(); // Создаем новый ArrayList

        // Используя цикл, добавляем новые объекты Coordinate, пока не достигнем объекта с parent'ом равным null
        while (!(worldMap.getEntity(coordinate) instanceof Herbivore)) {
            arrayList.add(coordinate); // Добавляем текущий объект в ArrayList
            coordinate = coordinate.getParent(); // Заменяем текущий объект его parent'ом
        }

        return arrayList; // Возвращаем полученный ArrayList
    }

    public static ArrayList<Coordinate> pathFindToEntity(Coordinate coordinate, Creature creature) {
        ArrayList<Coordinate> path = new ArrayList<>();

        path = findPathRecursion(coordinate, creature, path);

        Collections.reverse(path);

        return path;
    }

    public static ArrayList<Coordinate> findPathRecursion(Coordinate coordinate, Creature creature, ArrayList<Coordinate> path) {

        ArrayList<Coordinate> path1 = path;

        path1.add(coordinate);

        if (coordinate.equals(creature.getCoordinate())) {
            findPathRecursion(coordinate.getParent(), creature, path1);
        } else {
            return path1;
        }

        return path1;
    }
}


