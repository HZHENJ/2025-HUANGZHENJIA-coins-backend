package com.example.api;

import java.util.List;

/**
 * @ClassName MinimumCoinsResponse
 * @Description
 * @Author HUANG ZHENJIA
 * @Date 2025/1/4
 * @Version 1.0
 */
public class MinimumCoinsResponse {
    private List<Double> coins;
    public MinimumCoinsResponse(List<Double> coins){
        this.coins = coins;
    }
    public List<Double> getCoins() {
        return coins;
    }
    public void setCoins(List<Double> coins){
        this.coins = coins;
    }
}
