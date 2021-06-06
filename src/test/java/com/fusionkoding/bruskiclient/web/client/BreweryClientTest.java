package com.fusionkoding.bruskiclient.web.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Random;
import java.util.UUID;

import com.fusionkoding.bruskiclient.web.model.BeerDto;

import com.fusionkoding.bruskiclient.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BreweryClientTest {

    public static final String STOUT_UUID ="0fd60996-9b03-4884-a1dd-1915ae2e3427";
    public static final String DRAGONS_MILK_UUID = "46d320c2-3883-480a-930a-4a2810448d51";
    public static final String GOOMA_UUID = "3cadb2a1-f4cb-4944-a997-7fe7b4696169";

    @Autowired
    BreweryClient client;

    @Test
    void testGetBeerById() {
        BeerDto dto = client.getById(STOUT_UUID);
        assertNotNull(dto);
    }

    @Test
    void testSaveNewBeer() {
        BeerDto dto = BeerDto.builder()
                .price(BigDecimal.valueOf(1.00))
                .upc(new Random().nextLong() + "")
                .beerStyle(BeerStyleEnum.SAISON)
                .beerName("New Beer " + Math.random())
                .quantityOnHand(100)
                .build();
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
        client.update(GOOMA_UUID, dto);
    }

    @Test
    void testDeleteBeer() {
        client.delete(GOOMA_UUID);
    }
}
