package com.remaslover.dataanalyzermicroservice.service;

import com.remaslover.dataanalyzermicroservice.model.Data;
import com.remaslover.dataanalyzermicroservice.repository.DataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class KafkaDataServiceImpl implements KafkaDataService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaDataServiceImpl.class);
    private final DataRepository dataRepository;

    public KafkaDataServiceImpl(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }


    @Override
    public void handle(Data data) {
        logger.info("Data object was saved: {}", data);
        dataRepository.save(data);
    }
}
