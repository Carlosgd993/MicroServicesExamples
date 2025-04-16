package com.micro.shop.service.serviceImpl;

import org.springframework.stereotype.Service;

import com.micro.shop.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

    // private ItemJpaRepository itemJpaRepository;

    // public ItemServiceImpl(ItemJpaRepository itemJpaRepository) {
    // this.itemJpaRepository = itemJpaRepository;
    // }

    // @Override
    // public void createItem(Long shoppingCartId, Long productId, int quantity,
    // double unitPrice) {
    // itemJpaRepository.save(new Item(shoppingCartId, productId, quantity,
    // unitPrice));
    // }

    // @Override
    // public Optional<Item> getItemById(Long id) {
    // return itemJpaRepository.findById(id);
    // }

}