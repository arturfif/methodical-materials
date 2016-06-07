package com.materials.web.dao.impl;

import com.materials.web.dao.inter.DocumentDAO;
import com.materials.web.model.Document;
import com.materials.web.model.enumeration.Status;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DocumentDAOImpl implements DocumentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public DocumentDAOImpl(SessionFactory factory) {
        this.sessionFactory = factory; // Конструирует DAO
    }


    private Session currentSession() { // Извлекает текущий
        return sessionFactory.getCurrentSession(); // сеанс из фабрики
    }


    @Override
    @Transactional
    public Document get(long id) {
        return (Document) currentSession().get(Document.class, id);
    }

    @Override
    @Transactional
    public Document getByLibraryKey(int libraryKey) {
        Criteria criteria = currentSession().createCriteria(Document.class);
        return (Document) criteria.add(Restrictions.eq("library_key", libraryKey)).uniqueResult();
    }

    @Override
    @Transactional
    public void save(Document document) {
        currentSession().save(document);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Document> list() {
        return currentSession().createCriteria(Document.class).list();
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Document> listChecked() {
        Criteria criteria = currentSession().createCriteria(Document.class);
        return criteria.add(Restrictions.eq("status", Status.CHECKED.name())).list();
    }
}
