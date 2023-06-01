package com.petmily.backend.global;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.siot.IamportRestClient.IamportClient;

@Configuration
public class IamportConfig {

    @Value("${iamport.key}")
    private String restKey;

    @Value("${iamport.secret}")
    private String resSecret;

    @Bean
    public IamportClient iamportClient() {
        return new IamportClient(restKey,resSecret);
    }
}
