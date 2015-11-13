package game2048.web.controller;

import game2048.core.Direction;
import game2048.core.GameField;
import game2048.core.GameFieldImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes(value = "gameField")
@Scope("session")
@RequestMapping(value = "/")
public class PlayProcessController {

    private GameField gameField;

    @RequestMapping(method = RequestMethod.GET)
    public String getMainPage() {
        return "main";
    }

    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public String getGamePage(@ModelAttribute("gameField") GameField gameField, ModelMap model) {
        model.addAttribute("gameField", gameField);
        return "playpage";
    }

    @RequestMapping(value = "/game", method = RequestMethod.POST)
    public @ResponseBody String  makeMove(@RequestParam("direction") String direction) {
        gameField.move(Direction.getDirection(direction));
        gameField.fillRandomEmptyCell();
        String field = "";
        for (int[] ints : gameField.getValues()) {
            for (int anInt : ints) {
                field += anInt + ",";
            }
        }
        field = field.substring(0, field.length() - 1);
        return field;
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
