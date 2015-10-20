package game2048.console;

import game2048.core.Constants;
import game2048.core.Direction;
import game2048.core.GameField;
import game2048.core.GameFieldImpl;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static game2048.core.Constants.SIZE;

public class ConsolePrinter implements Output {

    private static final Map<Integer, ConsoleColor> colors = new HashMap<>();
    
    private PrintStream out;

    public ConsolePrinter(PrintStream out) {
        initializeColors();
        this.out = out;
    }

    @Override
    public void print(GameField field) {
        int[][] squares = field.getValues();
        out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        out.println("Score: " + field.getScore());
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                out.printf("%s%4d", colors.get(squares[i][j]).code, squares[i][j]);
                out.print(ConsoleColor.RESET.code);
            }
            out.println();
        }
        out.println();
    }
    
    private void initializeColors() {
        colors.put(0, ConsoleColor.WHITE);
        colors.put(2, ConsoleColor.MAGENTA);
        colors.put(4, ConsoleColor.GREEN);
        colors.put(8, ConsoleColor.PURPLE);
        colors.put(16, ConsoleColor.RED);
        colors.put(32, ConsoleColor.YELLOW);
        colors.put(64, ConsoleColor.BLUE);
        colors.put(128, ConsoleColor.CYAN);
        colors.put(256, ConsoleColor.YELLOW);
        colors.put(512, ConsoleColor.GREEN);
        colors.put(1024, ConsoleColor.RED);
        colors.put(2048, ConsoleColor.CYAN);
    }
}
