package game2048.core;

import org.junit.Test;

import static game2048.core.Direction.LEFT;
import static game2048.core.Direction.RIGHT;
import static game2048.core.Direction.UP;
import static game2048.core.Direction.DOWN;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GameFieldImplTest {

    @Test
    public void testConstructors() {
        GameFieldImpl gf1 = new GameFieldImpl();
        GameFieldImpl gf2 = new GameFieldImpl(new int[][]{
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

        GameFieldImpl gf = new GameFieldImpl(expected);

        assertThat(expected, is(gf.getValues()));
    }

    @Test
    public void testMoveLeftIsNotAvailable() {
        GameFieldImpl gf = new GameFieldImpl(new int[][]{
                {16, 8, 2, 0},
                { 0, 0, 0, 0},
                { 4, 2, 4, 0},
                {32,16, 2, 4}
        });

        assertThat(0, is(gf.move(LEFT)));
    }

    @Test
    public void testMoveLeftNoMergeAvailable() {
        GameFieldImpl gf = new GameFieldImpl(new int[][]{
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
        assertThat(0, is(gf.move(LEFT)));
        assertThat(expected, is(gf.getValues()));
    }

    @Test
    public void testMoveLeftWithMerge() {
        GameFieldImpl gf = new GameFieldImpl(new int[][]{
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
        assertThat(52, is(gf.move(LEFT)));
        assertThat(expected, is(gf.getValues()));
    }

    @Test
    public void testMoveRightIsNotAvailable() {
        GameFieldImpl gf = new GameFieldImpl(new int[][]{
                {0, 2,  8, 16},
                {0, 0,  0,  0},
                {0, 4,  2,  4},
                {4, 2, 16, 32}
        });

        assertThat(0, is(gf.move(RIGHT)));
    }

    @Test
    public void testMoveRightNoMergeAvailable() {
        GameFieldImpl gf = new GameFieldImpl(new int[][]{
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
        assertThat(0, is(gf.move(RIGHT)));
        assertThat(expected, is(gf.getValues()));
    }

    @Test
    public void testMoveRightWithMerge() {
        GameFieldImpl gf = new GameFieldImpl(new int[][]{
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
        assertThat(52, is(gf.move(RIGHT)));
        assertThat(expected, is(gf.getValues()));
    }

    @Test
    public void testMoveUpIsNotAvailable() {
        GameFieldImpl gf = new GameFieldImpl(new int[][]{
                {16, 0,  8, 16},
                { 4, 0, 16, 64},
                { 2, 0,  2, 16},
                { 0, 0,  4, 64}
        });

        assertThat(0, is(gf.move(UP)));
    }

    @Test
    public void testMoveUpNoMergeAvailable() {
        GameFieldImpl gf = new GameFieldImpl(new int[][]{
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
        assertThat(0, is(gf.move(UP)));
        assertThat(expected, is(gf.getValues()));
    }

    @Test
    public void testMoveUpWithMerge() {
        GameFieldImpl gf = new GameFieldImpl(new int[][]{
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
        assertThat(104, is(gf.move(UP)));
        assertThat(expected, is(gf.getValues()));
    }

    @Test
    public void testMoveDownIsNotAvailable() {
        GameFieldImpl gf = new GameFieldImpl(new int[][]{
                { 0, 0,  8, 16},
                {16, 0, 16, 64},
                { 4, 0,  2, 16},
                { 2, 0,  4, 64}
        });

        assertThat(0, is(gf.move(DOWN)));
    }

    @Test
    public void testMoveDownNoMergeAvailable() {
        GameFieldImpl gf = new GameFieldImpl(new int[][]{
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
        assertThat(0, is(gf.move(DOWN)));
        assertThat(expected, is(gf.getValues()));
    }

    @Test
    public void testMoveDownWithMerge() {
        GameFieldImpl gf = new GameFieldImpl(new int[][]{
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
        assertThat(104, is(gf.move(DOWN)));
        assertThat(expected, is(gf.getValues()));
    }


}
