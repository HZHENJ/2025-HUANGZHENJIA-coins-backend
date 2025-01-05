package com.example.resources;

import com.example.api.MinimumCoinsRequest;
import com.example.api.MinimumCoinsResponse;
import com.example.core.CoinCalculator;
import com.example.util.ErrorResponse;

import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @ClassName MinimumCoinsResource
 * @Description
 * @Author HUANG ZHENJIA
 * @Date 2025/1/3
 * @Version 1.0
 */

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MinimumCoinsResource {
    public MinimumCoinsResource() {}

    @GET
    @Path("/hello")
    public String sayHello() {
        return "Hello World!";
    }

    @POST
    @Path("/minimum-coins")
    public Response getMinimumCoins(MinimumCoinsRequest request) {
        try {
            BigDecimal targetAmount = request.getTargetAmount();
            List<BigDecimal> denominations = request.getCoinDenominations();

            // Validate input
            if (targetAmount.compareTo(BigDecimal.ZERO) < 0 ||
                    targetAmount.compareTo(new BigDecimal("10000")) > 0 ||
                    denominations == null || denominations.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ErrorResponse("Invalid input"))
                        .build();
            }

            // Calculate minimum coins
            List<BigDecimal> result = CoinCalculator.calculateMinimumCoins(targetAmount, denominations);

            // Convert response to List<Double> for easier JSON serialization
            List<Double> response = result.stream()
                    .map(BigDecimal::doubleValue)
                    .collect(Collectors.toList());

            return Response.ok(new MinimumCoinsResponse(response)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        }
    }

    // built a thread pool
    private static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(10);

    @POST
    @Path("/minimum-coins-threadpool")
    public void getMinimumCoinsAsync(
            MinimumCoinsRequest request,
            @Suspended AsyncResponse asyncResponse
    ) {
        THREAD_POOL.submit(()->{
            try {
                BigDecimal targetAmount = request.getTargetAmount();
                List<BigDecimal> denominations = request.getCoinDenominations();

                // Validate input
                if (targetAmount.compareTo(BigDecimal.ZERO) < 0 ||
                        targetAmount.compareTo(new BigDecimal("10000")) > 0 ||
                        denominations == null || denominations.isEmpty()) {
                    asyncResponse.resume(
                            Response.status(Response.Status.BAD_REQUEST)
                                    .entity(new ErrorResponse("Invalid input"))
                                    .build()
                    );
                    return;
                }

                // Calculate minimum coins
                List<BigDecimal> result = CoinCalculator.calculateMinimumCoins(targetAmount, denominations);

                // Convert response to List<Double> for easier JSON serialization
                List<Double> response = result.stream()
                        .map(BigDecimal::doubleValue)
                        .collect(Collectors.toList());

                asyncResponse.resume(
                        Response.ok(new MinimumCoinsResponse(response)).build()
                );

            } catch (IllegalArgumentException e) {
                asyncResponse.resume(
                        Response.status(Response.Status.BAD_REQUEST)
                                .entity(new ErrorResponse(e.getMessage()))
                                .build()
                );
            } catch (Exception e) {
                asyncResponse.resume(
                        Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                                .entity(new ErrorResponse("Internal server error"))
                                .build()
                );
            }
        });
    }

}
