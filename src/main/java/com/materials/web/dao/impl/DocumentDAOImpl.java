package com.materials.web.dao.impl;

import com.materials.web.dao.inter.DocumentDAO;
import com.materials.web.model.Author;
import com.materials.web.model.Department;
import com.materials.web.model.Document;
import com.materials.web.model.enumeration.Status;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
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
    public Document getByLibraryKey(long libraryKey) {
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
    public void setCheckedStatus(long id) {
        Document document = (Document) currentSession().get(Document.class, id);
        document.setStatus(Status.CHECKED);
    }

    @Override
    @Transactional
    public void remove(long id) {
        Document document = new Document();
        document.setId(id);
        currentSession().delete(document);
    }


    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Document> list() {
        Criteria criteria = currentSession().createCriteria(Document.class);
        criteria.addOrder(Order.desc("uploadDate"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        //criteria.setProjection(Projections.distinct(Projections.property("id")));
        return criteria.list();
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Document> listChecked() {
        Criteria criteria = currentSession().createCriteria(Document.class);
        criteria.addOrder(Order.desc("uploadDate"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.add(Restrictions.eq("status", Status.CHECKED)).list();
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Document> listByUserId(long id) {
        Criteria criteria = currentSession().createCriteria(Document.class);
        criteria.addOrder(Order.desc("uploadDate"));
        return criteria.add(Restrictions.eq("user_id", id)).list();
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Document> listUnchecked() {
        Criteria criteria = currentSession().createCriteria(Document.class);
        criteria.addOrder(Order.desc("uploadDate"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.add(Restrictions.eq("status", Status.UNCHECKED)).list();
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Document> listByLibraryKey(int libraryKey) {
        Criteria criteria = currentSession().createCriteria(Document.class);
        criteria.addOrder(Order.desc("uploadDate"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("status", Status.CHECKED));
        return criteria.add(Restrictions.eq("libraryKey", libraryKey)).list();
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Document> listByName(String name) {
        Criteria criteria = currentSession().createCriteria(Document.class);
        criteria.addOrder(Order.desc("uploadDate"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("status", Status.CHECKED));
        return criteria.add(Restrictions.ilike("name", name, MatchMode.START)).list();
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Document> listByDepartment(String departmentName) {
        Criteria criteria = currentSession().createCriteria(Document.class);
        criteria.addOrder(Order.desc("uploadDate"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("status", Status.CHECKED));
        Criteria departmentCriteria = currentSession().createCriteria(Department.class);
        Department department = (Department) departmentCriteria.add(Restrictions.ilike("name", departmentName, MatchMode.START)).uniqueResult();

        if(department != null) {
            return criteria.add(Restrictions.eq("department", department)).list();
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Document> listByPublishingYear(short year) {
        Criteria criteria = currentSession().createCriteria(Document.class);
        criteria.addOrder(Order.desc("uploadDate"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("status", Status.CHECKED));
        return criteria.add(Restrictions.eq("publishingYear", year)).list();
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Document> listByAuthorSurname(String surname) {
        Criteria criteria = currentSession().createCriteria(Author.class);
        List<Author> authorList = criteria.add(Restrictions.ilike("surname", surname, MatchMode.START)).list();
        List<Document> resultList = new ArrayList<>();
        for (Author author : authorList) {
            resultList.addAll(author.getCheckedDocumentSet());
        }
        return resultList;
    }


}
