package game2048.web.dao;

import java.util.List;

import game2048.web.entity.User;
import org.hibernate.SessionFactory;

import org.hibernate.criterion.Restrictions;

public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public User findByUserName(String username) {

        List<User> users;

        users = getSessionFactory().getCurrentSession()
                .createCriteria(User.class)
                .add(Restrictions.eq("username", username))
                .list();

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}