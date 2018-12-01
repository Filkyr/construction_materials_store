package com.netcracker.cmstore.dao.impl;

import com.netcracker.cmstore.dao.ProducerDAO;
import com.netcracker.cmstore.model.Producer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProducerDAOImpl implements ProducerDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProducerDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addProducer(Producer producer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(producer);
    }

    @Override
    public void removeProducer(int producerId) {
        Session session = this.sessionFactory.getCurrentSession();
        Producer p = session.load(Producer.class, producerId);
        if (null != p) {
            session.delete(p);
        }
    }

    @Override
    public void updateProducer(Producer producer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(producer);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Producer> getProducers() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from Producer").list();
    }

    @Override
    public Producer getProducerById(int producerId) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Producer.class, producerId);
    }
}
