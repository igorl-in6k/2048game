package game2048.core;

public enum Direction {
    LEFT,
    RIGHT,
    UP,
    DOWN;

    public static Direction getDirection(String dir) {
        dir = dir.trim().toLowerCase();
        switch (dir) {
            case "left": return LEFT;
            case "right": return RIGHT;
            case "up": return UP;
            case "down": return DOWN;
            default: return null;
        }
    }
}
