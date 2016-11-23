package hu.tigra.minesweeper;


public class MineField {

    private int[][] field;

    private final int width;
    private final int height;


    public MineField(int width, int height) {
        this.width = width;
        this.height = height;
        field = new int[height][width];
    }

    public void addBomb(int x, int y) {
        field[y][x] = -9;
        incrementNeighbours(x, y);
    }

    private void incrementNeighbours(int bx, int by) {
        for (int y = Math.max(0, by - 1); y <= Math.min(height - 1, by + 1); ++y) {
            for (int x = Math.max(0, bx - 1); x <= Math.min(width - 1, bx + 1); ++x) {
                if (x != bx || y != by) {
                    field[y][x] = field[y][x] + 1;
                }
            }
        }

        // TODO field = field.stream().map()
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
