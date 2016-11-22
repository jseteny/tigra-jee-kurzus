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
        assertEquals("1 at 1,0", 1, mineField.at(1, 0));
        assertEquals("1 at 0,1", 1, mineField.at(0, 1));
        assertEquals("1 at 1,1", 1, mineField.at(1, 1));
    }

    @Test
    public void addBombAt_00_and_10() {
        // Given
        MineField mineField = new MineField(3, 2);

        // When
        mineField.addBomb(0, 0);
        mineField.addBomb(1, 0);

        // Then
        assertEquals("1 at 2,0", 1, mineField.at(2, 0));
        assertEquals("2 at 0,1", 2, mineField.at(0, 1));
        assertEquals("2 at 1,1", 2, mineField.at(1, 1));
        assertEquals("1 at 2,1", 1, mineField.at(2, 1));
    }

    @Test
    public void add_2_neighborBombs_somewhere() {
        // Given
        MineField mineField = new MineField(10, 7);

        // When
        mineField.addBomb(4, 3);
        mineField.addBomb(5, 3);

        // Then
        assertEquals("1 at 3,2", 1, mineField.at(3, 2));
        assertEquals("2 at 4,2", 2, mineField.at(4, 2));
        assertEquals("2 at 5,2", 2, mineField.at(5, 2));
        assertEquals("1 at 6,2", 1, mineField.at(6, 2));

        // TODO check more neighbours
    }
}
