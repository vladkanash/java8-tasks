package com.expertsoft.model;

import java.math.BigDecimal;

public class Product {

    public enum Color {
        WHITE,
        BLACK,
        RED,
        BLUE,
        YELLOW
    }

    public Product() {}

    private Long id;
    private String name;
    private Color color;
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product #" + id + " (" + name + ")";
    }
}
