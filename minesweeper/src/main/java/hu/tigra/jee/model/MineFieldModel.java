package hu.tigra.jee.model;

import hu.tigra.minesweeper.Mask;
import hu.tigra.minesweeper.Mask.Explosion;
import hu.tigra.minesweeper.MineField;

import javax.enterprise.inject.Model;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@Model
public class MineFieldModel {

    private MineField mineField;
    private int[][] mask;

    private List<Integer> collumns;
    private List<MineFieldRow> rows;

    @SuppressWarnings("UnnecessaryLocalVariable")
    public MineFieldModel() {
        mineField = new MineField(8, 5);
        mineField.addBomb(4, 3);
        mineField.addBomb(5, 3);

        int[][] m = {
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1}
        };
        mask = m;

        collumns = IntStream.range(0, mineField.getWidth() - 1).boxed().collect(Collectors.toList());

        final int[] rowCount = {0};
        rows = Stream.generate(() -> new MineFieldRow(rowCount[0]++)).limit(mineField.getHeight()).collect(Collectors.toList());
    }

    public void click(int x, int y) {
        try {
            mask = Mask.unmask(mineField, mask, x, y);
        } catch (Explosion explosion) {
            // TODO
        }
    }

    public List<Integer> getColumns() {
        return collumns;
    }

    public List<MineFieldRow> getRows() {
        return rows;
    }


    public class MineFieldRow {

        private int r;


        public MineFieldRow(int r) {
            this.r = r;
        }

        public String cell(int c) {
            return (mask[r][c] == 0) ? String.valueOf(mineField.at(c, r)) : "";
        }

        public int getIndex() {
            return r;
        }
    }
}
