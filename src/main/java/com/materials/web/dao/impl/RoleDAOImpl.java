package com.materials.web.dao.impl;

import com.materials.web.dao.inter.RoleDAO;
import com.materials.web.model.Role;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public RoleDAOImpl(SessionFactory factory) {
        this.sessionFactory = factory; // Конструирует DAO
    }


    private Session currentSession() { // Извлекает текущий
        return sessionFactory.getCurrentSession(); // сеанс из фабрики
    }

    @Override
    @Transactional
    public Role get(long id) {
        return (Role) currentSession().get(Role.class, id);
    }

    @Override
    @Transactional
    public Role get(String name) {
        Criteria criteria = currentSession().createCriteria(Role.class);
        return (Role) criteria.add(Restrictions.eq("name", name)).uniqueResult();
    }
}
