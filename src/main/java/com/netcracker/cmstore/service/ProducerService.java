package com.netcracker.cmstore.service;

import com.netcracker.cmstore.model.Producer;

import java.util.List;

public interface ProducerService {
    void addProducer(Producer producer);

    void removeProducer(int producerId);

    void updateProducer(Producer producer);

    List<Producer> getProducers();

    Producer getProducerById(int producerId);
}
