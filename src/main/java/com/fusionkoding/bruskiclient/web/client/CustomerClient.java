package com.fusionkoding.bruskiclient.web.client;

import java.net.URI;
import java.util.UUID;

import com.fusionkoding.bruskiclient.web.model.CustomerDto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.Setter;

@Component
@ConfigurationProperties(value = "bruski", ignoreInvalidFields = false)
public class CustomerClient implements Client<CustomerDto, UUID> {

    private static final String CUSTOMER_PATH_V1 = "/api/v1/customer/";

    private final RestTemplate restTemplate;

    @Setter
    private String apiHost;

    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public CustomerDto getById(UUID id) {
        return restTemplate.getForObject(apiHost + CUSTOMER_PATH_V1 + id.toString(), CustomerDto.class);
    }

    @Override
    public CustomerDto getByUri(URI uri) {
        return restTemplate.getForObject(apiHost + uri.toString(), CustomerDto.class);
    }

    @Override
    public URI save(CustomerDto dto) {
        return restTemplate.postForLocation(apiHost + CUSTOMER_PATH_V1, dto);
    }

    @Override
    public void update(UUID id, CustomerDto dto) {
        restTemplate.put(apiHost + CUSTOMER_PATH_V1 + id.toString(), dto);
    }

    @Override
    public void delete(UUID id) {
        restTemplate.delete(apiHost + CUSTOMER_PATH_V1 + id.toString());
    }

}
