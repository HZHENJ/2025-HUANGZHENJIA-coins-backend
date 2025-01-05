package com.example.resources;

import com.example.api.MinimumCoinsRequest;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @ClassName MinimumCoinsResourceTest
 * @Description
 * @Author HUANG ZHENJIA
 * @Date 2025/1/4
 * @Version 1.0
 */

public class MinimumCoinsResourceTest {

    @Rule
    public final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new MinimumCoinsResource())
            .build();

    // @Test
    // void testCalculateMinimumCoins_Success_fail() {
    //     String inputJson = """
    //             {
    //               "targetAmount": 7.03,
    //               "coinDenominations": [0.01, 0.5, 1, 5, 10]
    //             }
    //             """;
    //
    //     Response response = resources.target("/api/minimum-coins")
    //             .request(MediaType.APPLICATION_JSON_TYPE)
    //             .post(Entity.json(inputJson));
    //
    //     assertEquals(200, response.getStatus());
    //     String responseBody = response.readEntity(String.class);
    //     assertNotNull(responseBody, "Response body should not be null");
    // }

    @Test
    public void testCalculateMinimumCoins_Success() {
        // data
        MinimumCoinsRequest request = new MinimumCoinsRequest();
        request.setTargetAmount(new BigDecimal("7.03"));
        request.setCoinDenominations(Arrays.asList(
                new BigDecimal("0.01"),
                new BigDecimal("0.5"),
                new BigDecimal("1"),
                new BigDecimal("5")
        ));

        // post request
        Response response = resources.target("/api/minimum-coins")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(request));

        // resp
        assertEquals(200, response.getStatus());
        String responseBody = response.readEntity(String.class);
        assertNotNull(responseBody);
        System.out.println("Response: " + responseBody);
    }

    @Test
    public void testCalculateMinimumCoins_InvalidInput() {
        MinimumCoinsRequest request = new MinimumCoinsRequest();
        request.setTargetAmount(new BigDecimal("-5"));
        request.setCoinDenominations(Arrays.asList(
                new BigDecimal("0.01"),
                new BigDecimal("0.5"),
                new BigDecimal("1"),
                new BigDecimal("5")
        ));

        Response response = resources.target("/api/minimum-coins")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(request));

        assertEquals(400, response.getStatus());
        String responseBody = response.readEntity(String.class);
        System.out.println("Response: " + responseBody);
    }

    @Test
    public void testCalculateMinimumCoins_EmptyDenominations() {
        MinimumCoinsRequest request = new MinimumCoinsRequest();
        request.setTargetAmount(new BigDecimal("7.03"));
        request.setCoinDenominations(Arrays.asList());

        Response response = resources.target("/api/minimum-coins")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(request));

        assertEquals(400, response.getStatus());
        String responseBody = response.readEntity(String.class);
        System.out.println("Response: " + responseBody);
    }

}
