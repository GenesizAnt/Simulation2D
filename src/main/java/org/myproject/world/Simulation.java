package org.myproject.world;

public class Simulation {

    WorldMap worldMap = new WorldMap(10,10);
    Action action = new Action(worldMap);

    public void startSimulation() {
        action.initActions(5,3,5,7,10);

        while (true) {

            action.turnActions();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
