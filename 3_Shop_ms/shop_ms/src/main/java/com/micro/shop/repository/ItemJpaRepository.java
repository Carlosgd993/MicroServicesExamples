package com.micro.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.shop.model.Item;

public interface ItemJpaRepository extends JpaRepository<Item, Long> {
}