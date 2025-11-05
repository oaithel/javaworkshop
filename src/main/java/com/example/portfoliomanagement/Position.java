package com.example.portfoliomanagement;

import javafx.beans.property.StringProperty;

/*
TODO: Next session we will use Property (Java)
 */
public class Position {
    //private int id;
   // private StringProperty symbol;
    private String symbol;
    private int shares;
    private double price;
    private double totalPrice;
    private double currentPrice;
    private double profitLoss;

    public Position(String symbol, int shares, double price) {
        this.symbol = symbol;
        this.shares = shares;
        this.price = price;
        this.totalPrice = price * shares;
        this.currentPrice = price;
        this.profitLoss = (currentPrice - price)*shares;
    }

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public int getShares() {
        return shares;
    }
    public void setShares(int shares) {
        this.shares = shares;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
        totalPrice = price * shares;
        profitLoss = (currentPrice-price) * shares;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public double getCurrentPrice() {
        return currentPrice;
    }
    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }
    public double getProfitLoss() {
        return profitLoss;
    }
    public void setProfitLoss(double profitLoss) {
        this.profitLoss = profitLoss;
    }

    @Override
    public String toString() {
        return "Position{" +
                "symbol='" + symbol + '\'' +
                ", shares=" + shares +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", currentPrice=" + currentPrice +
                ", profitLoss=" + profitLoss +
                '}';
    }
}
