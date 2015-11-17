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
public class PlayProcessController {

    private GameField gameField;

    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public String getGamePage(ModelMap model) {
        model.addAttribute("gameField", gameField);
        return "gamepage";
    }

    @RequestMapping(value = "/game", method = RequestMethod.POST)
    public @ResponseBody String  makeMove(@RequestParam("direction") String direction) {
        if ( gameField.move(Direction.getDirection(direction)) )
            gameField.fillRandomEmptyCell();
        String field = "";
        for (int[] row : gameField.getValues()) {
            for (int rowEl : row) {
                field += rowEl + ",";
            }
        }
        field += gameField.getScore() + ",";
        field += gameField.hasAvailableMove();
        return field;
    }

    @RequestMapping(value = "/game/new", method = RequestMethod.POST)
    public String startNewGame() {
        createGameField();
        return "redirect:/game";
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