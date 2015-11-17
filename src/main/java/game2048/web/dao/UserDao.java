package game2048.web.dao;

import game2048.web.entity.User;

public interface UserDao {

    User findByUserName(String username);

    void addUser(User user);
}