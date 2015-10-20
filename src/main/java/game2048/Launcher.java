package game2048;

import game2048.console.*;
import game2048.core.GameField;
import game2048.core.GameFieldImpl;

public class Launcher {

    public static void main(String[] args) {
        new Launcher().run();
    }

    public void run() {
        Input reader = new ConsoleInput(System.in);
        Output printer = new ConsolePrinter(System.out);
        GameField gf = new GameFieldImpl();

        gf.fillRandomEmptyCell();
        gf.fillRandomEmptyCell();

        InputOption option;

        while ( true ) {
            printer.print(gf);
            option = reader.getInputOption();
            if ( option.isDirection() ) {
                if ( gf.move(option.getAsDirection()) )
                    gf.fillRandomEmptyCell();
                // todo new game & exit
            }
        }
    }
}
