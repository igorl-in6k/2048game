package game2048.core;

import java.util.Random;

public class CellFiller implements CellFillingStrategy {

    private Random rand = new Random();

    @Override
    public void fill(Cell cell) {
        if ( rand.nextInt(10) == 0 )
            cell.setValue(4);
        else
            cell.setValue(2);
    }
}
