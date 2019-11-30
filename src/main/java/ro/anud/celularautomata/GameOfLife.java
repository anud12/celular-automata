package ro.anud.celularautomata;

import ro.anud.celularautomata.Cell;
import ro.anud.celularautomata.Grid;

import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Stream.iterate;
import static ro.anud.celularautomata.Grid.map;

public class GameOfLife {

    public static Grid<Cell> doSimultationStep(Grid<Cell> grid, Integer birthLimit, Integer deathLimit) {
        var aliveNeighboursGrid = map(grid, (cell, x, y) -> countAliveNeighbours(grid, x, y));
        return map(grid, (cell, x, y) -> {
            if (cell.isAlive()) {
                if (aliveNeighboursGrid.get(x).get(y) > deathLimit) {
                    return new Cell(true);
                } else {
                    return new Cell(false);
                }
            } else {
                if (aliveNeighboursGrid.get(x).get(y) < birthLimit) {
                    return new Cell(false);
                } else {
                    return new Cell(true);
                }
            }
        });
    }

    public static Integer countAliveNeighbours(Grid<Cell> map, int originX, int originY) {
        AtomicInteger count = new AtomicInteger(0);
        iterate(originX - 1, x -> x + 1)
                .limit(3)
                .filter(x -> x != 0)
                .forEach(x -> iterate(originY - 1, y -> y + 1)
                        .limit(3)
                        .filter(y -> y != 0)
                        .forEach(y -> {
                            if (x < 0
                                    || x >= map.size()
                                    || y < 0
                                    || y >= map.size()) {
                                count.set(count.get() + 1);
                                return;
                            }
                            if (map.get(x).get(y).isAlive()) {
                                count.set(count.get() + 1);
                            }
                        }));
        return count.get();
    }
}
