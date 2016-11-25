package hu.tigra.minesweeper;

import java.util.stream.IntStream;


public class MineField {

    public static final int BOMB = -9;
    private Integer[][] field;

    private final int width;
    private final int height;


    public MineField(int width, int height) {
        this.width = width;
        this.height = height;
        field = IntStream.range(0, height).mapToObj(y ->
                IntStream.range(0, width).mapToObj(x ->

                        0

                ).toArray(size -> new Integer[width]))
                .toArray(size -> new Integer[height][width]);
    }

    public void addBomb(int bombX, int bombY) {
        field = IntStream.range(0, height).mapToObj(y ->
                IntStream.range(0, width).mapToObj(x -> {
                    if (x == bombX && y == bombY) {
                        return BOMB;
                    } else if (field[y][x] == BOMB) {
                        return BOMB;
                    } else if (Math.abs(x - bombX) <= 1 && Math.abs(y - bombY) <= 1) {
                        return field[y][x] + 1;
                    } else {
                        return field[y][x];
                    }
                }).toArray(size -> new Integer[width]))
                .toArray(size -> new Integer[height][width]);
    }

    public int at(int x, int y) {
        return field[y][x];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
