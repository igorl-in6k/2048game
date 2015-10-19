package game2048.core;

import static game2048.core.Constants.BASIS;

public class Cell {

    private int value;

    public Cell(int val) {
        value = val;
    }

    public Cell(Cell another) {
        value = another.value;
    }

    public Cell() {
        this(0);
    }

    public boolean isEmpty() {
        return value == 0;
    }

    public int getValue() {
        return value;
    }

    public boolean merge(Cell cell) {
        if ( !equals(cell) )
            return false;
        value *= BASIS;
        cell.reset();
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof Cell && this.value == ((Cell) obj).value;
    }

    public void reset() {
        value = 0;
    }
}
