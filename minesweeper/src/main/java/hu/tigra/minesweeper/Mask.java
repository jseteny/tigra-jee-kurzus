package hu.tigra.minesweeper;

import static hu.tigra.minesweeper.MineField.BOMB;

public class Mask {

    public static class Explosion extends Exception {
    }


    public static Integer[][] unmask(MineField mineField, Integer[][] mask, int clickX, int clickY) throws Explosion {
        if (mineField.at(clickX, clickY) == BOMB) {
            throw new Explosion();
        }

        Integer[][] newMask = deepCopy(mask);
        return unmaskCell(mineField, mask, newMask, clickX, clickY);
    }

    private static Integer[][] unmaskCell(MineField mineField, Integer[][] mask, Integer[][] newMask, int x, int y) {
        if (mineField.at(x, y) == 0 && newMask[y][x] == 1) {
            newMask[y][x] = 0;
            return unmaskNeighbours(mineField, mask, newMask, x, y);

        } else if (mineField.at(x, y) != BOMB) {
            newMask[y][x] = 0;
            return newMask;

        } else {
            return newMask;
        }
    }

    private static Integer[][] unmaskNeighbours(MineField mineField, Integer[][] mask, Integer[][] newMask, int cellX, int cellY) {
        int startY = Math.max(0, cellY - 1);
        int endY = Math.min(mineField.getHeight() - 1, cellY + 1);

        int startX = Math.max(0, cellX - 1);
        int endX = Math.min(mineField.getWidth() - 1, cellX + 1);

        for (int y = startY; y <= endY; ++y) {
            for (int x = startX; x <= endX; ++x) {
                unmaskCell(mineField, mask, newMask, x, y);
            }
        }
        return newMask;
    }

    /**
     * http://stackoverflow.com/questions/1564832/how-do-i-do-a-deep-copy-of-a-2d-array-in-java
     */
    @SuppressWarnings("Convert2MethodRef")
    private static Integer[][] deepCopy(Integer[][] matrix) {
        return java.util.Arrays.stream(matrix).map(el -> el.clone()).toArray($ -> matrix.clone());
    }
}
