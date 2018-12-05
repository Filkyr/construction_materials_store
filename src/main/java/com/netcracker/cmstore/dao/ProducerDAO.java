package com.netcracker.cmstore.dao;

import com.netcracker.cmstore.model.Producer;

import java.util.List;

public interface ProducerDAO {
    void removeProducer(int producerId);

    void insertOrUpdateProducer(Producer producer);

    List<Producer> getProducers();

    Producer getProducerById(int producerId);
}
