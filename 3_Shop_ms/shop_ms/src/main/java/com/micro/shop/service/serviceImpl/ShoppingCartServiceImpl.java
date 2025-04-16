package com.micro.shop.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.micro.shop.client.UserServiceClient;
import com.micro.shop.model.Item;
import com.micro.shop.model.ShoppingCart;
import com.micro.shop.model.client.Order;
import com.micro.shop.repository.ShoppingCartJpaRepository;
import com.micro.shop.service.ShoppingCartService;
import com.micro.shop.service.kafka.KafkaProducerService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartJpaRepository repositoryShoppingCart;
    private final UserServiceClient userServiceClient;
    private final KafkaProducerService kafkaProducerService;

    public ShoppingCartServiceImpl(ShoppingCartJpaRepository shoppingCartJpaRepository,
            UserServiceClient userServiceClient,
            KafkaProducerService kafkaProducerService) {
        this.repositoryShoppingCart = shoppingCartJpaRepository;
        this.userServiceClient = userServiceClient;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public ShoppingCart createCart(ShoppingCart cart) {
        // Llamada al ms de usuario para verificar si el usuario existe
        if (!userServiceClient.userExists(cart.getUserId())) {
            throw new RuntimeException("El usuario no existe: " + cart.getUserId());
        }
        // Verificar si ya existe un carrito para el usuario
        if (repositoryShoppingCart.existsByUserId(cart.getUserId())) {
            throw new RuntimeException("Ya existe un carrito para el usuario: " + cart.getUserId());
        }

        ShoppingCart newShoppingCart = repositoryShoppingCart.save(cart);

        return newShoppingCart;

    }

    @Override
    public Optional<ShoppingCart> getCartById(Long cartId) {
        return repositoryShoppingCart.findById(cartId);
    }

    @Override
    public ShoppingCart addItemsToCart(Long userId, List<Item> itemsToAdd) {
        return repositoryShoppingCart.findByUserId(userId)
                .map(cart -> {
                    List<Item> updatedItems = new ArrayList<>(cart.getItemList());
                    itemsToAdd.stream()
                            .peek(item -> {
                                item.setShoppingCart(cart);
                            }).toList();
                    updatedItems.addAll(itemsToAdd);
                    cart.setItemList(updatedItems);
                    return repositoryShoppingCart.save(cart);
                })
                .orElseThrow(() -> new RuntimeException("No existe un carrito para el usuario: " + userId));
    }

    @Override
    public Optional<ShoppingCart> getByUserId(Long userId) {
        return repositoryShoppingCart.findByUserId(userId);

    }

    @Override
    public void createOrder(Long userId) {
        ShoppingCart cart = repositoryShoppingCart.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("No existe un carrito para el usuario: " + userId));

        Order order = new Order(userId, cart.getId());

        kafkaProducerService.sendMessage(order);

    }

}