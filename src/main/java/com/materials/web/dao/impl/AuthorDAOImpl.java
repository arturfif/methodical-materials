package com.materials.web.dao.impl;

import com.materials.web.dao.inter.AuthorDAO;
import com.materials.web.model.Author;
import com.materials.web.model.Document;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AuthorDAOImpl implements AuthorDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public AuthorDAOImpl(SessionFactory factory) {
        this.sessionFactory = factory; // Конструирует DAO
    }


    private Session currentSession() { // Извлекает текущий
        return sessionFactory.getCurrentSession(); // сеанс из фабрики
    }

    @Override
    @Transactional
    public Author get(long id) {
        return (Author) currentSession().get(Author.class, id);
    }

    @Override
    @Transactional
    public Author getByName(String name) {
        Criteria criteria = currentSession().createCriteria(Author.class);
        return (Author) criteria.add(Restrictions.eq("name", name)).uniqueResult();
    }

    @Override
    @Transactional
    public Author getBySurname(String surname) {
        Criteria criteria = currentSession().createCriteria(Author.class);
        return (Author) criteria.add(Restrictions.eq("surname", surname)).uniqueResult();
    }

    @Override
    @Transactional
    public Author getByPatronymic(String patronymic) {
        Criteria criteria = currentSession().createCriteria(Author.class);
        return (Author) criteria.add(Restrictions.eq("patronymic", patronymic)).uniqueResult();
    }

    @Override
    @Transactional
    public void save(Author author) {
        currentSession().save(author);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Author> list() {
        return currentSession().createCriteria(Author.class).list();
    }


}
