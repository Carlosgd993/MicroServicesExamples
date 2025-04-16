package com.micro.orders.dto;

import java.io.Serializable;

public class OrderDto implements Serializable {
    private Long userId;
    private Long cartId;

    public OrderDto() {
    }

    public OrderDto(Long userId, Long cartId) {
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

    @Override
    public String toString() {
        return "OrderDto{" +
                "userId=" + userId +
                ", cartId=" + cartId +
                '}';
    }
}
