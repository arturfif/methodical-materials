package com.materials.web.dao.impl;

import com.materials.web.dao.inter.SpecialtyDAO;
import com.materials.web.model.Specialty;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class SpecialtyDAOImpl implements SpecialtyDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public SpecialtyDAOImpl(SessionFactory factory) {
        this.sessionFactory = factory; // Конструирует DAO
    }

    private Session currentSession() { // Извлекает текущий
        return sessionFactory.getCurrentSession(); // сеанс из фабрики
    }

    @Override
    public void save(Specialty specialty) {
        currentSession().save(specialty);
    }

    @Override
    public Specialty get(long id) {
        return (Specialty) currentSession().get(Specialty.class, id);
    }

    @Override
    public Specialty get(String name) {
        Criteria criteria = currentSession().createCriteria(Specialty.class);
        return (Specialty) criteria.add(Restrictions.eq("name", name)).uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Specialty> list() {
        Criteria criteria = currentSession().createCriteria(Specialty.class);
        criteria.addOrder(Order.asc("name"));
        return criteria.list();
    }
}
