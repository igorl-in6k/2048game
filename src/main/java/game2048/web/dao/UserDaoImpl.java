package game2048.web.dao;

import java.util.List;

import game2048.web.entity.User;
import game2048.web.entity.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public User findByUserName(String username) {
        List<User> users;
        users = getSession()
                .createCriteria(User.class)
                .add(Restrictions.eq("username", username))
                .list();
        if (users.size() > 0)
            return users.get(0);
        return null;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}