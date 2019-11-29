package ro.anud.celularautomata;

import java.util.Random;

public class Cell {
    private boolean alive;

    public static Cell createRandom(double percentage) {
        return createRandom(percentage, new Random());
    }

    public static Cell createRandom(double percentage, Random random) {
        var alive = true;
        if (random.nextDouble() > percentage) {
            alive = false;
        }
        return new Cell(alive);
    }

    public Cell(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public Cell setAlive(final boolean alive) {
        this.alive = alive;
        return this;
    }
}
