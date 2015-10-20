package game2048.core;

import static game2048.core.Constants.SIZE;

public class GameFieldImpl implements GameField {

    protected Cell[][] cells = new Cell[SIZE][SIZE];
    private int score = 0;

    public GameFieldImpl() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
                cells[i][j] = new Cell();
        }
    }
    
    public GameFieldImpl(int[][] values) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int value = values[i][j];
                cells[i][j] = new Cell(value);
            }
        }
    }

    @Override
    public int[][] getValues() {
        int[][] values = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
                values[i][j] = cells[i][j].getValue();
        }
        return values;
    }

    @Override
    public int move(Direction direction) {
        switch (direction) {
            case LEFT:
                return moveLeft();
            case RIGHT:
                return moveRight();
            case UP:
                return moveUp();
            case DOWN:
                return moveDown();
            default:
                return 0;
        }
    }

    private int moveDown() {
        int currentMoveScore = 0;
        for (int i = 0; i < SIZE; i++) {
            currentMoveScore += moveRight(getColumn(i));
        }
        return currentMoveScore;
    }

    private int moveUp() {
        int currentMoveScore = 0;
        for (int i = 0; i < SIZE; i++) {
            currentMoveScore += moveLeft(getColumn(i));
        }
        return currentMoveScore;
    }

    private int moveRight() {
        int currentMoveScore = 0;
        for (int i = 0; i < SIZE; i++) {
            currentMoveScore += moveRight(cells[i]);
        }
        return currentMoveScore;
    }

    private int moveRight(Cell[] row) {
        int currentRowMoveScore = 0;
        shiftRight(row);
        for (int i = SIZE - 1; i > 0; i--) {
            Cell mergedCell = row[i];
            Cell mergingCell = row[i-1];
            if ( mergedCell.merge(mergingCell) )
                currentRowMoveScore += mergedCell.getValue();
        }
        shiftRight(row);
        return currentRowMoveScore;
    }

    private void shiftRight(Cell[] row) {
        for (int i = SIZE - 1; i > 0; i--) {
            if ( row[i].isEmpty() ) {
                int j = i - 1;
                while ( j >= 0 && row[j].isEmpty() )
                    j--;
                if (j >= 0 ) {
                    row[i].setValue(row[j]);
                    row[j].reset();
                }
            }
        }
    }

    private int moveLeft() {
        int currentMoveScore = 0;
        for (int i = 0; i < SIZE; i++) {
            currentMoveScore += moveLeft(cells[i]);
        }
        return currentMoveScore;
    }

    private int moveLeft(Cell[] row) {
        int currentRowMoveScore = 0;
        shiftLeft(row);
        for (int i = 0; i < SIZE - 1; i++) {
            Cell mergedCell = row[i];
            Cell mergingCell = row[i+1];
            if ( mergedCell.merge(mergingCell) )
                currentRowMoveScore += mergedCell.getValue();
        }
        shiftLeft(row);
        return currentRowMoveScore;
    }

    private void shiftLeft(Cell[] row) {
        for (int i = 0; i < SIZE - 1; i++) {
            if (row[i].isEmpty()) {
                int j = i + 1;
                while (j < SIZE && row[j].isEmpty())
                    j++;
                if (j < SIZE) {
                    row[i].setValue(row[j]);
                    row[j].reset();
                }
            }
        }
    }

    @Override
    public void fillRandomEmptyCell() {
    }

    @Override
    public boolean hasAvailableMove() {
        return false;
    }

    @Override
    public int getScore() {
        return 0;
    }

    private Cell[] getColumn(int idx) {
        Cell[] column = new Cell[SIZE];
        for (int i = 0; i < SIZE; i++) {
            column[i] = cells[i][idx];
        }
        return column;
    }
}
