package com.materials.web.dao.impl;

import com.materials.web.dao.inter.StudentDAO;
import com.materials.web.model.Student;
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
public class StudentDAOImpl implements StudentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public StudentDAOImpl(SessionFactory factory) {
        this.sessionFactory = factory; // Конструирует DAO
    }


    private Session currentSession() { // Извлекает текущий
        return sessionFactory.getCurrentSession(); // сеанс из фабрики
    }

    @Override
    public void save(Student student) {
        currentSession().save(student);
    }

    @Override
    public Student get(long id) {
        return (Student) currentSession().get(Student.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> list() {
        return currentSession().createCriteria(Student.class).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> listBySpecialtyId(long id) {
        Criteria criteria = currentSession().createCriteria(Student.class);
        return criteria.add(Restrictions.eq("specialty_id", id)).list();
    }
}
