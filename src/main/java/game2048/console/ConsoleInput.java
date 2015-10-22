package game2048.console;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ConsoleInput implements Input {

    private InputStream in;
    private Scanner sc;

    public ConsoleInput(InputStream is) {
        in = is;
        sc = new Scanner(in);
    }

    @Override
    public InputOption getInputOption() {
        switch (sc.next()) {
            case "w":
                return InputOption.MOVE_UP;
            case "a":
                return InputOption.MOVE_LEFT;
            case "s":
                return InputOption.MOVE_DOWN;
            case "d":
                return InputOption.MOVE_RIGHT;
            case "n":
                return InputOption.NEW_GAME;
            case "q":
                return InputOption.EXIT;
        }
        throw new RuntimeException("trouble with input");
    }
}
