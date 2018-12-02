package com.netcracker.cmstore.service.impl;

import com.netcracker.cmstore.dao.ProducerDAO;
import com.netcracker.cmstore.model.Producer;
import com.netcracker.cmstore.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProducerServiceImpl implements ProducerService {

    private final ProducerDAO producerDao;

    @Autowired
    public ProducerServiceImpl(ProducerDAO producerDao) {
        this.producerDao = producerDao;
    }

    @Transactional
    @Override
    public void addProducer(Producer producer) {
        producerDao.addProducer(producer);
    }

    @Transactional
    @Override
    public void removeProducer(int producerId) {
        producerDao.removeProducer(producerId);
    }

    @Transactional
    @Override
    public void updateProducer(Producer producer) {
        producerDao.updateProducer(producer);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Producer> getProducers() {
        return producerDao.getProducers();
    }

    @Transactional(readOnly = true)
    @Override
    public Producer getProducerById(int producerId) {
        return producerDao.getProducerById(producerId);
    }
}
