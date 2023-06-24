package org.myproject.world;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class Coordinate {

    private int cordX;
    private int cordY;
    private Coordinate parent;

    public Coordinate(int sizeX, int sizeY) {
        this.cordX = sizeX;
        this.cordY = sizeY;
    }

    public Coordinate(int cordX, int cordY, Coordinate parent) {
        this.cordX = cordX;
        this.cordY = cordY;
        this.parent = parent;
    }

    public int getCordX() {
        return cordX;
    }

    public void setCordX(int cordX) {
        this.cordX = cordX;
    }

    public int getCordY() {
        return cordY;
    }

    public void setCordY(int cordY) {
        this.cordY = cordY;
    }

    public Coordinate getParent() {
        return parent;
    }

    public void setParent(Coordinate parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return cordX == that.cordX && cordY == that.cordY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cordX, cordY);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "cordX=" + cordX +
                ", cordY=" + cordY +
                '}';
    }
}

//class RandomElementFromHashMap {
//    public static void main(String args[]) {
//        HashMap<String, Integer> map = new HashMap<String, Integer>();
//        map.put("apple", 1);
//        map.put("banana", 2);
//        map.put("orange", 3);
//        int randomIndex = new Random().nextInt(map.size());
//        String key = map.keySet().toArray()[randomIndex].toString();
//        Integer value = map.get(key);
//        System.out.println(value);
//    }
//}
