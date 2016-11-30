package hu.tigra.minesweeper;

import static hu.tigra.minesweeper.MineField.BOMB;

public class Mask {

    public static class Explosion extends Exception {
    }


    public static int[][] unmask(MineField mineField, int[][] mask, int clickX, int clickY) throws Explosion {
        if (mineField.at(clickX, clickY) == BOMB) {

        }

        int[][] newMask = deepCopy(mask);
        return unmaskCell(mineField, mask, newMask, clickX, clickY);
    }

    private static int[][] unmaskCell(MineField mineField, int[][] mask, int[][] newMask, int x, int y) {
        if () {
            newMask[y][x] = 0;
            return unmaskNeighbours(mineField, mask, newMask, x, y);

        } else if () {
            newMask[y][x] = 0;
            return newMask;

        } else {
            return newMask;
        }
    }

    private static int[][] unmaskNeighbours(MineField mineField, int[][] mask, int[][] newMask, int cellX, int cellY) {
        int startY =
        int endY =

        int startX =
        int endX =

        for (int y = startY; y <= endY; ++y) {
            for (int x = startX; x <= endX; ++x) {


            }
        }
        return newMask;
    }

    /**
     * http://stackoverflow.com/questions/1564832/how-do-i-do-a-deep-copy-of-a-2d-array-in-java
     */
    @SuppressWarnings("Convert2MethodRef")
    private static int[][] deepCopy(int[][] matrix) {
        return java.util.Arrays.stream(matrix).map(el -> el.clone()).toArray($ -> matrix.clone());
    }
}
