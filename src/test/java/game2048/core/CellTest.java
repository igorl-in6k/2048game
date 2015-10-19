package game2048.core;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CellTest {

    private Cell cell;

    @Test
    public void testConstructorWithParameter() {
        assertThat(16, is(new Cell(16).getValue()));
    }

    @Test
    public void testConstructorWithoutParameters() {
        assertThat(0, is(new Cell().getValue()));
    }

    @Test
    public void testEquals() {
        assertThat(true, is(new Cell().equals(new Cell())));
        assertThat(true, is(new Cell(32).equals(new Cell(32))));
        assertThat(false, is(new Cell(16).equals(new Cell())));
    }

    @Test
    public void cellShouldBeEmptyAfterCreation() {
        assertThat(true, is(new Cell().isEmpty()));
    }

    @Test
    public void cellShouldNotBeEmptyAfterCreationWithParameter() {
        assertThat(false, is(new Cell(2).isEmpty()));
    }

    @Test
    public void cellShouldReturnCorrectValue() {
        assertThat(1024, is(new Cell(1024).getValue()));
        assertThat(0, is(new Cell().getValue()));
    }

    @Test
    public void cellShouldNotMergeWithNotEqualCell() {
        assertThat(false, is(new Cell(1024).merge(new Cell(2048))));
        assertThat(false, is(new Cell().merge(new Cell(2))));
    }

    @Test
    public void cellShouldDoubleValueAfterMerge() {
        cell = new Cell(256);
        cell.merge(new Cell(256));
        assertThat(512, is(cell.getValue()));
    }

    @Test
    public void cellShouldBeEmptyAfterItHasBeenMerged() {
        cell = new Cell(32);
        new Cell(32).merge(cell);
        assertThat(true, is(cell.isEmpty()));
    }
}
