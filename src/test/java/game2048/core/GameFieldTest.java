package game2048.core;

import org.junit.Test;

import static game2048.core.Direction.LEFT;
import static game2048.core.Direction.RIGHT;
import static game2048.core.Direction.UP;
import static game2048.core.Direction.DOWN;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GameFieldTest {

    @Test
    public void testConstructors() {
        GameField gf1 = getGameFieldForTest();
        GameField gf2 = getGameFieldForTest(new int[][]{
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0}
        });

        assertThat(gf1.getValues(), is(gf2.getValues()));
    }

    @Test
    public void fieldShouldReturnCorrectCellsValues() {
        int[][] expected = new int[][]{
                {  2,    4,   8,  16},
                { 32,   64, 128, 256},
                {512, 1024, 512, 256},
                {128,   64, 32,   16}
        };

        GameField gf = getGameFieldForTest(expected);

        assertThat(expected, is(gf.getValues()));
    }

    @Test
    public void testFillRandomEmptyCell() {
        GameField gf = getGameFieldForTest(new int[][]{
                {2, 4, 2, 4},
                {4, 0, 8, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });

        gf.fillRandomEmptyCell();

        int[][] expected = new int[][]{
                {2, 4, 2, 4},
                {4, 2, 8, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        assertThat(expected, is(gf.getValues()));
    }

    @Test
    public void filedShouldHaveAvailableMoveWhenContainsEmptyCell() {
        GameField gf = getGameFieldForTest(new int[][]{
                {2, 4, 2, 4},
                {4, 2, 0, 2},
                {2, 4, 2, 4},
                {4, 2, 4, 2}
        });

        assertThat(true, is(gf.hasAvailableMove()));
    }

    @Test
    public void fieldShouldHaveAvailableMoveWhenMergeIsAvailable() {
        GameField gf = getGameFieldForTest(new int[][]{
                {2, 4, 2, 4},
                {4, 4, 4, 2},
                {2, 4, 2, 4},
                {4, 2, 4, 2}
        });

        assertThat(true, is(gf.hasAvailableMove()));
    }

    @Test
    public void testMoveLeftIsNotAvailable() {
        GameField gf = getGameFieldForTest(new int[][]{
                {16, 8, 2, 0},
                { 0, 0, 0, 0},
                { 4, 2, 4, 0},
                {32,16, 2, 4}
        });

        assertThat(false, is(gf.move(LEFT)));
    }

    @Test
    public void testMoveLeftNoMergeAvailable() {
        GameField gf = getGameFieldForTest(new int[][]{
                { 0,  0, 2,  0},
                { 0,  0, 0,  0},
                { 0,  0, 4, 32},
                {32, 16, 0,  4}
        });

        int[][] expected = new int[][]{
                { 2,  0, 0, 0},
                { 0,  0, 0, 0},
                { 4, 32, 0, 0},
                {32, 16, 4, 0}
        };
        assertThat(true, is(gf.move(LEFT)));
        assertThat(expected, is(gf.getValues()));
    }

    @Test
    public void testMoveLeftWithMerge() {
        GameField gf = getGameFieldForTest(new int[][]{
                { 4,  4, 4,  4},
                {16,  0, 0, 16},
                { 2,  0, 2,  0},
                {0,  16, 0,  4}
        });

        int[][] expected = new int[][]{
                { 8,  8, 0,  0},
                {32,  0, 0,  0},
                { 4,  0, 0,  0},
                {16,  4, 0,  0}
        };
        assertThat(true, is(gf.move(LEFT)));
        assertThat(expected, is(gf.getValues()));
    }

    @Test
    public void testMoveRightIsNotAvailable() {
        GameField gf = getGameFieldForTest(new int[][]{
                {0, 2,  8, 16},
                {0, 0,  0,  0},
                {0, 4,  2,  4},
                {4, 2, 16, 32}
        });

        assertThat(false, is(gf.move(RIGHT)));
    }

    @Test
    public void testMoveRightNoMergeAvailable() {
        GameField gf = getGameFieldForTest(new int[][]{
                { 0, 2,  0,  0},
                { 0, 0,  0,  0},
                {32, 4,  0,  0},
                { 4, 0, 16, 32}
        });

        int[][] expected = new int[][]{
                {0,  0,  0,  2},
                {0,  0,  0,  0},
                {0,  0, 32,  4},
                {0,  4, 16, 32}
        };
        assertThat(true, is(gf.move(RIGHT)));
        assertThat(expected, is(gf.getValues()));
    }

    @Test
    public void testMoveRightWithMerge() {
        GameField gf = getGameFieldForTest(new int[][]{
                { 4,  4, 4,  4},
                {16,  0, 0, 16},
                { 2,  0, 2,  0},
                {0,  16, 0,  4}
        });

        int[][] expected = new int[][]{
                {0, 0,  8,  8},
                {0, 0,  0, 32},
                {0, 0,  0,  4},
                {0, 0, 16, 4}
        };
        assertThat(true, is(gf.move(RIGHT)));
        assertThat(expected, is(gf.getValues()));
    }

    @Test
    public void testMoveUpIsNotAvailable() {
        GameField gf = getGameFieldForTest(new int[][]{
                {16, 0,  8, 16},
                { 4, 0, 16, 64},
                { 2, 0,  2, 16},
                { 0, 0,  4, 64}
        });

        assertThat(false, is(gf.move(UP)));
    }

    @Test
    public void testMoveUpNoMergeAvailable() {
        GameField gf = getGameFieldForTest(new int[][]{
                { 0, 2,  0,  2},
                { 0, 0,  0,  0},
                {32, 4,  0,  4},
                { 4, 0, 16, 32}
        });

        int[][] expected = new int[][]{
                {32, 2, 16,  2},
                { 4, 4,  0,  4},
                { 0, 0,  0, 32},
                { 0, 0,  0,  0}
        };
        assertThat(true, is(gf.move(UP)));
        assertThat(expected, is(gf.getValues()));
    }

    @Test
    public void testMoveUpWithMerge() {
        GameField gf = getGameFieldForTest(new int[][]{
                {32, 2, 4, 8},
                {32, 2, 4, 8},
                {32, 0, 4, 2},
                {0,  2, 4, 2}
        });

        int[][] expected = new int[][]{
                {64, 4, 8, 16},
                {32, 2, 8, 4},
                { 0, 0, 0, 0},
                { 0, 0, 0, 0}
        };
        assertThat(true, is(gf.move(UP)));
        assertThat(expected, is(gf.getValues()));
    }

    @Test
    public void testMoveDownIsNotAvailable() {
        GameField gf = getGameFieldForTest(new int[][]{
                { 0, 0,  8, 16},
                {16, 0, 16, 64},
                { 4, 0,  2, 16},
                { 2, 0,  4, 64}
        });

        assertThat(false, is(gf.move(DOWN)));
    }

    @Test
    public void testMoveDownNoMergeAvailable() {
        GameField gf = getGameFieldForTest(new int[][]{
                { 4, 2, 16, 32},
                {32, 0,  0,  4},
                { 0, 4,  0,  0},
                { 0, 0,  0,  2}
        });

        int[][] expected = new int[][]{
                { 0, 0,  0,  0},
                { 0, 0,  0, 32},
                { 4, 2,  0,  4},
                {32, 4, 16,  2}
        };
        assertThat(true, is(gf.move(DOWN)));
        assertThat(expected, is(gf.getValues()));
    }

    @Test
    public void testMoveDownWithMerge() {
        GameField gf = getGameFieldForTest(new int[][]{
                {32, 2, 4, 8},
                {32, 2, 4, 8},
                {32, 0, 4, 2},
                {0,  2, 4, 2}
        });

        int[][] expected = new int[][]{
                { 0, 0, 0,  0},
                { 0, 0, 0,  0},
                {32, 2, 8, 16},
                {64, 4, 8,  4}
        };
        assertThat(true, is(gf.move(DOWN)));
        assertThat(expected, is(gf.getValues()));
    }

    private GameField getGameFieldForTest(int[][] values) {
        return new GameFieldImpl(values, new FakeSelector(), new FakeFiller());
    }

    private GameField getGameFieldForTest() {
        return new GameFieldImpl(new FakeSelector(), new FakeFiller());
    }
}
