package com.micro.shop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.shop.dto.ItemDto;
import com.micro.shop.dto.ShoppingCartDto;
import com.micro.shop.model.Item;
import com.micro.shop.model.ShoppingCart;
import com.micro.shop.service.ShoppingCartService;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

        private final ShoppingCartService shoppingCartService;

        public ShoppingCartController(ShoppingCartService shoppingCartService) {
                this.shoppingCartService = shoppingCartService;
        }

        @PostMapping
        public ShoppingCartDto createShoppingCart(@RequestBody ShoppingCartDto shoppingCartDto) {
                ShoppingCart createdCart = shoppingCartService
                                .createCart(mapDtoToDomain(shoppingCartDto));

                return new ShoppingCartDto(
                                createdCart.getId(),
                                createdCart.getUserId(),
                                mappItemListDto(createdCart.getItemList()));
        }

        @GetMapping("/{shopingCartId}")
        public ShoppingCartDto getShoppingCartById(@PathVariable Long shopingCartId) {
                return shoppingCartService.getCartById(shopingCartId)
                                .map(cart -> new ShoppingCartDto(
                                                cart.getId(),
                                                cart.getUserId(),
                                                mappItemListDto(cart.getItemList())))
                                .orElseThrow(() -> new RuntimeException("Shopping cart not found"));
        }

        @PostMapping("/{userId}/items")
        public ShoppingCartDto addItemsToCart(@PathVariable Long userId,
                        @RequestBody List<ItemDto> itemsToAdd) {
                ShoppingCart updatedCart = shoppingCartService.addItemsToCart(userId,
                                mappItemList(itemsToAdd));

                return new ShoppingCartDto(
                                updatedCart.getId(),
                                updatedCart.getUserId(),
                                mappItemListDto(updatedCart.getItemList()));
        }

        @GetMapping("/user/{userId}")
        public ShoppingCartDto getByUserId(@PathVariable Long userId) {
                return shoppingCartService.getByUserId(userId)
                                .map(cart -> new ShoppingCartDto(
                                                cart.getId(),
                                                cart.getUserId(),
                                                mappItemListDto(cart.getItemList())))
                                .orElseThrow(() -> new RuntimeException("Shopping cart not found"));
        }

        @PostMapping("/user/{userId}/order")
        public void createOrder(@PathVariable Long userId) {
                shoppingCartService.createOrder(userId);
        }

        private ShoppingCart mapDtoToDomain(ShoppingCartDto cart) {
                if (cart == null) {
                        return null;
                }
                ShoppingCart newShoppingCart = new ShoppingCart(
                                cart.getUserId(),
                                new ArrayList<>());
                if (cart.getItemList() != null) {
                        newShoppingCart.setItemList(cart.getItemList()
                                        .stream()
                                        .map(item -> new Item(
                                                        item.getProductId(),
                                                        item.getQuantity(),
                                                        item.getUnitPrice(),
                                                        newShoppingCart))
                                        .toList());
                }
                return newShoppingCart;

        }

        private List<ItemDto> mappItemListDto(List<Item> items) {
                if (items.isEmpty()) {
                        return List.of();
                }
                return items.stream()
                                .map(item -> new ItemDto(
                                                item.getId(),
                                                item.getProductId(),
                                                item.getQuantity(),
                                                item.getUnitPrice()))
                                .toList();
        }

        private List<Item> mappItemList(List<ItemDto> items) {
                if (items == null) {
                        return List.of();
                }
                return items.stream()
                                .map(item -> new Item(
                                                item.getId(),
                                                item.getProductId(),
                                                item.getQuantity(),
                                                item.getUnitPrice(),
                                                null))
                                .toList();
        }
}