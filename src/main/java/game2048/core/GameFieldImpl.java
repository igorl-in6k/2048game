package game2048.core;

import static game2048.core.Constants.SIZE;

public class GameFieldImpl implements GameField {

    protected Cell[][] cells = new Cell[SIZE][SIZE];
    private CellSelectionStrategy selector;
    private CellFillingStrategy filler;
    private int score = 0;

    public GameFieldImpl() {
        this(new RandomEmptyCellSelector(), new CellFiller());
    }

    public GameFieldImpl(CellSelectionStrategy s, CellFillingStrategy f) {
        selector = s;
        filler = f;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
                cells[i][j] = new Cell();
        }
    }
    
    public GameFieldImpl(int[][] values, CellSelectionStrategy s, CellFillingStrategy f) {
        selector = s;
        filler = f;
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
    public boolean move(Direction direction) {
        int[][] beforeMove = getValues();
        switch (direction) {
            case LEFT:
                score += moveLeft(); break;
            case RIGHT:
                score += moveRight(); break;
            case UP:
                score += moveUp(); break;
            case DOWN:
                score += moveDown(); break;
        }
        return !equal(beforeMove, getValues());
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
        filler.fill(selector.getCell(cells));
    }

    @Override
    public boolean hasAvailableMove() {
        return hasEmptyCell() || hasAtLeastOneMerge();
    }

    @Override
    public int getScore() {
        return score;
    }

    private boolean hasEmptyCell() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
                if ( cells[i][j].isEmpty() )
                    return true;
        }
        return false;
    }

    private boolean hasAtLeastOneMerge() {
        for (int i = 0; i < SIZE - 1; i++) {
            for (int j = 0; j < SIZE - 1; j++)
                if ( (cells[i][j].equals(cells[i+1][j])) ||
                        (cells[i][j].equals(cells[i][j+1])) )
                    return true;
        }
        return false;
    }

    private Cell[] getColumn(int idx) {
        Cell[] column = new Cell[SIZE];
        for (int i = 0; i < SIZE; i++) {
            column[i] = cells[i][idx];
        }
        return column;
    }

    private boolean equal(int[][] arr1, int[][] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[i].length; j++) {
                if ( arr1[i][j] != arr2[i][j] )
                    return false;
            }
        }
        return true;
    }
}
