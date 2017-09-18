package com.expertsoft.model;

public class OrderItem {

    public OrderItem () {}

    public OrderItem(final Product product, final Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    private Product product;
    private Integer quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
