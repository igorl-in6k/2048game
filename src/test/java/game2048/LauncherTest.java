package game2048;

import game2048.console.Input;
import game2048.console.InputOption;
import game2048.console.Output;
import game2048.core.Direction;
import game2048.core.GameField;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LauncherTest implements GameField, Input, Output {

    @Override
    public int[][] getValues() {
        return new int[0][];
    }

    @Override
    public boolean move(Direction direction) {
        return false;
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

    @Override
    public InputOption getInputOption() {
        return null;
    }

    @Override
    public void print(GameField field) {

    }
}
