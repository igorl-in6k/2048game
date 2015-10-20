package game2048.console;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ConsoleInput implements Input {

    private InputStream in;

    public ConsoleInput(InputStream is) {
        in = is;
    }

    @Override
    public InputOption getInputOption() {
        try {
            switch (in.read()) {
                case 'w':
                    return InputOption.MOVE_UP;
                case 'a':
                    return InputOption.MOVE_LEFT;
                case 's':
                    return InputOption.MOVE_DOWN;
                case 'd':
                    return InputOption.MOVE_RIGHT;
                case 'n':
                    return InputOption.NEW_GAME;
                case 'q':
                    return InputOption.EXIT;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("trouble with input");
    }
}
