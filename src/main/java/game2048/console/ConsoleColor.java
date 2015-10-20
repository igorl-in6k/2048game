package game2048.console;

public enum ConsoleColor {

    RED("\u001B[31m"),
    BLUE("\u001B[34m"),
    WHITE("\u001B[37m"),
    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    MAGENTA("\033[1;35m");

    ConsoleColor(String code) {
        this.code = code;
    }

    public final String code;

    /*
    REDB "\033[1;41m"
    REDF "\033[31m"
    GREENB "\033[1;42m"
    GREENF "\033[1;32m"
    YELLOWB "\033[1;43m"
    YELLOWF "\033[1;33m"
    BLUEB "\033[1;44m"
    BLUEF "\033[1;34m"
    MAGENTAB "\033[1;45m"
    MAGENTAF "\033[1;35m"
    CYANB "\033[1;46m"
    CYANF "\033[1;36m"
    WHITEB "\033[1;47m"
    WHITEF "\033[1;37m"
    RESET "\033[0m"
     */
}
