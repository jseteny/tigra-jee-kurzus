package hu.tigra.minesweeper;


public class MineField {

    public static final int BOMB = -9;
    private int[][] field;

    private final int width;
    private final int height;


    public MineField(int width, int height) {
        this.width = width;
        this.height = height;
        field = new int[height][width];
    }

    public void addBomb(int x, int y) {
        field[y][x] = BOMB;
        incrementNeighbours(x, y);
    }

    private void incrementNeighbours(int bombX, int bombY) {
        int startY = Math.max(0, bombY - 1);
        int endY = Math.min(height - 1, bombY + 1);

        int startX = Math.max(0, bombX - 1);
        int endX = Math.min(width - 1, bombX + 1);

        for (int y = startY; y <= endY; ++y) {
            for (int x = startX; x <= endX; ++x) {
                if (field[y][x] != BOMB) {
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
