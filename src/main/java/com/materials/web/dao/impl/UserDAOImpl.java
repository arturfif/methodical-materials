package com.materials.web.dao.impl;

import com.materials.web.dao.inter.UserDAO;
import com.materials.web.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory factory) {
        this.sessionFactory = factory; // Конструирует DAO
    }


    private Session currentSession() { // Извлекает текущий
        return sessionFactory.getCurrentSession(); // сеанс из фабрики
    }

    @Override
    @Transactional
    public void save(User user) {
        currentSession().save(user);
    }

    @Override
    @Transactional
    public void delete(long personId) {
        User load = (User) currentSession().load(User.class, personId);
        currentSession().delete(load);
    }

    @Override
    @Transactional
    public User get(long personId) {
        return (User) currentSession().get(User.class, personId);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<User> list() {
        return currentSession().createCriteria(User.class).list();
    }

    @Override
    @Transactional
    public User get(String username) {
        Criteria criteria = currentSession().createCriteria(User.class);
        return (User) criteria.add(Restrictions.eq("username", username)).uniqueResult();
    }
}
