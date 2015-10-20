package game2048.core;

import static game2048.core.Constants.BASIS;

public class Cell {

    private int value;

    public Cell(int val) {
        if (val != 0 && !isPowerOfBasis(val))
            throw new IllegalArgumentException("Cell value (" + val
                    + ") should be a power of " + BASIS);
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

    public void setValue(Cell cell) {
        value = cell.getValue();
    }

    public boolean merge(Cell cell) {
        if ( !equals(cell) )
            return false;
        value *= BASIS;
        cell.reset();
        return true;
    }

    private boolean isPowerOfBasis(int num) {
        if ( num <= 1 || num % BASIS != 0 )
            return false;
        int pow = BASIS;
        while ( pow < num ) {
            pow *= BASIS;
        }
        return pow == num;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof Cell && this.value == ((Cell) obj).value;
    }

    public void reset() {
        value = 0;
    }
}
