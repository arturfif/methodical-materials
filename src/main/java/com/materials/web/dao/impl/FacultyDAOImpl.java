package com.materials.web.dao.impl;

import com.materials.web.dao.inter.FacultyDAO;
import com.materials.web.model.Faculty;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class FacultyDAOImpl implements FacultyDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public FacultyDAOImpl(SessionFactory factory) {
        this.sessionFactory = factory; // Конструирует DAO
    }

    private Session currentSession() { // Извлекает текущий
        return sessionFactory.getCurrentSession(); // сеанс из фабрики
    }

    @Override
    public void save(Faculty faculty) {
        currentSession().save(faculty);
    }

    @Override
    public Faculty get(long id) {
        return (Faculty) currentSession().get(Faculty.class, id);
    }

    @Override
    public Faculty get(String name) {
        Criteria criteria = currentSession().createCriteria(Faculty.class);
        return (Faculty) criteria.add(Restrictions.eq("name", name)).uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Faculty> list() {
        return currentSession().createCriteria(Faculty.class).list();
    }
}
