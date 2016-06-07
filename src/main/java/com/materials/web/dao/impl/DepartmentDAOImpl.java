package com.materials.web.dao.impl;

import com.materials.web.dao.inter.DepartmentDAO;
import com.materials.web.model.Department;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public DepartmentDAOImpl(SessionFactory factory) {
        this.sessionFactory = factory; // Конструирует DAO
    }

    private Session currentSession() { // Извлекает текущий
        return sessionFactory.getCurrentSession(); // сеанс из фабрики
    }

    @Override
    @Transactional
    public void save(Department department) {
        currentSession().save(department);
    }

    @Override
    @Transactional
    public Department get(long id) {
        return (Department) currentSession().get(Department.class, id);
    }

    @Override
    @Transactional
    public Department get(String name) {
        Criteria criteria = currentSession().createCriteria(Department.class);
        return (Department) criteria.add(Restrictions.eq("name", name)).uniqueResult();
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Department> list() {
        return currentSession().createCriteria(Department.class).list();
    }
}
