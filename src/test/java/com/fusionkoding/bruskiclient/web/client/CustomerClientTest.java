package com.fusionkoding.bruskiclient.web.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.UUID;

import com.fusionkoding.bruskiclient.web.model.BeerDto;
import com.fusionkoding.bruskiclient.web.model.CustomerDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerClientTest {

    @Autowired
    CustomerClient client;

    @Test
    void testGetCustomerById() {
        CustomerDto dto = client.getById(UUID.randomUUID());
        assertNotNull(dto);
    }

    @Test
    void testSaveNewCustomer() {
        CustomerDto dto = CustomerDto.builder().name("NewName").build();
        URI uri = client.save(dto);
        assertNotNull(uri);
    }

    @Test
    void testGetSavedCustomer() {
        CustomerDto beerDto = CustomerDto.builder().build();
        URI uri = client.save(beerDto);
        CustomerDto dto = client.getByUri(uri);
        assertNotNull(dto);
    }

    @Test
    void testUpdateCustomer() {
        CustomerDto dto = CustomerDto.builder().name("NewName").build();
        client.update(UUID.randomUUID(), dto);
    }

    @Test
    void testDeleteCustomer() {
        client.delete(UUID.randomUUID());
    }
}
