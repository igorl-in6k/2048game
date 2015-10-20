package game2048.core;

public class FakeSelector implements CellSelectionStrategy {
    @Override
    public Cell getCell(Cell[][] cells) {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if ( cell.isEmpty() )
                    return cell;
            }
        }
        return null;
    }
}
