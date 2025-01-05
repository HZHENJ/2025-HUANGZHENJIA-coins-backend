package com.example.core;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassName CoinCalculatorTest
 * @Description
 * @Author HUANG ZHENJIA
 * @Date 2025/1/4
 * @Version 1.0
 */
public class CoinCalculatorTest {
    @Test
    void testCalculateMinimumCoins() {
        BigDecimal targetAmount = new BigDecimal("7.03");
        List<BigDecimal> denominations = Arrays.asList(
                new BigDecimal("0.01"),
                new BigDecimal("0.5"),
                new BigDecimal("1"),
                new BigDecimal("5"),
                new BigDecimal("10")
        );

        List<BigDecimal> result = CoinCalculator.calculateMinimumCoins(targetAmount, denominations);

        // Expected result in ascending order
        List<BigDecimal> expected = Arrays.asList(
                new BigDecimal("0.01"),
                new BigDecimal("0.01"),
                new BigDecimal("0.01"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("5")
        );

        assertEquals(expected, result);
    }

    @Test
    void testCannotFormTargetAmount() {
        BigDecimal targetAmount = new BigDecimal("7.03");
        List<BigDecimal> denominations = Arrays.asList(
                new BigDecimal("2"),
                new BigDecimal("3")
        );

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                CoinCalculator.calculateMinimumCoins(targetAmount, denominations)
        );

        assertEquals("Target amount cannot be formed with the given denominations.", exception.getMessage());
    }
}
