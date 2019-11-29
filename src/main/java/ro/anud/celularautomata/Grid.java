package ro.anud.celularautomata;


import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Grid<T> extends ArrayList<ArrayList<T>> {
    public interface Mapper<T, U> {
        U apply(T cell, Integer x, Integer y);
    }

    public static <T> void print(Grid<T> arrayLists, Grid.Mapper<T, String> print) {
        AtomicInteger x = new AtomicInteger(0);
        System.out.print(" |");
        Stream.iterate(0, i -> i + 1)
                .limit(arrayLists.size())
                .forEach(System.out::print);
        System.out.println("|");

        arrayLists.forEach(cells -> {
            AtomicInteger y = new AtomicInteger(0);
            System.out.print(x + "|");
            cells.stream()
                    .map(cell -> {
                        var character = print.apply(cell, x.get(), y.get());
                        y.set(y.get() + 1);
                        return character;
                    })
                    .forEach(System.out::print);
            System.out.print("|" + x);
            System.out.println();
            x.set(x.get() + 1);
        });
        System.out.print(" |");
        Stream.iterate(0, i -> i + 1)
                .limit(arrayLists.size())
                .forEach(System.out::print);
        System.out.println("|");

    }

    public static <T, U> Grid<U> map(Grid<T> map, Mapper<T, U> mapper) {
        AtomicInteger x = new AtomicInteger(0);
        var list = map.stream()
                .map(cells -> {
                    AtomicInteger y = new AtomicInteger(0);
                    var array = cells.stream()
                            .map(cell -> {
                                var value = mapper.apply(cell, x.get(), y.get());
                                y.set(y.get() + 1);
                                return value;
                            })
                            .collect(Collectors.toCollection(ArrayList::new));
                    x.set(x.get() + 1);
                    return new ArrayList<>(array);
                }).collect(Collectors.toCollection(ArrayList::new));
        var grid = new Grid<U>();
        grid.addAll(list);
        return grid;
    }
}
