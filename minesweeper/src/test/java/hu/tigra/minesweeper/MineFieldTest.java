package hu.tigra.minesweeper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MineFieldTest {

    @Test
    public void addBombAt_00() {
        // Given
        MineField mineField = new MineField(2, 2);

        // When
        mineField.addBomb(0, 0);

        // Then
        assertAt(1, 0, mineField, has(1));
        assertAt(0, 1, mineField, has(1));
        assertAt(1, 1, mineField, has(1));
    }

    @Test
    public void addBombAt_00_and_10() {
        // Given
        MineField mineField = new MineField(3, 2);

        // When
        mineField.addBomb(0, 0);
        mineField.addBomb(1, 0);

        // Then
        assertAt(0, 0, mineField, hasBomb());
        assertAt(1, 0, mineField, hasBomb());

        assertAt(2, 0, mineField, has(1));
        assertAt(0, 1, mineField, has(2));
        assertAt(1, 1, mineField, has(2));
        assertAt(2, 1, mineField, has(1));

        // TODO others are zero
    }

    @Test
    public void add_2_neighborBombs_somewhere() {
        // Given
        MineField mineField = new MineField(10, 7);

        // When
        mineField.addBomb(4, 3);
        mineField.addBomb(5, 3);

        // Then
        assertAt(3, 2, mineField, has(1));
        assertAt(4, 2, mineField, has(2));
        assertAt(5, 2, mineField, has(2));
        assertAt(6, 2, mineField, has(1));

        // TODO check more neighbours and others are zero
    }

    @Test
    public void widthHeightOk() {
        // Given
        MineField mineField = new MineField(1, 2);

        // When
        mineField.addBomb(0, 1);
    }

    private void assertAt(int x, int y, MineField mineField, int expected) {
        assertEquals("expected " + expected + " at " + x + "," + y, expected, mineField.at(x, y));
    }

    private int has(int n) {
        return n;
    }

    private int hasBomb() {
        return -9;
    }
}
