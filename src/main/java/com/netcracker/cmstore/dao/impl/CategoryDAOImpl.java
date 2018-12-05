package com.netcracker.cmstore.dao.impl;

import com.netcracker.cmstore.dao.CategoryDAO;
import com.netcracker.cmstore.model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public CategoryDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void removeCategory(int categoryId) {
        Session session = this.sessionFactory.getCurrentSession();
        Category p = session.load(Category.class, categoryId);
        if (null != p) {
            session.delete(p);
        }
    }

    @Override
    public void updateOrInsertCategory(Category category) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(category);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Category> getCategories() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from Category").list();
    }

    @Override
    public Category getCategoryById(int categoryId) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Category.class, categoryId);
    }
}

