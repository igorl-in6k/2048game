package game2048.core;

public interface GameField {

    int[][] getValues();

    int move(Direction direction);

    void fillRandomEmptyCell();

    boolean hasAvailableMove();

    int getScore();

}
