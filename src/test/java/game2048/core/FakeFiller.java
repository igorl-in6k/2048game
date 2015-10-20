package game2048.core;

public class FakeFiller implements CellFillingStrategy {
    @Override
    public void fill(Cell cell) {
        cell.setValue(2);
    }
}
