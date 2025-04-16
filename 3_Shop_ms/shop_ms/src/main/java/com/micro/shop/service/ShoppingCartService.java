
package com.micro.shop.service;

import java.util.List;
import java.util.Optional;

import com.micro.shop.model.Item;
import com.micro.shop.model.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart createCart(ShoppingCart cart);

    Optional<ShoppingCart> getCartById(Long cartId);

    ShoppingCart addItemsToCart(Long cartId, List<Item> item);

    Optional<ShoppingCart> getByUserId(Long userId);

    void createOrder(Long userId);

    // ShoppingCart removeItemFromCart(Long cartId, Long itemId);

    // List<Item> getItemsInCart(Long cartId);

    // double calculateTotal(Long cartId);
}