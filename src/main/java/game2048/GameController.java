package game2048;

import game2048.console.*;
import game2048.core.GameField;
import game2048.core.GameFieldImpl;

import static game2048.console.InputOption.EXIT;
import static game2048.console.InputOption.NEW_GAME;

public class GameController {

    private GameField gameField;
    private Input reader;
    private Output printer;

    public GameController(GameField gf, Input in, Output out) {
        gameField = gf;
        reader = in;
        printer = out;
    }

    public GameController() {
        this(new GameFieldImpl(),
                new ConsoleInput(System.in),
                new ConsolePrinter(System.out));
    }

    public void runApp() {
        InputOption option = NEW_GAME;
        while ( option == NEW_GAME ) {
            option = runGame();
        }
    }

    protected InputOption runGame() {
        gameField.fillRandomEmptyCell();
        gameField.fillRandomEmptyCell();

        InputOption option;

        boolean gameOver = false;
        while ( !gameOver ) {
            printer.print(gameField);
            option = reader.getInputOption();
            if ( option.isDirection() ) {
                if ( gameField.move(option.getAsDirection()) )
                    gameField.fillRandomEmptyCell();
                gameOver = !gameField.hasAvailableMove();
            }
            else return option;
        }
        printer.printLine("Game Over");
        return EXIT;
    }
}
