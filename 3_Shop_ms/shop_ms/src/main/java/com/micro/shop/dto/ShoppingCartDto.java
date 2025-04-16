package com.micro.shop.dto;

import java.util.List;

public class ShoppingCartDto {

    private Long id;
    private Long userId;
    private List<ItemDto> itemList;

    public ShoppingCartDto() {
    }

    public ShoppingCartDto(Long userId, List<ItemDto> itemList) {
        this.userId = userId;
        this.itemList = itemList;
    }

    public ShoppingCartDto(Long id, Long userId, List<ItemDto> itemList) {
        this.id = id;
        this.userId = userId;
        this.itemList = itemList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<ItemDto> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemDto> itemList) {
        this.itemList = itemList;
    }

}
