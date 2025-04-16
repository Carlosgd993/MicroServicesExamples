package com.micro.shop.model.client;

import java.io.Serializable;

public class Order implements Serializable {

    private Long userId;
    private Long cartId;

    public Order() {
    }

    public Order(Long userId, Long cartId) {
        this.userId = userId;
        this.cartId = cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
}
