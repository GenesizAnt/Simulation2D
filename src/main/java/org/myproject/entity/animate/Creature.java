package org.myproject.entity.animate;

import org.myproject.entity.Entity;
import org.myproject.world.Coordinate;
import org.myproject.world.WorldMap;

public abstract class Creature extends Entity {

    private int speed;
    private int health;

    public Creature(Coordinate coordinate) {
        super(coordinate);
        this.speed = 1;
        this.health = (int) (Math.random() * 40) + 1;
    }

    public abstract void makeMove(WorldMap worldMap);

    public void setHealth(int sizeHP) {
        this.health = sizeHP;
    }

    public int getHealth() {
        return health;
    }

    public void searchFastTrack(WorldMap worldMap) {

    }
}
