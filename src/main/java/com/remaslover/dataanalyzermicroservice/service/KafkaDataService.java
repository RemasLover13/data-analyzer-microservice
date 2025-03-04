package com.remaslover.dataanalyzermicroservice.service;

import com.remaslover.dataanalyzermicroservice.model.Data;

public interface KafkaDataService {
    void handle(Data data);
}
