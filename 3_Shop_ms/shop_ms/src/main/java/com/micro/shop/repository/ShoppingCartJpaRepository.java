package com.micro.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.shop.model.ShoppingCart;

public interface ShoppingCartJpaRepository extends JpaRepository<ShoppingCart, Long> {

    Optional<ShoppingCart> findById(Long id);

    Optional<ShoppingCart> findByUserId(Long userId);

    boolean existsByUserId(Long userId);

}
