package ro.anud.celularautomata;

import java.util.ArrayList;
import java.util.Random;

import static java.util.stream.Stream.iterate;
import static ro.anud.celularautomata.Grid.print;
import static ro.anud.celularautomata.GameOfLife.doSimultationStep;

public class Main {

    public static void main(String[] args) {
        Random random = new Random(1);
        Integer size = 100;

        Grid<Cell> grid = iterate(0, i -> i < size, i -> i + 1)
                .map(i -> new ArrayList<Cell>())
                .peek(cells -> iterate(0, i -> i < size, i -> i + 1)
                        .forEach(i -> cells.add(Cell.createRandom(0.45D, random))))
                .reduce(new Grid<>(), (arrayList, cells) -> {
                    arrayList.add(cells);
                    return arrayList;
                }, (arrayLists, arrayLists2) -> {
                    arrayLists.addAll(arrayLists2);
                    return arrayLists;
                });
        print(grid, (cell, x, y) -> {
            if (cell.isAlive()) {
                return "X";
            } else {
                return " ";
            }
        });

        System.out.println();
        for (int i = 0; i < 10; i++) {
            grid = doSimultationStep(grid, 3, 5);
        }
        System.out.println();
        print(grid, (cell, x, y) -> {
            if (cell.isAlive()) {
                return "X";
            } else {
                return " ";
            }
        });

        System.out.println();

        var flood = FloodFill.fill(grid, 36, 10);
        print(flood.getResponse(), (cell, x, y) ->  {
            if (cell.isAlive()) {
                return "X";
            } else {
                return " ";
            }
        });
    }
}
