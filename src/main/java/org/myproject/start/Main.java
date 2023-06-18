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
                }
            }
        }


        for (int i = 0; i < coordinates.size(); i++) {
            System.out.println(coordinates.get(i));
        }

    }

}


