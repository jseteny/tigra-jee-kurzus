package hu.tigra.minesweeper;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MaskTest {

    private static final int B = MineField.BOMB;

    private MineField mineField;

    @Before
    public void setup() {
        int[][] field = {
                {0, 0, 0, 1, 1},
                {0, 0, 0, 2, B},
                {0, 0, 0, 2, B},
                {1, 1, 2, 2, 2},
                {1, B, 2, B, 1}
        };
        mineField = new MineField(field);
    }

    @Test
    public void unmaskAt_11() {

        int[][] previous = {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        int[][] expected = {
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        int[][] actual = Mask.unmask(mineField, previous, 1, 1);
        for (int r = 0; r < expected.length; r++) {
            assertThat(actual[r]).as("row " + r).containsExactly(expected[r]);
        }
    }

    @Test
    public void unmaskAt_00() {

        int[][] previous = {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        int[][] expected = {
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        int[][] actual = Mask.unmask(mineField, previous, 0, 0);
        for (int r = 0; r < expected.length; r++) {
            assertThat(actual[r]).as("row " + r).containsExactly(expected[r]);
        }
    }
}