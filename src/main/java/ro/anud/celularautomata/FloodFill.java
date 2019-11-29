package ro.anud.celularautomata;

import java.util.HashMap;

public class FloodFill {
    public static Grid<Integer> fill(Grid<Cell> cellGrid, Integer x, Integer y) {
        var grid = Grid.map(cellGrid, (cell, x1, y1) -> 0);

        fillRecursive(cellGrid,
                      grid,
                      x,
                      y,
                      new HashMap<>());
        return grid;
    }

    private static void fillRecursive(Grid<Cell> cellGrid,
                                      Grid<Integer> integerGrid,
                                      Integer x,
                                      Integer y,
                                      HashMap<Integer, Integer> ignore) {
        //        System.out.println("x + y " + (x + " " + y));
        //        System.out.println();
        //        System.out.println("x < cellGrid.size() " + (x < cellGrid.size()));
        //        System.out.println("x >= 0 " + (x >= 0));
        //        System.out.println("y < cellGrid.size() " + (y < cellGrid.size()));
        //        System.out.println("y >= 0 " + (y >= 0));
        //        System.out.println("!ignore.containsKey(x) " + (!ignore.containsKey(x)));
        //        System.out.println("!ignore.containsValue(y) " + (!ignore.containsValue(y)));

        System.out.println(ignore);
        if (x < cellGrid.size()
                && x >= 0
                && y < cellGrid.size()
                && y >= 0
                && !ignore.containsKey(x)
                && !ignore.containsValue(y)) {
            ignore.put(x, y);
            System.out.println(cellGrid.get(x).get(y).isAlive());
            if (cellGrid.get(x).get(y).isAlive()) {
                System.out.println("put");
                var number = integerGrid.get(x).get(y);

                integerGrid.get(x).set(y, 1);

                fillRecursive(cellGrid, integerGrid, x + 1, y, ignore);
                fillRecursive(cellGrid, integerGrid, x - 1, y, ignore);
                fillRecursive(cellGrid, integerGrid, x, y + 1, ignore);
                fillRecursive(cellGrid, integerGrid, x, y - 1, ignore);
                fillRecursive(cellGrid, integerGrid, x + 1, y + 1, ignore);
                fillRecursive(cellGrid, integerGrid, x + 1, y - 1, ignore);
                fillRecursive(cellGrid, integerGrid, x - 1, y - 1, ignore);
                fillRecursive(cellGrid, integerGrid, x - 1, y + 1, ignore);
            }
        }
    }


}
