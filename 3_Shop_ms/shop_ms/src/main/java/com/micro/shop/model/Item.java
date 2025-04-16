package com.micro.shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private int quantity;

    private double unitPrice;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id", nullable = false)
    private ShoppingCart shoppingCart; // Relación con Shoping Cart

    public Item() {
    }

    public Item(Long productId, int cantidad, double precioUnit, ShoppingCart shoppingCart) {
        this.productId = productId;
        this.quantity = cantidad;
        this.unitPrice = precioUnit;
        this.shoppingCart = shoppingCart;
    }

    public Item(Long id, Long productId, int cantidad, double precioUnit, ShoppingCart shoppingCart) {
        this.id = id;
        this.productId = productId;
        this.quantity = cantidad;
        this.unitPrice = precioUnit;
        this.shoppingCart = shoppingCart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int cantidad) {
        this.quantity = cantidad;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

}
