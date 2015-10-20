package game2048;

import game2048.console.*;
import game2048.core.Direction;
import game2048.core.GameField;
import game2048.core.GameFieldImpl;

import static game2048.core.Direction.*;

public class Launcher {

    public static void main(String[] args) {
        Input reader = new ConsoleInput(System.in);
        Output printer = new ConsolePrinter(System.out);
        GameField gf = new GameFieldImpl();

        gf.fillRandomEmptyCell();
        gf.fillRandomEmptyCell();

        InputOption option;

        while ( true ) {
            printer.print(gf);
            switch (reader.getInputOption()) {
                case MOVE_DOWN:
                    if ( gf.move(DOWN) != 0 )
                        gf.fillRandomEmptyCell();
                    break;
                case MOVE_UP:
                    if ( gf.move(UP) != 0 )
                        gf.fillRandomEmptyCell();
                    break;
                case MOVE_LEFT:
                    if ( gf.move(LEFT) != 0 )
                        gf.fillRandomEmptyCell();
                    break;
                case MOVE_RIGHT:
                    if ( gf.move(RIGHT) != 0 )
                        gf.fillRandomEmptyCell();
                    break;
            }
        }
    }
}
