package com.example.api;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName MinimumCoinsRequest
 * @Description
 * @Author HUANG ZHENJIA
 * @Date 2025/1/4
 * @Version 1.0
 */
public class MinimumCoinsRequest {
    private BigDecimal targetAmount;
    private List<BigDecimal> coinDenominations;
    public BigDecimal getTargetAmount() {
        return targetAmount;
    }
    public void setTargetAmount(BigDecimal targetAmount) {
        this.targetAmount = targetAmount;
    }
    public List<BigDecimal> getCoinDenominations() {
        return coinDenominations;
    }
    public void setCoinDenominations(List<BigDecimal> coinDenominations) {
        this.coinDenominations = coinDenominations;
    }

}
