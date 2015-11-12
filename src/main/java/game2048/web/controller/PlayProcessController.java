package game2048.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/")
public class PlayProcessController {

    private static String direction = "no direction";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMainPage() {
        return "main";
    }

    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public String getGamePage(ModelMap model) {
        model.addAttribute("direction", direction);
        return "playpage";
    }

    @RequestMapping(value = "/game", method = RequestMethod.POST)
    public String makeMove(@RequestParam("direction") String direction) {
        this.direction = direction;
        return "redirect:/game";
    }

}
