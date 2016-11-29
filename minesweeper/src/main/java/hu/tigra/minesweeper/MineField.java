package hu.tigra.minesweeper;


public class MineField {

    public static final int BOMB = -9;

    private final int[][] field;
    private final int width;
    private final int height;


    public MineField(int width, int height) {
        this.width = width;
        this.height = height;
        field = new int[height][width];
    }

    public MineField(int[][] field) {
        this.field = field;
        width = field.length;
        height = field[0].length;
    }

    public void addBomb(int x, int y) {
        field[y][x] = BOMB;
        incrementNeighbours(x, y);
    }

    private void incrementNeighbours(int bombX, int bombY) {
        int startX = Math.max(0, bombX - 1);
        int endX = Math.min(width - 1, bombX + 1);

        int startY = Math.max(0, bombY - 1);
        int endY = Math.min(height - 1, bombY + 1);

        for (int x = startX; x <= endX; ++x) {
            for (int y = startY; y <= endY; ++y) {
                if (field[y][x] != BOMB) {
                    field[y][x] = field[y][x] + 1;
                }
            }
        }
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
