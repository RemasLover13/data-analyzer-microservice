package com.remaslover.dataanalyzermicroservice.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.remaslover.dataanalyzermicroservice.model.Data;
import com.remaslover.dataanalyzermicroservice.util.LocalDateTimeDeserializer;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import reactor.kafka.receiver.KafkaReceiver;

import java.time.LocalDateTime;

@Service
public class KafkaDataReceiverImpl implements KafkaDataReceiver {

    private final KafkaReceiver<String, Object> kafkaReceiver;
    private final LocalDateTimeDeserializer localDateTimeDeserializer;
    private final KafkaDataService kafkaDataService;

    public KafkaDataReceiverImpl(KafkaReceiver<String, Object> kafkaReceiver, LocalDateTimeDeserializer localDateTimeDeserializer, KafkaDataService kafkaDataService) {
        this.kafkaReceiver = kafkaReceiver;
        this.localDateTimeDeserializer = localDateTimeDeserializer;
        this.kafkaDataService = kafkaDataService;
    }

    @PostConstruct
    private void init() {
        fetch();
    }

    @Override
    public void fetch() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, localDateTimeDeserializer)
                .create();
        kafkaReceiver.receive()
                .subscribe(r -> {
                    Data data = gson.fromJson(r.value().toString(), Data.class);
                    kafkaDataService.handle(data);
                    r.receiverOffset().acknowledge();
                });

    }
}
