package hu.tigra.minesweeper;

import hu.tigra.minesweeper.Mask.Explosion;
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
    public void unmaskAt_11() throws Exception {

        Integer[][] previous = {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        Integer[][] expected = {
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
        };
        Integer[][] actual = Mask.unmask(mineField, previous, 1, 1);
        for (int r = 0; r < expected.length; r++) {
            assertThat(actual[r]).as("row " + r).containsExactly(expected[r]);
        }
    }

    @Test
    public void unmaskAt_00() throws Exception {

        Integer[][] previous = {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        Integer[][] expected = {
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
        };
        Integer[][] actual = Mask.unmask(mineField, previous, 0, 0);
        for (int r = 0; r < expected.length; r++) {
            assertThat(actual[r]).as("row " + r).containsExactly(expected[r]);
        }
    }

    @Test
    public void unmaskAt_30() throws Exception {

        Integer[][] previous = {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        Integer[][] expected = {
                {1, 1, 1, 0, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        Integer[][] actual = Mask.unmask(mineField, previous, 3, 0);
        for (int r = 0; r < expected.length; r++) {
            assertThat(actual[r]).as("row " + r).containsExactly(expected[r]);
        }
    }

    @Test(expected = Explosion.class)
    public void unmaskAt_41() throws Exception {

        Integer[][] previous = {
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
        };
        Mask.unmask(mineField, previous, 4, 1);
    }
}
