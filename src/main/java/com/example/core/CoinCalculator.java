package com.example.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName CoinCalculator
 * @Description
 * @Author HUANG ZHENJIA
 * @Date 2025/1/4
 * @Version 1.0
 */
public class CoinCalculator {
    public static List<BigDecimal> calculateMinimumCoins(BigDecimal targetAmount, List<BigDecimal> denominations) {
        // Sort denominations in descending order
        denominations.sort(Collections.reverseOrder());

        List<BigDecimal> result = new ArrayList<>();

        BigDecimal remainingAmount = targetAmount;

        for (BigDecimal coin : denominations) {
            while (remainingAmount.compareTo(coin) >= 0) {
                result.add(coin);
                remainingAmount = remainingAmount.subtract(coin);
            }
        }

        if (remainingAmount.compareTo(BigDecimal.ZERO) > 0) {
            throw new IllegalArgumentException("Target amount cannot be formed with the given denominations.");
        }

        // Sort the result in ascending order before returning
        result.sort(BigDecimal::compareTo);

        return result;
    }
}
