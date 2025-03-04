package com.remaslover.dataanalyzermicroservice.config;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class BeanConfig {

    @Bean
    public XML consumerXml() throws IOException {
        InputStream is = getClass().getResourceAsStream("/kafka/consumer.xml");
        if (is == null) {
            throw new FileNotFoundException("consumer.xml not found");
        }
        return new XMLDocument(is.readAllBytes());
    }

}
