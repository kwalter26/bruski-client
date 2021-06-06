package com.fusionkoding.bruskiclient.web.client;

import java.net.URI;
import java.util.UUID;

import com.fusionkoding.bruskiclient.web.model.BeerDto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.Setter;

@Component
@ConfigurationProperties(value = "bruski", ignoreInvalidFields = false)
public class BreweryClient implements Client<BeerDto, String> {

    private static final String BEER_PATH_V1 = "/api/v1/beer/";

    private final RestTemplate restTemplate;

    @Setter
    private String apiHost;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public BeerDto getById(String id) {
        return restTemplate.getForObject(apiHost + BEER_PATH_V1 + id.toString(), BeerDto.class);
    }

    @Override
    public BeerDto getByUri(URI uri) {
        return restTemplate.getForObject(apiHost + uri.toString(), BeerDto.class);
    }

    @Override
    public URI save(BeerDto dto) {
        return restTemplate.postForLocation(apiHost + BEER_PATH_V1, dto);
    }

    @Override
    public void update(String id, BeerDto dto) {
        restTemplate.put(apiHost + BEER_PATH_V1 + id.toString(), dto);
    }

    @Override
    public void delete(String id) {
        restTemplate.delete(apiHost + BEER_PATH_V1 + id.toString());
    }
}
