package org.myproject.entity.animate;

import org.myproject.entity.Entity;
import org.myproject.start.Main;
import org.myproject.world.Coordinate;

public abstract class Creature extends Entity {

    private int speed;
    private int sizeHP;

    public Creature(Coordinate coordinate) {
        super(coordinate);
        this.speed = 1;
        this.sizeHP = (int) (Math.random() * 40) + 1;
    }

    public abstract void makeMove();
}
