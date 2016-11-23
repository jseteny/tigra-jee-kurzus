package hu.tigra.jee.model;

import javax.enterprise.inject.Model;
import java.util.Arrays;
import java.util.List;


@Model
public class MineFieldModel {

    public List<MineFieldRow> rows() {
        return Arrays.asList(
                new MineFieldRow(),
                new MineFieldRow()
        );
    }
}
