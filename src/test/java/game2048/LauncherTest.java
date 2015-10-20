package game2048;

import game2048.console.Input;
import game2048.console.InputOption;
import static game2048.console.InputOption.*;
import game2048.console.Output;
import game2048.core.Direction;
import game2048.core.GameField;
import game2048.core.GameFieldImpl;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LauncherTest implements GameField, Input, Output {

    private InputOption[] options;
    private GameFieldImpl gf;
    private int i = 0;

    @Before
    public void before() {
        gf = new GameFieldImpl();
    }

    @Test
    public void testGameAbortedAfterFewMoves() {
        Launcher launcher = new Launcher(this, this, this);
        options = new InputOption[]{MOVE_LEFT, MOVE_UP, EXIT};
        i = 0;

        InputOption lastOption = launcher.runGame();

        assertThat(lastOption, is(EXIT));
    }

    @Test
    public void testStartedNewGameAfterFewMoves() {
        Launcher launcher = new Launcher(this, this, this);
        options = new InputOption[]{MOVE_LEFT, MOVE_UP, NEW_GAME};
        i = 0;

        InputOption lastOption = launcher.runGame();

        assertThat(lastOption, is(NEW_GAME));
    }

    @Override
    public int[][] getValues() {
        return new int[0][];
    }

    @Override
    public boolean move(Direction direction) {
        return true;
    }

    @Override
    public void fillRandomEmptyCell() {

    }

    @Override
    public boolean hasAvailableMove() {
        return true;
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public InputOption getInputOption() {
        return options[i++];
    }

    @Override
    public void print(GameField field) {

    }
}
