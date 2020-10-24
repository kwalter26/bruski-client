package com.fusionkoding.bruskiclient.web.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.UUID;

import com.fusionkoding.bruskiclient.web.model.BeerDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BreweryClientTest {

    @Autowired
    BreweryClient client;

    @Test
    void testGetBeerById() {
        BeerDto dto = client.getById(UUID.randomUUID());
        assertNotNull(dto);
    }

    @Test
    void testSaveNewBeer() {
        BeerDto dto = client.getById(UUID.randomUUID());
        URI uri = client.save(dto);
        assertNotNull(uri);
    }

    @Test
    void testGetSavedBeer() {
        BeerDto beerDto = BeerDto.builder().build();
        URI uri = client.save(beerDto);
        BeerDto dto = client.getByUri(uri);
        assertNotNull(dto);
    }

    @Test
    void testUpdateBeer() {
        BeerDto dto = BeerDto.builder().beerName("New Beer").build();
        client.update(UUID.randomUUID(), dto);
    }

    @Test
    void testDeleteBeer() {
        client.delete(UUID.randomUUID());
    }
}
