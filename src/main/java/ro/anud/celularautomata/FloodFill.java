package ro.anud.celularautomata;

import java.util.ArrayList;
import java.util.List;

public class FloodFill {
    private static class Point {

        public Integer x;
        public Integer y;

        Point(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Result<T> {
        private int size;
        private T response;

        public int getSize() {
            return size;
        }

        public Result<T> setSize(final int size) {
            this.size = size;
            return this;
        }

        public T getResponse() {
            return response;
        }

        public Result<T> setResponse(final T response) {
            this.response = response;
            return this;
        }
    }

    public static Result<Grid<Cell>> fill(Grid<Cell> cellGrid, Integer x, Integer y) {
        var grid = Grid.map(cellGrid, (cell, x1, y1) -> new Cell(false));

        var size = fillRecursive(cellGrid,
                                 grid,
                                 x,
                                 y,
                                 new ArrayList<>());
        return new Result<Grid<Cell>>()
                .setSize(size)
                .setResponse(grid);
    }

    private static Integer fillRecursive(Grid<Cell> cellGrid,
                                         Grid<Cell> integerGrid,
                                         Integer x,
                                         Integer y,
                                         List<Point> ignore) {
        var cond1 = x < cellGrid.size();
        var cond2 = x > -1;
        var cond3 = y < cellGrid.size();
        var cond4 = y > -1;

        if (ignore.stream().anyMatch(point -> point.x.equals(x) && point.y.equals(y))) {
            return 0;
        }

        ignore.add(new Point(x, y));
        if (cond1
                && cond2
                && cond3
                && cond4) {
            if (cellGrid.get(x).get(y).isAlive()) {
                integerGrid.get(x).set(y, new Cell(true));
                return 1
                        + fillRecursive(cellGrid, integerGrid, x + 1, y, ignore)
                        + fillRecursive(cellGrid, integerGrid, x - 1, y, ignore)
                        + fillRecursive(cellGrid, integerGrid, x, y + 1, ignore)
                        + fillRecursive(cellGrid, integerGrid, x, y - 1, ignore)
                        + fillRecursive(cellGrid, integerGrid, x + 1, y + 1, ignore)
                        + fillRecursive(cellGrid, integerGrid, x + 1, y - 1, ignore)
                        + fillRecursive(cellGrid, integerGrid, x - 1, y - 1, ignore)
                        + fillRecursive(cellGrid, integerGrid, x - 1, y + 1, ignore);
            }
        }
        return 0;
    }

}
