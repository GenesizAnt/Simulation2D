package org.myproject.entity.animate;

import org.myproject.entity.Entity;
import org.myproject.world.Coordinate;
import org.myproject.world.WorldMap;

public abstract class Creature extends Entity {

    private int speed;
    private int sizeHP;

    public Creature(Coordinate coordinate) {
        super(coordinate);
        this.speed = 1;
        this.sizeHP = (int) (Math.random() * 40) + 1;
    }

    public abstract void makeMove(WorldMap worldMap);

    public void setSizeHP(int sizeHP) {
        this.sizeHP = sizeHP;
    }

    public int getSizeHP() {
        return sizeHP;
    }
}
