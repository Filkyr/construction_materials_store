package com.netcracker.cmstore.service;

import com.netcracker.cmstore.model.Producer;

import java.util.List;

public interface ProducerService {
    void removeProducer(int producerId);

    void insertOrUpdateProducer(Producer producer);

    List<Producer> getProducers();

    Producer getProducerById(int producerId);
}
