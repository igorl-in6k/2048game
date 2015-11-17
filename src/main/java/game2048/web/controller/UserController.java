package game2048.web.controller;

import game2048.web.entity.User;
import game2048.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/users/new", method = RequestMethod.GET)
    public String getSignUpPage() {
        return "signup";
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public String registerNewUser(@RequestParam("username") String username,
                                  @RequestParam("password") String password) {
        User user = new User(username, password);
        if ( userService.addNewUser(user) )
            return "redirect:/game";
        return "redirect:/";
    }

}