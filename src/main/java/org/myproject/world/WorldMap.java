package org.myproject.world;

import org.myproject.entity.Entity;
import org.myproject.entity.inanimate.Ground;

public class WorldMap {

    private int sizeX;
    private int sizeY;
    private Entity[][] map;

    public WorldMap(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.map = new Entity[sizeX][sizeY];
        createMap();
    }

    public void drawMap() {
        for (int i = 0; i < sizeX; i++) {
            System.out.println();
            for (int j = 0; j < sizeY; j++) {
                System.out.print(map[i][j]);
            }
        }
    }

    private void createMap() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                map[i][j] = new Ground(new Coordinate(i, j));
            }
        }
    }

    public Entity getEntity(Coordinate coordinate) {
        return map[coordinate.getCordX()][coordinate.getCordY()];
    }

    public Entity getEntity(int x, int y) {
        return map[x][y];
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public Entity[][] getMap() {
        return map;
    }
}
