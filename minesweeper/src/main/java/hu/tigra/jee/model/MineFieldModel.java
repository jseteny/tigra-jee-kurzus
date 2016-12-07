package hu.tigra.jee.model;

import hu.tigra.minesweeper.Mask;
import hu.tigra.minesweeper.Mask.Explosion;
import hu.tigra.minesweeper.MineField;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@Named
@javax.enterprise.context.ApplicationScoped
public class MineFieldModel implements Serializable {

    private MineField mineField;
    private Integer[][] mask;

    private List<Integer> collumns;
    private List<MineFieldRow> rows;

    public MineFieldModel() {
        init();
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    private void init() {

        initMineField();

        initMask();

        collumns = IntStream.range(0, mineField.getWidth()).boxed().collect(Collectors.toList());

        final int[] rowCount = {0};
        rows = Stream.generate(() -> new MineFieldRow(rowCount[0]++)).limit(mineField.getHeight()).collect(Collectors.toList());
    }

    private void initMineField() {
        int bombCount = 8;
        mineField = new MineField(12, 5);

        int c = 0;
        Random rand = new Random();
        while (c < bombCount) {
            int x = rand.nextInt(mineField.getWidth());
            int y = rand.nextInt(mineField.getHeight());

            if (mineField.addBomb(x, y))
                ++c;
        }
    }

    private void initMask() {
        mask = IntStream.range(0, mineField.getHeight()).mapToObj(y ->
                IntStream.range(0, mineField.getWidth()).mapToObj(x ->

                        1

                ).toArray(size -> new Integer[mineField.getWidth()]))
                .toArray(size -> new Integer[mineField.getHeight()][mineField.getWidth()]);
    }

    public void click(int x, int y) {
        try {
            mask = Mask.unmask(mineField, mask, x, y);
            EventBus eventBus = EventBusFactory.getDefault().eventBus();
            eventBus.publish("/reload", 1234);
        } catch (Explosion explosion) {
            // TODO
            mask[y][x] = 0;
            EventBus eventBus = EventBusFactory.getDefault().eventBus();
            eventBus.publish("/reload", 1234);
        }
    }

    public void restart() {
        init();
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish("/reload", 1234);
    }

    public List<Integer> getColumns() {
        return collumns;
    }

    public List<MineFieldRow> getRows() {
        return rows;
    }


    @SuppressWarnings("WeakerAccess")
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
