package game2048.web.controller;

import game2048.core.Direction;
import game2048.core.GameField;
import game2048.core.GameFieldImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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

    // --------------------------------------------
    @RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
    public ModelAndView defaultPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security + Hibernate Example");
        model.addObject("message", "This is default page!");
        model.setViewName("hello");
        return model;

    }

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security + Hibernate Example");
        model.addObject("message", "This page is for ROLE_ADMIN only!");
        model.setViewName("admin");

        return model;

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }

    // customize the error message
    private String getErrorMessage(HttpServletRequest request, String key) {

        Exception exception = (Exception) request.getSession().getAttribute(key);

        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Invalid username and password!";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Invalid username and password!";
        }

        return error;
    }

    // for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        // check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addObject("username", userDetail.getUsername());

        }

        model.setViewName("403");
        return model;

    }
}