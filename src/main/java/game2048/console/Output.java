package game2048.console;

import game2048.core.GameField;

public interface Output {

    void print(GameField field);

    void printLine(String str);

}
