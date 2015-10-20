package game2048.console;

import static game2048.core.Direction.*;
import game2048.core.Direction;

public enum InputOption {

    NEW_GAME,
    EXIT,
    MOVE_LEFT,
    MOVE_RIGHT,
    MOVE_UP,
    MOVE_DOWN;

    public boolean isDirection() {
        return this == MOVE_LEFT || this == MOVE_RIGHT ||
                this == MOVE_DOWN || this == MOVE_UP;
    }

    public Direction getAsDirection() {
        switch (this) {
            case MOVE_UP:
                return UP;
            case MOVE_LEFT:
                return LEFT;
            case MOVE_RIGHT:
                return RIGHT;
            case MOVE_DOWN:
                return DOWN;
        }
        return null;
    }

}
