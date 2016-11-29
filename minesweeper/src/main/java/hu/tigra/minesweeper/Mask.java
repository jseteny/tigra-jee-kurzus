package hu.tigra.minesweeper;

import static hu.tigra.minesweeper.MineField.BOMB;

public class Mask {

    public static class Explosion extends Exception {
    }


    public static int[][] unmask(MineField mineField, int[][] mask, int clickX, int clickY) throws Explosion {
        if (mineField.at(clickX, clickY) == BOMB) {
            throw new Explosion();
        }

        int[][] newMask = deepCopy(mask);
        return um(mineField, mask, newMask, clickX, clickY);
    }

    private static int[][] um(MineField mineField, int[][] mask, int[][] newMask, int clickX, int clickY) {
        int startY = Math.max(0, clickY - 1);
        int endY = Math.min(mineField.getHeight() - 1, clickY + 1);

        int startX = Math.max(0, clickX - 1);
        int endX = Math.min(mineField.getWidth() - 1, clickX + 1);

        for (int y = startY; y <= endY; ++y) {
            for (int x = startX; x <= endX; ++x) {
                if (mineField.at(x, y) == 0 && newMask[y][x] == 1) {
                    newMask[y][x] = 0;
                    um(mineField, mask, newMask, x, y);
                }
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
