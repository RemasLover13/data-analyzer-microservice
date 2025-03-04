package com.remaslover.dataanalyzermicroservice.config;

import com.jcabi.xml.XML;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String servers;

    @Value("${topics}")
    private List<String> topics;

    private final XML settings;

    public KafkaConfig(XML settings) {
        this.settings = settings;
    }

    @Bean
    public Map<String, Object> receiverProperties() {
        Map<String, Object> props = new HashMap<>(5);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
               StringDeserializer.class);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put("spring.json.trusted.packages",
                new TextXPath(
                        this.settings, "//trustedPackages"
                ));
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "1");
        return props;
    }

    @Bean
    public ReceiverOptions<String, Object> receiverOptions() {
        ReceiverOptions<String, Object> receiverOptions = ReceiverOptions
                .create(receiverProperties());
        return receiverOptions.subscription(topics)
                .addAssignListener(receiverPartitions ->
                        System.out.println("assigned: " + receiverPartitions))
                .addRevokeListener(receiverPartitions ->
                        System.out.println("revoked: " + receiverPartitions));
    }

    @Bean
    public KafkaReceiver<String, Object> receiver(
            ReceiverOptions<String, Object> receiverOptions
    ) {
        return KafkaReceiver.create(receiverOptions);
    }
}
