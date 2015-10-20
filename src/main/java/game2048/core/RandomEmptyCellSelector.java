package game2048.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomEmptyCellSelector implements CellSelectionStrategy {

    private Random rand = new Random();

    @Override
    public Cell getCell(Cell[][] cells) {
        List<Cell> emptyCells = new ArrayList<>();
        for (int i = 0; i < Constants.SIZE; i++) {
            for (int j = 0; j < Constants.SIZE; j++) {
                if ( cells[i][j].isEmpty() )
                    emptyCells.add(cells[i][j]);
            }
        }
        if ( emptyCells.size() == 0 )
            return null;
        return emptyCells.get(rand.nextInt(emptyCells.size()));
    }
}
