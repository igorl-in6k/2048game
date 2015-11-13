package game2048.web.controller;

import game2048.core.Direction;
import game2048.core.GameField;
import game2048.core.GameFieldImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes(value = "gameField")
@Scope("session")
@RequestMapping(value = "/")
public class PlayProcessController {

    private GameField gameField;

    @RequestMapping(method = RequestMethod.GET)
    public String getGamePage(@ModelAttribute("gameField") GameField gameField) {
        return "playpage";
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody String  makeMove(@RequestParam("direction") String direction) {
        if ( gameField.move(Direction.getDirection(direction)) )
            gameField.fillRandomEmptyCell();
        String field = "";
        for (int[] ints : gameField.getValues()) {
            for (int anInt : ints) {
                field += anInt + ",";
            }
        }
        field += gameField.getScore();
        return field;
    }

    @RequestMapping(value = "/newgame", method = RequestMethod.POST)
    public String startNewGame() {
        createGameField();
        return "redirect:/";
    }

    @ModelAttribute("gameField")
    public GameField createGameField() {
        GameField gameField = new GameFieldImpl();
        gameField.fillRandomEmptyCell();
        gameField.fillRandomEmptyCell();
        this.gameField = gameField;
        return gameField;
    }
}
