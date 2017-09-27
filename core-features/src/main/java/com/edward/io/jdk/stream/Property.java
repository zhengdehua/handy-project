package com.edward.io.jdk.stream;

/**
 * Created by edwardcheng on 2017/8/30.
 */
public class Property {

    private String name;
    private Integer distance;
    private Integer sales;
    private Integer priceLevel;

    public Property(String name, Integer distance, Integer sales, Integer priceLevel) {
        this.name = name;
        this.distance = distance;
        this.sales = sales;
        this.priceLevel = priceLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(Integer priceLevel) {
        this.priceLevel = priceLevel;
    }

    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", distance=" + distance +
                ", sales=" + sales +
                ", priceLevel=" + priceLevel +
                '}';
    }
}
