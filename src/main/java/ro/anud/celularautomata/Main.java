package ro.anud.celularautomata;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.stream.Stream.iterate;
import static ro.anud.celularautomata.Grid.print;
import static ro.anud.celularautomata.GameOfLife.doSimultationStep;

public class Main {

    public static void main(String[] noArgs) {
        Random random = new Random(1);
        Integer size = 100;

        Grid<Cell> grid = iterate(0, i -> i < size, i -> i + 1)
                .map(i -> {
                    var array = new ArrayList<Cell>();
                    iterate(0, j -> j < size, j -> j + 1)
                            .forEach(j -> array.add(Cell.createRandom(0.45D, random)));
                    return array;
                })
                .collect(Collectors.toCollection(Grid<Cell>::new));

        print(grid, (cell, x, y) -> {
            if (cell.isAlive()) {
                return "X";
            } else {
                return " ";
            }
        });

        System.out.println();
        for (int i = 0; i < 10; i++) {
            grid = doSimultationStep(grid, 5, 2);
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
        print(flood.getResponse(), (cell, x, y) -> {
            if (cell.isAlive()) {
                return "X";
            } else {
                return " ";
            }
        });
    }
}
