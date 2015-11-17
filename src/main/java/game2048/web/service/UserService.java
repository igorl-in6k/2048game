package game2048.web.service;

import game2048.web.dao.UserDao;
import game2048.web.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean addNewUser(User user) {
        if ( userDao.findByUserName(user.getUsername()) != null )
            return false;

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userDao.addUser(user);
        return true;
    }
}
